
package com.elanor.sim.telemetry;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TelemetryReadingTest {

    @Test
    void shouldStoreTelemetryReadingDetails() {

        Instant timestamp = Instant.parse("2026-11-21T12:00:00Z");

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                23.5,
                "CELSIUS",
                timestamp
        );

        assertEquals("TEMP-001", reading.sensorId());
        assertEquals("TEMPERATURE", reading.sensorType());
        assertEquals(23.5, reading.value());
        assertEquals("CELSIUS", reading.unit());
        assertEquals(timestamp, reading.timestamp());
    }

    @Test
    void shouldSupportEqualityForIdenticalReadings() {

        Instant timestamp = Instant.parse("2026-11-21T12:00:00Z");

        TelemetryReading first = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                23.5,
                "CELSIUS",
                timestamp
        );

        TelemetryReading second = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                23.5,
                "CELSIUS",
                timestamp
        );

        assertEquals(first, second);
    }
}
