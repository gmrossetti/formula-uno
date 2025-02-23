package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;

public class LeaderboardEntry {
    private final CarDriver carDriver;
    private final boolean disqualified;
    private final String disqualificationReason;

    public LeaderboardEntry(CarDriver carDriver) {
        this.carDriver = carDriver;
        this.disqualified = false;
        this.disqualificationReason = null;
    }

    public LeaderboardEntry(CarDriver carDriver, String disqualificationReason) {
        this.carDriver = carDriver;
        this.disqualified = true;
        this.disqualificationReason = disqualificationReason;
    }

    public CarDriver getCarDriver() {
        return carDriver;
    }

    public boolean isDisqualified() {
        return disqualified;
    }

    public String getDisqualificationReason() {
        return disqualificationReason;
    }
}
