
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;

public class Co2Sensor implements Sensor {

    private final String sensorId;

    public Co2Sensor(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String getSensorType() {
        return "CO2";
    }

    @Override
    public double read(EnvironmentState state) {
        return state.getCo2Ppm();
    }

    @Override
    public String getUnit() {
        return "PPM";
    }
}
