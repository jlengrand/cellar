package nl.lengrand.cellar;

import nl.lengrand.cellar.faunadb.SensorApi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CellarMonitor {

    private static final int THREADS = 1;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREADS);

    private static final long START = 0;
    private static final long SPAN = 30;
    private static final TimeUnit UNIT = TimeUnit.MINUTES;

    private ScheduledFuture monitorHandle;

    private CellarProvider provider = new CellarProvider();
    private SensorApi faunaApi = new SensorApi();

    final Runnable monitoring = () -> { faunaApi.add(provider.getSensorValues()); };

    public void startMonitoring(){
        monitorHandle = scheduler.scheduleAtFixedRate(monitoring, START, SPAN, UNIT);
    }

    public void stopMonitoring(){
        monitorHandle.cancel(true);
    }

    public void updateMonitoring(){

    }
}
