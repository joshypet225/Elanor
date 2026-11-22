
package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTargetsTest {

    @Test
    void shouldStoreConfiguredEnvironmentalTargets() {

        EnvironmentTargets targets =
                new EnvironmentTargets(23.0, 0.75, 100.0, 450.0);

        assertEquals(23.0, targets.getTemperatureCelsius());
        assertEquals(0.75, targets.getRelativeHumidity());
        assertEquals(100.0, targets.getLuminosityLux());
        assertEquals(450.0, targets.getCo2Ppm());
    }
}
