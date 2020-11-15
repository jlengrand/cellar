package nl.lengrand.cellar.store.faunadb;

import com.faunadb.client.query.Language;
import com.faunadb.client.types.Value;
import nl.lengrand.cellar.store.SensorApiProvider;
import nl.lengrand.cellar.store.SensorValue;
import nl.lengrand.cellar.store.SensorApi;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;
import static com.faunadb.client.query.Language.Obj;
import static nl.lengrand.cellar.store.faunadb.Connection.*;

@ApplicationScoped
public class FaunaSensorApi implements SensorApi {

    private Connection connection;

    public FaunaSensorApi(){
        this.connection = new Connection();
        connection.init();
    }

    @Override
    public void add(SensorValue value) {
        try {
            Value addDataResult = connection.getClient().query(
                    Create(
                            Collection(Language.Value(COLLECTION_NAME)),
                            Obj("data",
                                    Obj( "temperature", Language.Value(value.getTemperature()),
                                            "humidity", Language.Value(value.getHumidity()) ,
                                            "reading", Language.Value(value.getReading()),
                                            "timestamp", Language.Value(value.getTimestamp())
                                    )
                            )
                    )
            ).get();
            System.out.println("Added sensor data to collection " + COLLECTION_NAME + ":\n " + addDataResult + "\n");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Failing to upload data! Restarting Connection");
            e.printStackTrace();
            connection.init();
        }
    }
}