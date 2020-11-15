package nl.lengrand.cellar.store;

import nl.lengrand.cellar.store.faunadb.FaunaSensorApi;
import nl.lengrand.cellar.store.influxdb.InfluxDbSensorApi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class SensorApiProvider {

    @Inject
    @ConfigProperty(name = "sensor.api.type", defaultValue = "influx")
    private String sensorApiType;

    @Produces
    public SensorApi getSensorApi() {
        System.out.println("THE TYPE " + sensorApiType);
        if (sensorApiType.equals("influx")) {
            System.out.println("influx db api requested");
            return new InfluxDbSensorApi();
        } else {
            System.out.println("fauna db api requested");
            return new FaunaSensorApi();
        }
    }

}
