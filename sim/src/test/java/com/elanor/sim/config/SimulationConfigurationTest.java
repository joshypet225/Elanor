
package com.elanor.sim.config;

import com.elanor.sim.simulation.EnvironmentEngine;
import com.elanor.sim.simulation.EnvironmentState;
import com.elanor.sim.simulation.EnvironmentTargets;
import com.elanor.sim.simulation.FacilityState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SimulationConfigurationTest {

    @Autowired
    private EnvironmentEngine environmentEngine;

    @Autowired
    private EnvironmentState environmentState;

    @Autowired
    private FacilityState facilityState;

    @Autowired
    private EnvironmentTargets environmentTargets;

    @Test
    void shouldCreateSimulationBeans() {
        assertNotNull(environmentEngine);
        assertNotNull(environmentState);
        assertNotNull(facilityState);
        assertNotNull(environmentTargets);
    }

    @Test
    void shouldRunSimulationCycle() {
        environmentEngine.simulateCycle();

        assertNotNull(environmentEngine.getCurrentState());
    }
}
