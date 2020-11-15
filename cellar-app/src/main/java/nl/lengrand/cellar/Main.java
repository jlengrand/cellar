package nl.lengrand.cellar;

import io.helidon.config.Config;
import io.helidon.microprofile.server.Server;

import static io.helidon.config.ConfigSources.classpath;
import static io.helidon.config.ConfigSources.file;

public class Main {

    private Main() {}

    public static void main(final String[] args){
        Server.builder()
                .config(buildConfig())
                .build().start();
    }

    private static Config buildConfig() {
        return Config.builder()
                .disableEnvironmentVariablesSource()
                .sources(
                        file("cellar-config.properties").optional(),
                        classpath("META-INF/microprofile-config.properties"))
                .build();
    }
}
