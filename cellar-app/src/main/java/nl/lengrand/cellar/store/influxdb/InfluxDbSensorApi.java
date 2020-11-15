package nl.lengrand.cellar.store.influxdb;

import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import nl.lengrand.cellar.store.SensorApi;
import nl.lengrand.cellar.store.SensorValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InfluxDbSensorApi implements SensorApi {

    @Inject
    private Connection connection;

    @Inject
    private WritePrecision PRECISION;

    @Override
    public void add(SensorValue value) {
        try (WriteApi writeApi = connection.getClient().getWriteApi()) {
            writeApi.writeMeasurement(PRECISION, value);
            System.out.println("Added sensor data to bucket :\n " + value + "\n");
        }
    }
}
