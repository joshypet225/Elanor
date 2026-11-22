
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;

public class HumiditySensor implements Sensor {

    private final String sensorId;

    public HumiditySensor(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String getSensorType() {
        return "HUMIDITY";
    }

    @Override
    public double read(EnvironmentState state) {
        return state.getRelativeHumidity();
    }

    @Override
    public String getUnit() {
        return "RATIO";
    }
}

