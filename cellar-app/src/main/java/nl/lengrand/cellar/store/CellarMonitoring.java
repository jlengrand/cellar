package nl.lengrand.cellar.store;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CellarMonitoring {

    public void start(){
        System.out.println("Monitoring enabled by config. Starting up");
        CellarMonitor monitor = new CellarMonitor();
        monitor.startMonitoring();
    }
}
