package nl.lengrand.cellar;


import nl.lengrand.cellar.driver.DataDriver;
import nl.lengrand.cellar.driver.DataDriverProvider;
import nl.lengrand.cellar.driver.Dht11DataDriver;
import nl.lengrand.cellar.store.SensorValue;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/sensor")
@RequestScoped
public class CellarResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Inject @DataDriverProvider.SpecificDataDriver
    private DataDriver dataDriver;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getSensorValues(){
        return createResponse(dataDriver.getSensorValues());
    }

    private JsonObject createResponse(SensorValue value) {
        return JSON.createObjectBuilder()
                .add("temperature", value.getTemperature())
                .add("humidity", value.getHumidity())
                .add("reading", value.getReading().toString())
                .build();
    }
}
