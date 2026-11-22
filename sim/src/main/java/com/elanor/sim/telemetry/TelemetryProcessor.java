
package com.elanor.sim.telemetry;

import com.elanor.sim.sensor.Sensor;
import com.elanor.sim.simulation.EnvironmentState;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class TelemetryProcessor {

    private final List<Sensor> sensors;
    private final Clock clock;
    private final TelemetryValidator validator;
    private final TelemetryMetrics metrics;

    public TelemetryProcessor(
            List<Sensor> sensors,
            Clock clock,
            TelemetryValidator validator,
            TelemetryMetrics metrics
    ) {
        this.sensors = List.copyOf(sensors);
        this.clock = clock;
        this.validator = validator;
        this.metrics = metrics;
    }

    public List<TelemetryReading> collect(EnvironmentState state) {

        List<TelemetryReading> validReadings = new ArrayList<>();

        var timestamp = clock.instant();

        for (Sensor sensor : sensors) {

            double value = sensor.read(state);

            TelemetryReading reading = new TelemetryReading(
                    sensor.getSensorId(),
                    sensor.getSensorType(),
                    value,
                    sensor.getUnit(),
                    timestamp
            );

            if (validator.isValid(reading)) {

                validReadings.add(reading);
                metrics.record(reading);

            } else {

                metrics.recordInvalidReading();
            }
        }

        return validReadings;
    }
}

