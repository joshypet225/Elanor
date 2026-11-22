
package com.elanor.sim.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacilityStateTest {

    @Test
    void shouldStartWithAllSystemsOperational() {

        FacilityState facility = new FacilityState();

        assertTrue(facility.isPowerAvailable());
        assertTrue(facility.isCoolingOperational());
        assertTrue(facility.isVentilationOperational());
        assertTrue(facility.isLightingOperational());
    }

    @Test
    void shouldAllowPowerToBeDisabled() {

        FacilityState facility = new FacilityState();

        facility.setPowerAvailable(false);

        assertFalse(facility.isPowerAvailable());
    }

    @Test
    void shouldAllowCoolingToFailIndependently() {

        FacilityState facility = new FacilityState();

        facility.setCoolingOperational(false);

        assertFalse(facility.isCoolingOperational());
        assertTrue(facility.isVentilationOperational());
        assertTrue(facility.isLightingOperational());
    }

    @Test
    void shouldAllowSystemsToRecoverIndependently() {

        FacilityState facility = new FacilityState();

        facility.setCoolingOperational(false);
        facility.setCoolingOperational(true);

        assertTrue(facility.isCoolingOperational());
    }
}
