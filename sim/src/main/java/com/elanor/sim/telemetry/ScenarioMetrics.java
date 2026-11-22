
package com.elanor.sim.telemetry;

import com.elanor.sim.simulation.EnvironmentalScenario;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.atomic.AtomicReference;

public class ScenarioMetrics {

    private final AtomicReference<EnvironmentalScenario> activeScenario =
            new AtomicReference<>(EnvironmentalScenario.NORMAL);

    public ScenarioMetrics(MeterRegistry registry) {
        for (EnvironmentalScenario scenario : EnvironmentalScenario.values()) {
            Gauge.builder(
                    "agrosense.simulation.scenario",
                    activeScenario,
                    current -> current.get() == scenario ? 1.0 : 0.0
            )
            .tag("scenario", scenario.name())
            .register(registry);
        }
    }

    public void recordScenario(EnvironmentalScenario scenario) {
        activeScenario.set(scenario);
    }
}
