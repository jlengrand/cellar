package nl.lengrand.cellar.store;

import nl.lengrand.cellar.driver.DataDriver;
import nl.lengrand.cellar.driver.DataDriverProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CellarMonitor {

    private ScheduledFuture monitorHandle;

    private static final int THREADS = 1;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREADS);

    private static final long START = 0;

    @ConfigProperty(name = "monitor.time.span", defaultValue = "15")
    private long span;

    @Inject
    private TimeUnit timeUnit;

    @Inject @DataDriverProvider.SpecificDataDriver
    private DataDriver dataDriver;

    @Inject @SensorApiProvider.SpecificSensorApi
    private SensorApi sensorApi;

    final Runnable monitoring = () -> { sensorApi.add(dataDriver.getSensorValues()); };

    public void startMonitoring(){
        monitorHandle = scheduler.scheduleAtFixedRate(monitoring, START, span, timeUnit);
    }

    public void stopMonitoring(){
        monitorHandle.cancel(true);
    }

    public void updateMonitoring(){

    }
}
