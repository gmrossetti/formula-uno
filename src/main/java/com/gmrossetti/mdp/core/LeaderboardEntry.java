package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;

public record LeaderboardEntry(CarDriver carDriver, boolean isDisqualified, String annotations) {}
