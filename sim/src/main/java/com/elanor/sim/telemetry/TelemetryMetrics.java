
package com.elanor.sim.telemetry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.atomic.AtomicReference;

public class TelemetryMetrics {

    private final AtomicReference<Double> temperature =
            new AtomicReference<>(0.0);

    private final AtomicReference<Double> humidity =
            new AtomicReference<>(0.0);

    private final AtomicReference<Double> luminosity =
            new AtomicReference<>(0.0);

    private final AtomicReference<Double> co2 =
            new AtomicReference<>(0.0);

    private final Counter validReadingsCounter;
    private final Counter invalidReadingsCounter;

    public TelemetryMetrics(MeterRegistry registry) {

        Gauge.builder(
                "agrosense.storage.temperature.celsius",
                temperature,
                AtomicReference::get
        ).register(registry);

        Gauge.builder(
                "agrosense.storage.relative.humidity.ratio",
                humidity,
                AtomicReference::get
        ).register(registry);

        Gauge.builder(
                "agrosense.storage.luminosity.lux",
                luminosity,
                AtomicReference::get
        ).register(registry);

        Gauge.builder(
                "agrosense.storage.co2.ppm",
                co2,
                AtomicReference::get
        ).register(registry);

        this.validReadingsCounter = Counter.builder(
                "agrosense.telemetry.readings"
        ).register(registry);

        this.invalidReadingsCounter = Counter.builder(
                "agrosense.telemetry.invalid"
        ).register(registry);
    }

    public void record(TelemetryReading reading) {

        switch (reading.sensorType()) {

            case "TEMPERATURE" ->
                    temperature.set(reading.value());

            case "HUMIDITY" ->
                    humidity.set(reading.value());

            case "LUMINOSITY" ->
                    luminosity.set(reading.value());

            case "CO2" ->
                    co2.set(reading.value());

            default -> throw new IllegalArgumentException(
                    "Unsupported sensor type: " + reading.sensorType()
            );
        }

        validReadingsCounter.increment();
    }

    public void recordInvalidReading() {
        invalidReadingsCounter.increment();
    }

    public double getTemperature() {
        return temperature.get();
    }

    public double getHumidity() {
        return humidity.get();
    }

    public double getLuminosity() {
        return luminosity.get();
    }

    public double getCo2() {
        return co2.get();
    }
}

