package nl.lengrand.cellar.store;

import nl.lengrand.cellar.store.faunadb.FaunaSensorApi;
import nl.lengrand.cellar.store.influxdb.InfluxDbSensorApi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApplicationScoped
public class SensorApiProvider {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.METHOD})
    public @interface SpecificSensorApi{}

    @Inject
    private InfluxDbSensorApi influxDbSensorApi;

    @Inject
    private FaunaSensorApi faunaSensorApi;

    @Inject
    @ConfigProperty(name = "sensor.api.type", defaultValue = "influx")
    private String sensorApiType;

    @Produces @SpecificSensorApi
    public SensorApi getSensorApi() {
        System.out.println("THE TYPE " + sensorApiType);
        if (sensorApiType.equals("influx")) {
            System.out.println("influx db api requested");
            return influxDbSensorApi;
        } else {
            System.out.println("fauna db api requested");
            return faunaSensorApi;
        }
    }

}
