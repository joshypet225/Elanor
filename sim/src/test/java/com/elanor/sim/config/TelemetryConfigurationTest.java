
package com.elanor.sim.config;

import com.elanor.sim.telemetry.TelemetryMetrics;
import com.elanor.sim.telemetry.TelemetryProcessor;
import com.elanor.sim.telemetry.TelemetryValidator;

import io.micrometer.core.instrument.MeterRegistry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TelemetryConfigurationTest {

    @Autowired
    private TelemetryProcessor processor;

    @Autowired
    private TelemetryValidator validator;

    @Autowired
    private TelemetryMetrics metrics;

    @Autowired
    private MeterRegistry registry;

    @Test
    void shouldCreateTelemetryBeans() {

        assertNotNull(processor);
        assertNotNull(validator);
        assertNotNull(metrics);
        assertNotNull(registry);
    }

    @Test
    void shouldRegisterTelemetryGauges() {

        assertNotNull(
                registry.find(
                        "agrosense.storage.temperature.celsius"
                ).gauge()
        );

        assertNotNull(
                registry.find(
                        "agrosense.storage.relative.humidity.ratio"
                ).gauge()
        );

        assertNotNull(
                registry.find(
                        "agrosense.storage.luminosity.lux"
                ).gauge()
        );

        assertNotNull(
                registry.find(
                        "agrosense.storage.co2.ppm"
                ).gauge()
        );
    }
}

