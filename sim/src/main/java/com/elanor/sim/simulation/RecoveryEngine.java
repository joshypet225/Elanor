
package com.elanor.sim.simulation;

public class RecoveryEngine {

    private final double recoveryRate;

    public RecoveryEngine(double recoveryRate) {

        if (recoveryRate <= 0.0 || recoveryRate > 1.0) {
            throw new IllegalArgumentException(
                    "Recovery rate must be greater than 0 and at most 1"
            );
        }

        this.recoveryRate = recoveryRate;
    }

    public void recover(EnvironmentState state, EnvironmentTargets targets) {

        state.setTemperatureCelsius(
                moveTowards(
                        state.getTemperatureCelsius(),
                        targets.getTemperatureCelsius()
                )
        );

        state.setRelativeHumidity(
                moveTowards(
                        state.getRelativeHumidity(),
                        targets.getRelativeHumidity()
                )
        );

        state.setLuminosityLux(
                moveTowards(
                        state.getLuminosityLux(),
                        targets.getLuminosityLux()
                )
        );

        state.setCo2Ppm(
                moveTowards(
                        state.getCo2Ppm(),
                        targets.getCo2Ppm()
                )
        );
    }

    private double moveTowards(double current, double target) {

        return current + (target - current) * recoveryRate;
    }
}
