
package com.elanor.sim.telemetry;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TelemetryMetricsTest {

    @Test
    void shouldRegisterEnvironmentalGauges() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                25.5,
                "CELSIUS",
                Instant.now()
        );

        metrics.record(reading);

        assertEquals(
                25.5,
                registry.get("agrosense.storage.temperature.celsius")
                        .gauge()
                        .value()
        );

        registry.close();
    }

    @Test
    void shouldIncrementValidReadingsCounter() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                25.5,
                "CELSIUS",
                Instant.now()
        );

        metrics.record(reading);
        metrics.record(reading);

        assertEquals(
                2.0,
                registry.get("agrosense.telemetry.readings")
                        .counter()
                        .count()
        );

        registry.close();
    }

    @Test
    void shouldIncrementInvalidReadingsCounter() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        metrics.recordInvalidReading();

        assertEquals(
                1.0,
                registry.get("agrosense.telemetry.invalid")
                        .counter()
                        .count()
        );

        registry.close();
    }
}

