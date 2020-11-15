package nl.lengrand.cellar.store;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CellarMonitoring {

    @Inject
    @ConfigProperty(name = "monitoring.enabled", defaultValue = "true")
    private volatile boolean monitoringEnabled;

    @Inject
    private CellarMonitor monitor;

    public void start(){
        if (monitoringEnabled) {
            System.out.println("Monitoring enabled by config. Starting up");
            monitor.startMonitoring();
        } else {
            System.out.println("Monitoring disabled by config");
        }
    }

    // Hooks into the application start
    private void onStartup(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        this.start();
    }
}
