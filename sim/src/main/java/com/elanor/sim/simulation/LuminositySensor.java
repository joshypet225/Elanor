
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;

public class LuminositySensor implements Sensor {

    private final String sensorId;

    public LuminositySensor(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String getSensorType() {
        return "LUMINOSITY";
    }

    @Override
    public double read(EnvironmentState state) {
        return state.getLuminosityLux();
    }

    @Override
    public String getUnit() {
        return "LUX";
    }
}
