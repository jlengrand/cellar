package nl.lengrand.cellar.store;

import com.influxdb.client.domain.WritePrecision;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class WritePrecisionProvider {

    @Inject
    @ConfigProperty(name = "sensor.api.influx.precision", defaultValue = "S")
    private volatile String writePrecision;

    @Produces
    public WritePrecision getTimeUnit() {
        System.out.println("THE write precision " + writePrecision);
        return WritePrecision.valueOf(writePrecision);
    }

}
