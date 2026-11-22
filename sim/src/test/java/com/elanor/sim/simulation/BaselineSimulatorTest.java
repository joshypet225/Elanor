
package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaselineSimulatorTest {

    @Test
    void shouldUpdateEnvironmentState() {
        EnvironmentState state =
                new EnvironmentState(23.0, 0.75, 100.0, 450.0);

        BaselineSimulator simulator = new BaselineSimulator(42L);

        simulator.update(state);

        assertNotEquals(23.0, state.getTemperatureCelsius());
    }

    @Test
    void simulatorsWithSameSeedShouldProduceSameResults() {
        EnvironmentState first =
                new EnvironmentState(23.0, 0.75, 100.0, 450.0);

        EnvironmentState second =
                new EnvironmentState(23.0, 0.75, 100.0, 450.0);

        BaselineSimulator simulatorOne = new BaselineSimulator(42L);
        BaselineSimulator simulatorTwo = new BaselineSimulator(42L);

        simulatorOne.update(first);
        simulatorTwo.update(second);

        assertEquals(
                first.getTemperatureCelsius(),
                second.getTemperatureCelsius()
        );

        assertEquals(
                first.getRelativeHumidity(),
                second.getRelativeHumidity()
        );
    }

    @Test
    void humidityShouldRemainWithinValidBounds() {
        EnvironmentState state =
                new EnvironmentState(23.0, 0.999, 100.0, 450.0);

        BaselineSimulator simulator = new BaselineSimulator(42L);

        for (int i = 0; i < 1000; i++) {
            simulator.update(state);
        }

        assertTrue(state.getRelativeHumidity() >= 0.0);
        assertTrue(state.getRelativeHumidity() <= 1.0);
    }
}
