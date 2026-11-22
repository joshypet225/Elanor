
package com.elanor.sim.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SimulationSchedulerTest {

    private SimulationService simulationService;
    private SimulationScheduler simulationScheduler;

    @BeforeEach
    void setUp() {
        simulationService = mock(SimulationService.class);
        simulationScheduler = new SimulationScheduler(simulationService);
    }

    @Test
    void shouldExecuteSimulationCycle() {

        simulationScheduler.executeSimulationCycle();

        verify(simulationService, times(1)).runCycle();
    }
}
