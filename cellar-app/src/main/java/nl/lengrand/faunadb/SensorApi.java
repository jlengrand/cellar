package nl.lengrand.faunadb;

import com.faunadb.client.query.Language;
import com.faunadb.client.types.Value;
import nl.lengrand.SensorValue;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;
import static com.faunadb.client.query.Language.Obj;
import static nl.lengrand.faunadb.Connection.*;

public class SensorApi {

    private Connection connection;

    public SensorApi(){
        this.connection = new Connection();
        connection.init();
    }

    public void add(SensorValue values) {
        Value addDataResult = null;
        try {
            addDataResult = connection.getClient().query(
                    Create(
                            Collection(Language.Value(COLLECTION_NAME)),
                            Obj("data",
                                    Obj( "temperature", Language.Value(values.getTemperature()),
                                            "humidity", Language.Value(values.getHumidity()) ,
                                            "reading", Language.Value(values.getReading()),
                                            "timestamp", Language.Value(Instant.now())
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