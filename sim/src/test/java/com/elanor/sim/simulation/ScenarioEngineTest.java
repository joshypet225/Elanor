
package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioEngineTest {

    @Test
    void shouldDisableCoolingDuringCoolingFailure() {

        FacilityState facility = new FacilityState();
        ScenarioEngine engine = new ScenarioEngine();

        engine.apply(
                facility,
                EnvironmentalScenario.COOLING_FAILURE
        );

        assertFalse(facility.isCoolingOperational());
        assertTrue(facility.isPowerAvailable());
    }

    @Test
    void shouldDisableVentilationDuringVentilationFailure() {

        FacilityState facility = new FacilityState();
        ScenarioEngine engine = new ScenarioEngine();

        engine.apply(
                facility,
                EnvironmentalScenario.VENTILATION_FAILURE
        );

        assertFalse(facility.isVentilationOperational());
        assertTrue(facility.isCoolingOperational());
    }

    @Test
    void shouldKeepLightingOperationalDuringExcessiveLightingScenario() {

        FacilityState facility = new FacilityState();
        ScenarioEngine engine = new ScenarioEngine();

        engine.apply(
                facility,
                EnvironmentalScenario.EXCESSIVE_LIGHTING
        );

        assertTrue(facility.isLightingOperational());
    }

    @Test
    void shouldRestoreAllSystemsDuringNormalScenario() {

        FacilityState facility = new FacilityState();

        facility.setPowerAvailable(false);
        facility.setCoolingOperational(false);
        facility.setVentilationOperational(false);
        facility.setLightingOperational(false);

        ScenarioEngine engine = new ScenarioEngine();

        engine.apply(
                facility,
                EnvironmentalScenario.NORMAL
        );

        assertTrue(facility.isPowerAvailable());
        assertTrue(facility.isCoolingOperational());
        assertTrue(facility.isVentilationOperational());
        assertTrue(facility.isLightingOperational());
    }
}

