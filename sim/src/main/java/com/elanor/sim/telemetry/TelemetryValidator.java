
package com.elanor.sim.telemetry;

import java.util.Set;

public class TelemetryValidator {

    private static final Set<String> SUPPORTED_SENSOR_TYPES =
            Set.of("TEMPERATURE", "HUMIDITY", "LUMINOSITY", "CO2");

    public boolean isValid(TelemetryReading reading) {

        if (reading == null) {
            return false;
        }

        if (reading.sensorId() == null ||
                reading.sensorId().isBlank()) {
            return false;
        }

        if (reading.sensorType() == null ||
                !SUPPORTED_SENSOR_TYPES.contains(reading.sensorType())) {
            return false;
        }

        if (reading.unit() == null ||
                reading.unit().isBlank()) {
            return false;
        }

        if (reading.timestamp() == null) {
            return false;
        }

        if (!Double.isFinite(reading.value())) {
            return false;
        }

        return switch (reading.sensorType()) {

            case "TEMPERATURE" ->
                    reading.unit().equals("CELSIUS");

            case "HUMIDITY" ->
                    reading.unit().equals("RATIO") &&
                    reading.value() >= 0.0 &&
                    reading.value() <= 1.0;

            case "LUMINOSITY" ->
                    reading.unit().equals("LUX") &&
                    reading.value() >= 0.0;

            case "CO2" ->
                    reading.unit().equals("PPM") &&
                    reading.value() >= 0.0;

            default -> false;
        };
    }
}
