
package com.elanor.sim.config;

import com.elanor.sim.sensor.Co2Sensor;
import com.elanor.sim.sensor.HumiditySensor;
import com.elanor.sim.sensor.LuminositySensor;
import com.elanor.sim.sensor.Sensor;
import com.elanor.sim.sensor.TemperatureSensor;
import com.elanor.sim.telemetry.TelemetryMetrics;
import com.elanor.sim.telemetry.TelemetryProcessor;
import com.elanor.sim.telemetry.TelemetryValidator;
import com.elanor.sim.telemetry.ScenarioMetrics;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.List;

@Configuration
public class TelemetryConfiguration {

    @Bean
    public Clock telemetryClock() {
        return Clock.systemUTC();
    }

    @Bean
    public TelemetryValidator telemetryValidator() {
        return new TelemetryValidator();
    }

    @Bean
    public List<Sensor> sensors() {

        return List.of(
                new TemperatureSensor("TEMP-001"),
                new HumiditySensor("HUM-001"),
                new LuminositySensor("LUX-001"),
                new Co2Sensor("CO2-001")
        );
    }

    @Bean
    public TelemetryMetrics telemetryMetrics(
            MeterRegistry registry
    ) {
        return new TelemetryMetrics(registry);
    }

    @Bean
    public TelemetryProcessor telemetryProcessor(
            List<Sensor> sensors,
            Clock telemetryClock,
            TelemetryValidator validator,
            TelemetryMetrics metrics
    ) {
        return new TelemetryProcessor(
                sensors,
                telemetryClock,
                validator,
                metrics
        );
    }
    @Bean
    public ScenarioMetrics scenarioMetrics(MeterRegistry meterRegistry) {
       return new ScenarioMetrics(meterRegistry);
    }
}
