package nl.lengrand;

import nl.lengrand.cellar.Dht11Driver;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CellarProvider {

    private Dht11Driver driver = new Dht11Driver();

    public SensorValue getSensorValues(){
        float[] values =  driver.getTemperatureAndHumidity();
        return new SensorValue(values);
    }
}
