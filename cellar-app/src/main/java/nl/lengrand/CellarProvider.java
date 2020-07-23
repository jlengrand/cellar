package nl.lengrand;

import nl.lengrand.cellar.Dht11Driver;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CellarProvider {

    @ConfigProperty(name = "monitoring.enabled", defaultValue = "true")
    private static boolean monitoringEnabled;

    private static final int N_TRIES = 5;

    private Dht11Driver driver = new Dht11Driver();

    public CellarProvider(){
        if(monitoringEnabled) {
            System.out.println("Monitoring enabled by config. Starting up");
            startMonitoring();
        }
        else System.out.println("Monitoring disabled by config");
    }


    public SensorValue getSensorValues(){

        int counter = 0;
        float[] values;
        do {
            values =  driver.getTemperatureAndHumidity();
            counter++;
        }
        while (noSensorData(values) && counter < N_TRIES);

        return new SensorValue(values);
    }

    private boolean noSensorData(float[] values) {
        return values[0] == 0 && values[1] == 0;
    }

    static void startMonitoring(){
        CellarMonitor monitor = new CellarMonitor();
        monitor.startMonitoring();
    }
}
