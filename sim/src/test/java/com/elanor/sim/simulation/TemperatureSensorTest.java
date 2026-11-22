
package com.elanor.sim.sensor;

import com.elanor.sim.simulation.EnvironmentState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorTest {

    @Test
    void shouldReturnTemperatureFromEnvironmentState() {

        EnvironmentState state =
                new EnvironmentState(23.5, 0.75, 100.0, 450.0);

        TemperatureSensor sensor =
                new TemperatureSensor("TEMP-001");

        assertEquals(23.5, sensor.read(state));
    }

    @Test
    void shouldReturnCorrectSensorId() {

        TemperatureSensor sensor =
                new TemperatureSensor("TEMP-001");

        assertEquals("TEMP-001", sensor.getSensorId());
    }

    @Test
    void shouldReturnCorrectSensorType() {

        TemperatureSensor sensor =
                new TemperatureSensor("TEMP-001");

        assertEquals("TEMPERATURE", sensor.getSensorType());
    }

    @Test
    void shouldReturnCelsiusAsUnit() {

        TemperatureSensor sensor =
                new TemperatureSensor("TEMP-001");

        assertEquals("CELSIUS", sensor.getUnit());
    }
}

