
package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecoveryEngineTest {

    @Test
    void shouldMoveTemperatureTowardsTarget() {

        EnvironmentState state =
                new EnvironmentState(28.0, 0.75, 100.0, 450.0);

        EnvironmentTargets targets =
                new EnvironmentTargets(23.0, 0.75, 100.0, 450.0);

        RecoveryEngine engine = new RecoveryEngine(0.2);

        engine.recover(state, targets);

        assertEquals(
                27.0,
                state.getTemperatureCelsius(),
                0.0001
        );
    }

    @Test
    void shouldMoveAllEnvironmentalReadingsTowardsTargets() {

        EnvironmentState state =
                new EnvironmentState(28.0, 0.85, 200.0, 600.0);

        EnvironmentTargets targets =
                new EnvironmentTargets(23.0, 0.75, 100.0, 450.0);

        RecoveryEngine engine = new RecoveryEngine(0.2);

        engine.recover(state, targets);

        assertTrue(state.getTemperatureCelsius() < 28.0);
        assertTrue(state.getRelativeHumidity() < 0.85);
        assertTrue(state.getLuminosityLux() < 200.0);
        assertTrue(state.getCo2Ppm() < 600.0);
    }

    @Test
    void shouldNotChangeValuesAlreadyAtTarget() {

        EnvironmentState state =
                new EnvironmentState(23.0, 0.75, 100.0, 450.0);

        EnvironmentTargets targets =
                new EnvironmentTargets(23.0, 0.75, 100.0, 450.0);

        RecoveryEngine engine = new RecoveryEngine(0.2);

        engine.recover(state, targets);

        assertEquals(23.0, state.getTemperatureCelsius());
        assertEquals(0.75, state.getRelativeHumidity());
        assertEquals(100.0, state.getLuminosityLux());
        assertEquals(450.0, state.getCo2Ppm());
    }

    @Test
    void shouldRejectInvalidRecoveryRate() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new RecoveryEngine(0.0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new RecoveryEngine(1.1)
        );
    }
}
