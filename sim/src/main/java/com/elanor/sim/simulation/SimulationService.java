
package com.elanor.sim.simulation;

import com.elanor.sim.telemetry.TelemetryReading;
import com.elanor.sim.telemetry.TelemetryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {

    private final EnvironmentEngine environmentEngine;
    private final TelemetryService telemetryService;

    public SimulationService(
            EnvironmentEngine environmentEngine,
            TelemetryService telemetryService
    ) {
        this.environmentEngine = environmentEngine;
        this.telemetryService = telemetryService;
    }

    public List<TelemetryReading> runCycle() {

        // 1. Update the simulated storage environment
        environmentEngine.simulateCycle();

        // 2. Retrieve the latest environmental state
        EnvironmentState currentState =
                environmentEngine.getCurrentState();

        // 3. Collect and return telemetry readings
        return telemetryService.collect(currentState);
    }
}
