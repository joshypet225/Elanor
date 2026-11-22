
package com.elanor.sim.simulation;

public class EnvironmentTargets {

    private final double temperatureCelsius;
    private final double relativeHumidity;
    private final double luminosityLux;
    private final double co2Ppm;

    public EnvironmentTargets(
            double temperatureCelsius,
            double relativeHumidity,
            double luminosityLux,
            double co2Ppm
    ) {
        this.temperatureCelsius = temperatureCelsius;
        this.relativeHumidity = relativeHumidity;
        this.luminosityLux = luminosityLux;
        this.co2Ppm = co2Ppm;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public double getRelativeHumidity() {
        return relativeHumidity;
    }

    public double getLuminosityLux() {
        return luminosityLux;
    }

    public double getCo2Ppm() {
        return co2Ppm;
    }
}
