package nl.lengrand.cellar.store.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Connection {

    @Inject
    @ConfigProperty(name = "sensor.api.influx.location", defaultValue = "http://localhost:8086")
    private String api_location;

    @Inject
    @ConfigProperty(name = "sensor.api.influx.key")
    private String api_key;

    @Inject
    @ConfigProperty(name = "sensor.api.influx.organization", defaultValue = "cellar")
    private String organization;

    @Inject
    @ConfigProperty(name = "sensor.api.influx.bucket", defaultValue = "cellar-data")
    private String bucket;


    private InfluxDBClient client;

    @PostConstruct
    public void initConnection(){
        if(api_key == null) System.out.println("No value found for influx API Key");
        client = InfluxDBClientFactory.create(api_location, api_key.toCharArray(), organization, bucket);
    }

    public InfluxDBClient getClient() {
        return client;
    }
}
