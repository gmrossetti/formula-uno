package com.gmrossetti.mdp.formulauno.leaderboard;

import com.gmrossetti.mdp.formulauno.driver.IDriver;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Leaderboard class that maintains a list of leaderboard entries.
 * It provides methods to add entries, check if the leaderboard is empty,
 * check if a driver is present, get a driver's entry, and get a driver's position.
 */
public class Leaderboard {
    private final LinkedList<LeaderboardEntry> entries = new LinkedList<>();

    /**
     * Adds a new entry to the leaderboard.
     *
     * @param leaderboardEntry The leaderboard entry to be added.
     */
    public void addEntry(LeaderboardEntry leaderboardEntry){
        entries.addLast(leaderboardEntry);
    }

    /**
     * Checks if the leaderboard is empty.
     *
     * @return True if the leaderboard is empty, false otherwise.
     */
    public boolean isEmpty(){
        return entries.isEmpty();
    }

    /**
     * Checks if the leaderboard contains a specific driver.
     *
     * @param driver The driver to be checked.
     * @return True if the driver is present, false otherwise.
     */
    public boolean containsDriver(IDriver driver){
        return entries.stream().anyMatch(leaderboardEntry -> leaderboardEntry.driver().equals(driver));
    }

    /**
     * Gets the leaderboard entry for a specific driver.
     *
     * @param driver The driver whose entry is to be retrieved.
     * @return The leaderboard entry for the specified driver.
     * @throws NoSuchElementException if the driver is not found in the leaderboard.
     */
    public LeaderboardEntry getLeaderboardEntry(IDriver driver){
        LeaderboardEntry result = entries.stream().filter(leaderboardEntry -> leaderboardEntry.driver().equals(driver))
                .findFirst().orElse(null);

        if(result == null) throw new NoSuchElementException("driver provided not found.");

        return result;
    }

    /**
     * Gets the position of a specific driver in the leaderboard.
     *
     * @param driver The driver whose position is to be retrieved.
     * @return The position of the specified driver.
     * @throws IllegalArgumentException if the driver is disqualified.
     */
    public int getPosition(IDriver driver) {
        if(getLeaderboardEntry(driver).isDisqualified()){
            throw new IllegalArgumentException("Cannot call getPosition on a disqualified CarDriver.");
        }

        int positionCnt = 1;

        for (LeaderboardEntry leaderboardEntry:
             entries) {

            if(leaderboardEntry.driver().equals(driver)){
                return positionCnt;
            }

            if(!leaderboardEntry.isDisqualified()) positionCnt++;
        }

        return positionCnt;
    }
}
