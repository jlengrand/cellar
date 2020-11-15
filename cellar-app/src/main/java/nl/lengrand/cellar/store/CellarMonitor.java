package nl.lengrand.cellar.store;

import nl.lengrand.cellar.driver.DataDriver;
import nl.lengrand.cellar.driver.DataDriverProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CellarMonitor {

    private static final int THREADS = 1;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREADS);

    private static final long START = 0;
    private static final long SPAN = 30;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    private ScheduledFuture monitorHandle;

    @Inject @DataDriverProvider.SpecificDataDriver
    private DataDriver dataDriver;

    @Inject @SensorApiProvider.SpecificSensorApi
    private SensorApi sensorApi;

    final Runnable monitoring = () -> { sensorApi.add(dataDriver.getSensorValues()); };

    public void startMonitoring(){
        monitorHandle = scheduler.scheduleAtFixedRate(monitoring, START, SPAN, UNIT);
    }

    public void stopMonitoring(){
        monitorHandle.cancel(true);
    }

    public void updateMonitoring(){

    }
}
