
package com.elanor.sim.simulation;

public class FacilityState {

    private boolean powerAvailable = true;
    private boolean coolingOperational = true;
    private boolean ventilationOperational = true;
    private boolean lightingOperational = true;

    public boolean isPowerAvailable() {
        return powerAvailable;
    }

    public void setPowerAvailable(boolean powerAvailable) {
        this.powerAvailable = powerAvailable;
    }

    public boolean isCoolingOperational() {
        return coolingOperational;
    }

    public void setCoolingOperational(boolean coolingOperational) {
        this.coolingOperational = coolingOperational;
    }

    public boolean isVentilationOperational() {
        return ventilationOperational;
    }

    public void setVentilationOperational(boolean ventilationOperational) {
        this.ventilationOperational = ventilationOperational;
    }

    public boolean isLightingOperational() {
        return lightingOperational;
    }

    public void setLightingOperational(boolean lightingOperational) {
        this.lightingOperational = lightingOperational;
    }
}

