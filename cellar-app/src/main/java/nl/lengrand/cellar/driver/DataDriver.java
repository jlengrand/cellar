package nl.lengrand.cellar.driver;

import nl.lengrand.cellar.store.SensorValue;

public interface DataDriver {

    SensorValue getSensorValues();
}
