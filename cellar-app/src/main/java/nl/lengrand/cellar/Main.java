package nl.lengrand.cellar;

import io.helidon.microprofile.server.Server;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

public class Main {

    @ConfigProperty(name = "monitoring.enabled", defaultValue = "true")
    private static boolean monitoringEnabled;

    private Main() { }

    public static void main(final String[] args) throws IOException {
        Server server = startServer();

        if(monitoringEnabled) {
            System.out.println("Monitoring enabled by config. Starting up");
            startMonitoring();
        }
        else System.out.println("Monitoring disabled by config");

    }

    static Server startServer() {
        return Server.create().start();
    }

    static void startMonitoring(){
        CellarMonitor monitor = new CellarMonitor();
        monitor.startMonitoring();
    }
}
