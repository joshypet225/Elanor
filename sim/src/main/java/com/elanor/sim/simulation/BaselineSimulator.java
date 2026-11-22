
package com.elanor.sim.simulation;

import java.util.Random;

public class BaselineSimulator {

    private final Random random;

    public BaselineSimulator(long seed) {
        this.random = new Random(seed);
    }

    public void update(EnvironmentState state) {

        // Generate small environmental fluctuations
        double temperatureChange = random.nextDouble(-0.10, 0.10);
        double humidityChange = random.nextDouble(-0.005, 0.005);
        double luminosityChange = random.nextDouble(-2.0, 2.0);
        double co2Change = random.nextDouble(-5.0, 5.0);

        // Apply changes to the existing environment state
        state.setTemperatureCelsius(
                state.getTemperatureCelsius() + temperatureChange
        );

        state.setRelativeHumidity(
                clamp(state.getRelativeHumidity() + humidityChange, 0.0, 1.0)
        );

        state.setLuminosityLux(
                Math.max(0.0, state.getLuminosityLux() + luminosityChange)
        );

        state.setCo2Ppm(
                Math.max(0.0, state.getCo2Ppm() + co2Change)
        );
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
