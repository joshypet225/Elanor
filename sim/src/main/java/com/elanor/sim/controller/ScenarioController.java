
package com.elanor.sim.controller;

import com.elanor.sim.simulation.EnvironmentEngine;
import com.elanor.sim.simulation.EnvironmentalScenario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scenarios")
public class ScenarioController {

    private final EnvironmentEngine environmentEngine;

    public ScenarioController(EnvironmentEngine environmentEngine) {
        this.environmentEngine = environmentEngine;
    }

    @PostMapping("/{scenario}")
    public String activateScenario(@PathVariable EnvironmentalScenario scenario) {
        environmentEngine.setActiveScenario(scenario);

        return "Scenario activated: " + scenario;
    }
}
