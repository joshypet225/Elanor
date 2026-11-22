
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;

public class TemperatureSensor implements Sensor {

    private final String sensorId;

    public TemperatureSensor(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String getSensorId() {
        return sensorId;
    }

    @Override
    public String getSensorType() {
        return "TEMPERATURE";
    }

    @Override
    public double read(EnvironmentState state) {
        return state.getTemperatureCelsius();
    }

    @Override
    public String getUnit() {
        return "CELSIUS";
    }
}
