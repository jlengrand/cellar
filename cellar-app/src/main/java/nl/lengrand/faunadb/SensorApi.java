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

    public void add(SensorValue values) throws ExecutionException, InterruptedException {
        Value addDataResult = connection.getClient().query(
                Create(
                        Collection(Language.Value(COLLECTION_NAME)),
                        Obj("data",
                                Obj( "temperature", Language.Value(values.getTemperature()),
                                        "humidity", Language.Value(values.getHumidity()) ,
                                        "timestamp", Language.Value(Instant.now())
                                )
                        )
                )
        ).get();
        System.out.println("Added sensor data to collection " + COLLECTION_NAME + ":\n " + addDataResult + "\n");
    }

    public void add(float[] res) throws ExecutionException, InterruptedException {
        Value addDataResult = connection.getClient().query(
                Create(
                        Collection(Language.Value(COLLECTION_NAME)),
                        Obj("data",
                                Obj( "temperature", Language.Value(res[0]),
                                        "humidity", Language.Value(res[1]),
                                        "timestamp", Language.Value(Instant.now())
                                )
                        )
                )
        ).get();
        System.out.println("Added sensor data to collection " + COLLECTION_NAME + ":\n " + addDataResult + "\n");
    }

    public void update(float[] res) throws ExecutionException, InterruptedException {
        Value updateDataResult =
                connection.getClient().query(
                        Update(
                                Select(Language.Value("ref"), Get(Match(Index(INDEX_NAME), Language.Value(UPDATE_DEVICE_NAME)))),
                                Obj("data",
                                        Obj(
                                                "temperature", Language.Value(res[0]),
                                                "humidity", Language.Value(res[1]),
                                                "timestamp", Language.Value(Instant.now())
                                        )
                                )
                        )
                ).get();
        System.out.println("Updated sensor data from collection " + COLLECTION_NAME + ":\n " + updateDataResult + "\n");
    }
}