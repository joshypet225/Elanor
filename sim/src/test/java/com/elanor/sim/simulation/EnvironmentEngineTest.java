package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;
import com.elanor.sim.telemetry.ScenarioMetrics;
import static org.junit.jupiter.api.Assertions.*;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

class EnvironmentEngineTest {

    
private EnvironmentEngine createEngine() {

    EnvironmentState state =
            new EnvironmentState(23.0, 0.75, 100.0, 450.0);

    FacilityState facility = new FacilityState();

    EnvironmentTargets targets =
            new EnvironmentTargets(23.0, 0.75, 100.0, 450.0);

    BaselineSimulator baselineSimulator =
            new BaselineSimulator(42L);

    ScenarioEngine scenarioEngine = new ScenarioEngine();

    RecoveryEngine recoveryEngine =
            new RecoveryEngine(0.2);

    ScenarioMetrics scenarioMetrics = new ScenarioMetrics(new SimpleMeterRegistry());

    return new EnvironmentEngine(
            state,
            facility,
            targets,
            baselineSimulator,
            scenarioEngine,
            recoveryEngine,
            scenarioMetrics
    );
}

    @Test
    void shouldStartWithNormalScenario() {

        EnvironmentEngine engine = createEngine();

        assertEquals(
                EnvironmentalScenario.NORMAL,
                engine.getActiveScenario()
        );
    }

    @Test
    void shouldChangeActiveScenario() {

        EnvironmentEngine engine = createEngine();

        engine.setActiveScenario(
                EnvironmentalScenario.COOLING_FAILURE
        );

        assertEquals(
                EnvironmentalScenario.COOLING_FAILURE,
                engine.getActiveScenario()
        );
    }

    @Test
    void shouldApplyBaselineAndCoolingFailure() {

        EnvironmentEngine engine = createEngine();

        double initialTemperature =
                engine.getCurrentState().getTemperatureCelsius();

        engine.setActiveScenario(
                EnvironmentalScenario.COOLING_FAILURE
        );

        engine.simulateCycle();

        assertTrue(
                engine.getCurrentState().getTemperatureCelsius()
                        > initialTemperature
        );
    }

    @Test
    void shouldPreserveSameEnvironmentStateObject() {

        EnvironmentEngine engine = createEngine();

        EnvironmentState initialState = engine.getCurrentState();

        engine.simulateCycle();

        assertSame(initialState, engine.getCurrentState());
    }
   
@Test
void shouldDisableCoolingWhenCoolingFailureIsActivated() {

    EnvironmentEngine engine = createEngine();

    engine.setActiveScenario(
            EnvironmentalScenario.COOLING_FAILURE
    );

    assertFalse(
            engine.getFacilityState().isCoolingOperational()
    );
}

@Test
void shouldIncreaseTemperatureWhenCoolingIsUnavailable() {

    EnvironmentEngine engine = createEngine();

    double initialTemperature =
            engine.getCurrentState().getTemperatureCelsius();

    engine.setActiveScenario(
            EnvironmentalScenario.COOLING_FAILURE
    );

    engine.simulateCycle();

    assertTrue(
            engine.getCurrentState().getTemperatureCelsius()
                    > initialTemperature
    );
}

@Test
void shouldDisableFacilitySystemsDuringPowerOutage() {

    EnvironmentEngine engine = createEngine();

    engine.setActiveScenario(
            EnvironmentalScenario.POWER_OUTAGE
    );

    assertFalse(engine.getFacilityState().isPowerAvailable());
    assertFalse(engine.getFacilityState().isCoolingOperational());
    assertFalse(engine.getFacilityState().isVentilationOperational());
    assertFalse(engine.getFacilityState().isLightingOperational());
}
   
@Test
void shouldGraduallyRecoverTemperature() {

    EnvironmentEngine engine = createEngine();

    engine.getCurrentState().setTemperatureCelsius(28.0);

    engine.setActiveScenario(EnvironmentalScenario.RECOVERY);

    engine.simulateCycle();

    assertEquals(
            27.0,
            engine.getCurrentState().getTemperatureCelsius(),
            0.0001
    );
}

@Test
void shouldRestoreFacilitySystemsDuringRecovery() {

    EnvironmentEngine engine = createEngine();

    engine.setActiveScenario(EnvironmentalScenario.POWER_OUTAGE);

    engine.setActiveScenario(EnvironmentalScenario.RECOVERY);

    assertTrue(engine.getFacilityState().isPowerAvailable());
    assertTrue(engine.getFacilityState().isCoolingOperational());
    assertTrue(engine.getFacilityState().isVentilationOperational());
    assertTrue(engine.getFacilityState().isLightingOperational());
}

@Test
void shouldIncreaseLuminosityDuringExcessiveLighting() {

    EnvironmentEngine engine = createEngine();

    double initialLuminosity =
            engine.getCurrentState().getLuminosityLux();

    engine.setActiveScenario(
            EnvironmentalScenario.EXCESSIVE_LIGHTING
    );

    engine.simulateCycle();

    assertTrue(
            engine.getCurrentState().getLuminosityLux()
                    > initialLuminosity
    );
}

}
