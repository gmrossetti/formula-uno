package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.IDriver;

public record LeaderboardEntry(IDriver driver, boolean isDisqualified, String annotations) {}
