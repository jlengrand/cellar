package nl.lengrand.cellar.store;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name="cellar-point")
public class SensorValue {

    public enum READING{
        OK, ERROR
    }

    @Column(tag = true)
    private READING reading;

    @Column(name = "temperature")
    private float temperature;

    @Column(name = "humidity")
    private float humidity;

    @Column(timestamp = true)
    private Instant timestamp;

    public SensorValue(float[] values){
        this.timestamp = Instant.now();
        if(values[0] == 0 && values[1] == 0){
            this.reading = READING.ERROR;
            this.temperature = 0;
            this.humidity = 0;
        }
        else{
            this.reading = READING.OK;
            this.temperature = values[0];
            this.humidity = values[1];
        }
    }

    public READING getReading() {
        return reading;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public Instant getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "SensorValue{" +
                "reading=" + reading +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", timestamp=" + timestamp +
                '}';
    }
}
