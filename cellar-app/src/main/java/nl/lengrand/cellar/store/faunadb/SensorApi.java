package nl.lengrand.cellar.store.faunadb;

import nl.lengrand.cellar.SensorValue;

public interface SensorApi {
    void add(SensorValue values);
}
