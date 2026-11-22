
package com.elanor.sim.telemetry;

import com.elanor.sim.sensor.*;
import com.elanor.sim.simulation.EnvironmentState;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TelemetryProcessorTest {

    private final Instant expectedTimestamp =
            Instant.parse("2026-11-21T12:00:00Z");

    private final Clock fixedClock =
            Clock.fixed(expectedTimestamp, ZoneOffset.UTC);

    private final TelemetryValidator validator =
            new TelemetryValidator();

    @Test
    void shouldCollectAndPublishValidReadings() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        EnvironmentState state = new EnvironmentState(
                23.5, 0.75, 120.0, 450.0
        );

        List<Sensor> sensors = List.of(
                new TemperatureSensor("TEMP-001"),
                new HumiditySensor("HUM-001"),
                new LuminositySensor("LUX-001"),
                new Co2Sensor("CO2-001")
        );

        TelemetryProcessor processor = new TelemetryProcessor(
                sensors, fixedClock, validator, metrics
        );

        List<TelemetryReading> readings = processor.collect(state);

        assertEquals(4, readings.size());

        assertEquals(
                23.5,
                registry.get("agrosense.storage.temperature.celsius")
                        .gauge().value()
        );

        assertEquals(
                0.75,
                registry.get("agrosense.storage.relative.humidity.ratio")
                        .gauge().value()
        );

        assertEquals(
                4.0,
                registry.get("agrosense.telemetry.readings")
                        .counter().count()
        );

        assertEquals(
                0.0,
                registry.get("agrosense.telemetry.invalid")
                        .counter().count()
        );

        registry.close();
    }

    @Test
    void shouldRejectInvalidReadingsWithoutPublishingThem() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        EnvironmentState state = new EnvironmentState(
                23.5, 1.5, 120.0, 450.0
        );

        List<Sensor> sensors = List.of(
                new TemperatureSensor("TEMP-001"),
                new HumiditySensor("HUM-001"),
                new LuminositySensor("LUX-001"),
                new Co2Sensor("CO2-001")
        );

        TelemetryProcessor processor = new TelemetryProcessor(
                sensors, fixedClock, validator, metrics
        );

        List<TelemetryReading> readings = processor.collect(state);

        assertEquals(3, readings.size());

        assertEquals(
                3.0,
                registry.get("agrosense.telemetry.readings")
                        .counter().count()
        );

        assertEquals(
                1.0,
                registry.get("agrosense.telemetry.invalid")
                        .counter().count()
        );

        assertEquals(
                0.0,
                registry.get("agrosense.storage.relative.humidity.ratio")
                        .gauge().value()
        );

        registry.close();
    }

    @Test
    void shouldAccumulateMetricsAcrossCollectionCycles() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        EnvironmentState state = new EnvironmentState(
                23.5, 0.75, 120.0, 450.0
        );

        List<Sensor> sensors = List.of(
                new TemperatureSensor("TEMP-001"),
                new HumiditySensor("HUM-001")
        );

        TelemetryProcessor processor = new TelemetryProcessor(
                sensors, fixedClock, validator, metrics
        );

        processor.collect(state);
        processor.collect(state);

        assertEquals(
                4.0,
                registry.get("agrosense.telemetry.readings")
                        .counter().count()
        );

        registry.close();
    }

    @Test
    void shouldReturnEmptyListWhenNoSensorsAreRegistered() {

        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        TelemetryMetrics metrics = new TelemetryMetrics(registry);

        EnvironmentState state = new EnvironmentState(
                23.5, 0.75, 120.0, 450.0
        );

        TelemetryProcessor processor = new TelemetryProcessor(
                List.of(), fixedClock, validator, metrics
        );

        List<TelemetryReading> readings = processor.collect(state);

        assertTrue(readings.isEmpty());

        assertEquals(
                0.0,
                registry.get("agrosense.telemetry.readings")
                        .counter().count()
        );

        registry.close();
    }
}

