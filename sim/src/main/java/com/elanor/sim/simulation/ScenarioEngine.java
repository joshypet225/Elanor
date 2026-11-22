
package com.elanor.sim.simulation;

public class ScenarioEngine {

    public void apply(
            FacilityState facility,
            EnvironmentalScenario scenario
    ) {

        switch (scenario) {

            case NORMAL, RECOVERY -> {
                facility.setPowerAvailable(true);
                facility.setCoolingOperational(true);
                facility.setVentilationOperational(true);
                facility.setLightingOperational(true);
            }

            case COOLING_FAILURE -> {
                facility.setCoolingOperational(false);
            }

            case VENTILATION_FAILURE -> {
                facility.setVentilationOperational(false);
            }

            case EXCESSIVE_LIGHTING -> {
                    // Equipment remains operational.
    		   // The environmental engine will model excessive light.
            }

            case POWER_OUTAGE -> {
                facility.setPowerAvailable(false);
                facility.setCoolingOperational(false);
                facility.setVentilationOperational(false);
                facility.setLightingOperational(false);
            }
        }
    }
}
