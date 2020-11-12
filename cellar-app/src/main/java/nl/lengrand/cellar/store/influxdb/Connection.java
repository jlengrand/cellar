package nl.lengrand.cellar.store.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.WritePrecision;

public class Connection {

    private static final String API_LOCATION = "http://localhost:8086";
    private static final String API_KEY_NAME = "INFLUX_CELLAR_KEY";
    private static final String ORG_NAME = "cellar";
    private static final String BUCKET_NAME = "cellar-data";
    protected static final WritePrecision PRECISION = WritePrecision.S;

    private InfluxDBClient client;

    public Connection(){
        client = InfluxDBClientFactory.create(API_LOCATION, getKey().toCharArray(), ORG_NAME, BUCKET_NAME);
    }

    private String getKey() {
        return System.getenv(API_KEY_NAME);
    }

    public InfluxDBClient getClient() {
        return client;
    }
}
