package com.gmrossetti.mdp.formulauno.leaderboard;

import com.gmrossetti.mdp.formulauno.driver.IDriver;

public record LeaderboardEntry(IDriver driver, boolean isDisqualified, String annotations) {}
