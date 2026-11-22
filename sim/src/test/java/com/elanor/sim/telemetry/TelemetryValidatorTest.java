
package com.elanor.sim.telemetry;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TelemetryValidatorTest {

    private final TelemetryValidator validator =
            new TelemetryValidator();

    private final Instant timestamp =
            Instant.parse("2026-11-21T12:00:00Z");

    @Test
    void shouldAcceptValidTemperatureReading() {

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                40.0,
                "CELSIUS",
                timestamp
        );

        assertTrue(validator.isValid(reading));
    }

    @Test
    void shouldAcceptValidHumidityReading() {

        TelemetryReading reading = new TelemetryReading(
                "HUM-001",
                "HUMIDITY",
                0.75,
                "RATIO",
                timestamp
        );

        assertTrue(validator.isValid(reading));
    }

    @Test
    void shouldRejectHumidityAboveOne() {

        TelemetryReading reading = new TelemetryReading(
                "HUM-001",
                "HUMIDITY",
                1.5,
                "RATIO",
                timestamp
        );

        assertFalse(validator.isValid(reading));
    }

    @Test
    void shouldRejectNegativeLuminosity() {

        TelemetryReading reading = new TelemetryReading(
                "LUX-001",
                "LUMINOSITY",
                -10.0,
                "LUX",
                timestamp
        );

        assertFalse(validator.isValid(reading));
    }

    @Test
    void shouldRejectNegativeCo2Reading() {

        TelemetryReading reading = new TelemetryReading(
                "CO2-001",
                "CO2",
                -100.0,
                "PPM",
                timestamp
        );

        assertFalse(validator.isValid(reading));
    }

    @Test
    void shouldRejectNonFiniteValues() {

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                Double.NaN,
                "CELSIUS",
                timestamp
        );

        assertFalse(validator.isValid(reading));
    }

    @Test
    void shouldRejectMissingTimestamp() {

        TelemetryReading reading = new TelemetryReading(
                "TEMP-001",
                "TEMPERATURE",
                25.0,
                "CELSIUS",
                null
        );

        assertFalse(validator.isValid(reading));
    }

    @Test
    void shouldRejectUnsupportedSensorType() {

        TelemetryReading reading = new TelemetryReading(
                "PRESS-001",
                "PRESSURE",
                100.0,
                "PASCAL",
                timestamp
        );

        assertFalse(validator.isValid(reading));
    }
}
