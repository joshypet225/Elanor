
package com.elanor.sim.simulation;

import com.elanor.sim.telemetry.TelemetryReading;
import com.elanor.sim.telemetry.TelemetryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SimulationServiceTest {

    private EnvironmentEngine environmentEngine;
    private TelemetryService telemetryService;
    private SimulationService simulationService;

    @BeforeEach
    void setUp() {
        environmentEngine = mock(EnvironmentEngine.class);
        telemetryService = mock(TelemetryService.class);

        simulationService = new SimulationService(
                environmentEngine,
                telemetryService
        );
    }

    @Test
    void shouldRunSimulationAndCollectTelemetry() {

        EnvironmentState state = new EnvironmentState(
                12.0, 0.85, 100.0, 400.0
        );

        List<TelemetryReading> readings = List.of(
                new TelemetryReading(
                        "TEMP-001", "TEMPERATURE",
                        12.0, "CELSIUS", Instant.now()
                ),
                new TelemetryReading(
                        "HUM-001", "HUMIDITY",
                        0.85, "RATIO", Instant.now()
                ),
                new TelemetryReading(
                        "LUX-001", "LUMINOSITY",
                        100.0, "LUX", Instant.now()
                ),
                new TelemetryReading(
                        "CO2-001", "CO2",
                        400.0, "PPM", Instant.now()
                )
        );

        when(environmentEngine.getCurrentState()).thenReturn(state);
        when(telemetryService.collect(state)).thenReturn(readings);

        List<TelemetryReading> result = simulationService.runCycle();

        verify(environmentEngine, times(1)).simulateCycle();
        verify(telemetryService, times(1)).collect(state);

        assertNotNull(result);
        assertEquals(4, result.size());
    }
}
