
package com.elanor.sim.simulation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimulationScheduler {

    private final SimulationService simulationService;

    public SimulationScheduler(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Scheduled(
            fixedRateString = "${agrosense.simulation.interval-ms:5000}"
    )
    public void executeSimulationCycle() {
        simulationService.runCycle();
    }
}
