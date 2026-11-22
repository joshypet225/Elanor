
package com.elanor.sim.simulation;

public class EnvironmentState {

    private double temperatureCelsius;
    private double relativeHumidity;
    private double luminosityLux;
    private double co2Ppm;

    public EnvironmentState(
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

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public double getLuminosityLux() {
        return luminosityLux;
    }

    public void setLuminosityLux(double luminosityLux) {
        this.luminosityLux = luminosityLux;
    }

    public double getCo2Ppm() {
        return co2Ppm;
    }

    public void setCo2Ppm(double co2Ppm) {
        this.co2Ppm = co2Ppm;
    }
}
