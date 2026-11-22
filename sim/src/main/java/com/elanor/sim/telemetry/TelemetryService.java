
package com.elanor.sim.telemetry;

import com.elanor.sim.simulation.EnvironmentState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelemetryService {

    private final TelemetryProcessor telemetryProcessor;

    public TelemetryService(TelemetryProcessor telemetryProcessor) {
        this.telemetryProcessor = telemetryProcessor;
    }

    public List<TelemetryReading> collect(EnvironmentState state) {
        return telemetryProcessor.collect(state);
    }
}
