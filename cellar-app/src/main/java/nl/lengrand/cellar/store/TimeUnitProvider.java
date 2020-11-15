package nl.lengrand.cellar.store;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TimeUnitProvider {

    @Inject
    @ConfigProperty(name = "monitor.time.unit", defaultValue = "MINUTES")
    private String monitorTimeUnit;

    @Produces
    public TimeUnit getTimeUnit() {
        System.out.println("THE Time Unit " + monitorTimeUnit);
        return TimeUnit.valueOf(monitorTimeUnit);
    }

}
