package nl.lengrand;

import nl.lengrand.faunadb.SensorApi;

import java.util.concurrent.*;

public class CellarMonitor {

    private static final int THREADS = 1;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREADS);

    // From config
    private static final long START = 0;
    private static final long SPAN = 3;
    private static final TimeUnit UNIT = TimeUnit.MINUTES;

    private ScheduledFuture monitorHandle;

    // How to use that exactly?
    private CellarProvider provider = new CellarProvider();
    private SensorApi faunaApi = new SensorApi();

    final Runnable monitoring = () -> {
        try {
            faunaApi.add(provider.getSensorValues());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error add fauna data");
            System.exit(0);
        }
    };

    public void startMonitoring(){
        monitorHandle = scheduler.scheduleAtFixedRate(monitoring, START, SPAN, TimeUnit.MINUTES);
    }

    public void stopMonitoring(){
        monitorHandle.cancel(true);
    }

    public void updateMonitoring(){

    }
}
