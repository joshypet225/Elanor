
package com.elanor.sim.simulation;

import com.elanor.sim.telemetry.ScenarioMetrics;

public class EnvironmentEngine {
    private final ScenarioMetrics scenarioMetrics;
    private final EnvironmentState state;
    private final FacilityState facility;
    private final EnvironmentTargets targets;
    private final BaselineSimulator baselineSimulator;
    private final ScenarioEngine scenarioEngine;
    private final RecoveryEngine recoveryEngine;

    private EnvironmentalScenario activeScenario =
            EnvironmentalScenario.NORMAL;

    public EnvironmentEngine(
            EnvironmentState state,
            FacilityState facility,
            EnvironmentTargets targets,
            BaselineSimulator baselineSimulator,
            ScenarioEngine scenarioEngine,
            RecoveryEngine recoveryEngine,
	    ScenarioMetrics scenarioMetrics
    ) {
        this.state = state;
        this.facility = facility;
        this.targets = targets;
        this.baselineSimulator = baselineSimulator;
        this.scenarioEngine = scenarioEngine;
        this.recoveryEngine = recoveryEngine;
        this.scenarioMetrics = scenarioMetrics;
    }

    public void simulateCycle() {

        if (activeScenario == EnvironmentalScenario.RECOVERY) {

            recoveryEngine.recover(state, targets);
            return;
        }

        baselineSimulator.update(state);

        applyEnvironmentalEffects();
    }

    private void applyEnvironmentalEffects() {

        if (!facility.isCoolingOperational()) {
            state.setTemperatureCelsius(
                    state.getTemperatureCelsius() + 0.15
            );
        }

        if (!facility.isVentilationOperational()) {

            state.setRelativeHumidity(
                    Math.min(
                            1.0,
                            state.getRelativeHumidity() + 0.005
                    )
            );

            state.setCo2Ppm(
                    state.getCo2Ppm() + 10.0
            );
        }

        if (activeScenario == EnvironmentalScenario.EXCESSIVE_LIGHTING) {
            state.setLuminosityLux(
                    state.getLuminosityLux() + 25.0
            );
        }
    }

    public void setActiveScenario(EnvironmentalScenario scenario) {

        this.activeScenario = scenario;
        scenarioEngine.apply(facility, scenario);
        scenarioMetrics.recordScenario(scenario);
    }

    public EnvironmentState getCurrentState() {
        return state;
    }

    public FacilityState getFacilityState() {
        return facility;
    }

    public EnvironmentalScenario getActiveScenario() {
        return activeScenario;
    }
}
