package com.elanor.sim.config;

import com.elanor.sim.simulation.BaselineSimulator;
import com.elanor.sim.simulation.EnvironmentEngine;
import com.elanor.sim.simulation.EnvironmentState;
import com.elanor.sim.simulation.EnvironmentTargets;
import com.elanor.sim.simulation.FacilityState;
import com.elanor.sim.simulation.RecoveryEngine;
import com.elanor.sim.simulation.ScenarioEngine;
import com.elanor.sim.telemetry.ScenarioMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationConfiguration {

    @Bean
    public EnvironmentState environmentState() {
        return new EnvironmentState(
                12.0,  // Temperature °C
                0.85,  // Relative humidity ratio (85%)
                100.0, // Luminosity lux
                400.0  // CO2 ppm
        );
    }

    @Bean
    public FacilityState facilityState() {
        return new FacilityState();
    }

    @Bean
    public EnvironmentTargets environmentTargets() {
        return new EnvironmentTargets(
                12.0,
                0.85,
                100.0,
                400.0
        );
    }

    @Bean
    public BaselineSimulator baselineSimulator() {
        return new BaselineSimulator(System.currentTimeMillis());
    }

    @Bean
    public ScenarioEngine scenarioEngine() {
        return new ScenarioEngine();
    }

    @Bean
    public RecoveryEngine recoveryEngine() {
        return new RecoveryEngine(0.2);
    }

    @Bean
    public EnvironmentEngine environmentEngine(
            EnvironmentState environmentState,
            FacilityState facilityState,
            EnvironmentTargets environmentTargets,
            BaselineSimulator baselineSimulator,
            ScenarioEngine scenarioEngine,
            RecoveryEngine recoveryEngine,
            ScenarioMetrics scenarioMetrics
    ) {
        return new EnvironmentEngine(
                environmentState,
                facilityState,
                environmentTargets,
                baselineSimulator,
                scenarioEngine,
                recoveryEngine,
                scenarioMetrics
        );
    }
}
