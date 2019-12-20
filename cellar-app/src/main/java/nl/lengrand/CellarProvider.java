package nl.lengrand;

import nl.lengrand.cellar.Dht11Driver;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CellarProvider {

    private static final int N_TRIES = 5;

    private Dht11Driver driver = new Dht11Driver();

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
}
