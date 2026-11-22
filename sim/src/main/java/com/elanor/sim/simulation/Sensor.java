
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;

public interface Sensor {

    String getSensorId();

    String getSensorType();

    double read(EnvironmentState state);

    String getUnit();
}
