package nl.lengrand.cellar.store.faunadb;

import com.faunadb.client.FaunaClient;
import com.faunadb.client.query.Language;
import com.faunadb.client.types.Value;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;

public class Connection {

    @Inject
    @ConfigProperty(name = "sensor.api.fauna.key")
    private String api_key;

    @Inject
    @ConfigProperty(name = "sensor.api.fauna.db", defaultValue = "cellar")
    private String db_name = "cellar";

    private FaunaClient client;

    public void init(){
        try {
            this.client = createConnection();
        } catch (ExecutionException | InterruptedException | ConnectionException e) {
            System.out.println("Problem while initializing connection with FaunaDb");
            e.printStackTrace();
        }
    }

    private String getDbKey(FaunaClient adminClient) throws InterruptedException, ExecutionException {
        Value keyResults = adminClient.query(
                CreateKey(Obj("database", Database(Language.Value(db_name)), "role", Language.Value("server")))
        ).get();

        return keyResults.at("secret").to(String.class).get();
    }

    public FaunaClient createConnection() throws ExecutionException, InterruptedException, ConnectionException {
        FaunaClient adminClient = this.createAdminConnection();
        return adminClient.newSessionClient(getDbKey(adminClient));
    }

    public FaunaClient createAdminConnection() throws ConnectionException {
        if(api_key == null) throw new ConnectionException("Local API key not found !");
        return FaunaClient.builder()
                .withSecret(api_key)
                .build();
    }

    public FaunaClient getClient(){
        return client;
    }
}

