package nl.lengrand.cellar.store.faunadb;

import com.faunadb.client.query.Language;
import com.faunadb.client.types.Value;
import nl.lengrand.cellar.store.SensorValue;
import nl.lengrand.cellar.store.SensorApi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;
import static com.faunadb.client.query.Language.Obj;

@ApplicationScoped
public class FaunaSensorApi implements SensorApi {

    @Inject
    @ConfigProperty(name = "sensor.api.fauna.collection", defaultValue = "sensors")
    private String collection_name = "sensors";

    @Inject
    private Connection connection;

    @Override
    public void add(SensorValue value) {
        try {
            Value addDataResult = connection.getClient().query(
                    Create(
                            Collection(Language.Value(collection_name)),
                            Obj("data",
                                    Obj( "temperature", Language.Value(value.getTemperature()),
                                            "humidity", Language.Value(value.getHumidity()) ,
                                            "reading", Language.Value(value.getReading()),
                                            "timestamp", Language.Value(value.getTimestamp())
                                    )
                            )
                    )
            ).get();
            System.out.println("Added sensor data to collection " + collection_name + ":\n " + addDataResult + "\n");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Failing to upload data! Restarting Connection");
            e.printStackTrace();
            connection.init();
        }
    }
}