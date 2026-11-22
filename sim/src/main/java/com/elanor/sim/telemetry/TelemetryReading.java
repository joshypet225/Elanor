
package com.elanor.sim.telemetry;

import java.time.Instant;

public record TelemetryReading(
        String sensorId,
        String sensorType,
        double value,
        String unit,
        Instant timestamp
) {
}
