package nl.lengrand.cellar.store.influxdb;

import com.influxdb.client.WriteApi;
import nl.lengrand.cellar.store.SensorApi;
import nl.lengrand.cellar.store.SensorValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InfluxDbSensorApi implements SensorApi {

    private Connection connection;

    public InfluxDbSensorApi(){
        this.connection = new Connection();
    }

    @Override
    public void add(SensorValue value) {
        try (WriteApi writeApi = connection.getClient().getWriteApi()) {
            writeApi.writeMeasurement(connection.PRECISION, value);
            System.out.println("Added sensor data to bucket :\n " + value + "\n");
        }
    }
}
