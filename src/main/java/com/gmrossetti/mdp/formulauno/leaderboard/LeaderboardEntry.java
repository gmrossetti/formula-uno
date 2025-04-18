package com.gmrossetti.mdp.formulauno.leaderboard;

import com.gmrossetti.mdp.formulauno.driver.IDriver;

/**
 * Represents an entry in the leaderboard.
 *
 * @param driver          The driver associated with this entry.
 * @param isDisqualified   Indicates if the driver is disqualified.
 * @param annotations      Additional annotations or comments about the entry.
 */
public record LeaderboardEntry(IDriver driver, boolean isDisqualified, String annotations) {}
