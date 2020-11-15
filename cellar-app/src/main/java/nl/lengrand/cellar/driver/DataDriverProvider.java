package nl.lengrand.cellar.driver;

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
public class DataDriverProvider {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.METHOD})
    public @interface SpecificDataDriver{}

    @Inject
    @ConfigProperty(name = "driver.type", defaultValue = "dht11")
    private String driverType;

    @Inject
    private Dht11DataDriver dht11DataDriver;

    @Inject
    private DummyDataDriver dummyDataDriver;

    @Produces @SpecificDataDriver
    public DataDriver getDataProvider() {
        System.out.println("THE DRIVER TYPE " + driverType);
        if (driverType.equals("dht11")) {
            System.out.println("dht11 data driver requested");
            return dht11DataDriver;
        } else {
            System.out.println("dummy driver requested");
            return dummyDataDriver;
        }
    }

}
