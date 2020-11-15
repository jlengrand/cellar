package nl.lengrand.cellar.driver;

import nl.lengrand.cellar.store.SensorValue;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class DummyDataDriver implements DataDriver {

    public SensorValue getSensorValues(){
        Random r = new Random();
        return new SensorValue(new float[]{20 + r.nextFloat() * (25 - 20), 60 + r.nextFloat() * (70 - 60)});
    }
}
