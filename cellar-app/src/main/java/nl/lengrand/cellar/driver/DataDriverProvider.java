package nl.lengrand.cellar.driver;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class DataDriverProvider {

    @Inject
    @ConfigProperty(name = "driver.type", defaultValue = "dht11")
    private String driverType;

    @Produces
    public DataDriver getDataProvider() {
        System.out.println("THE DRIVER TYPE " + driverType);
        if (driverType.equals("dht11")) {
            System.out.println("dht11 data driver requested");
            return new Dht11DataDriver();
        } else {
            System.out.println("dummy driver requested");
            return new DummyDataDriver();
        }
    }

}
