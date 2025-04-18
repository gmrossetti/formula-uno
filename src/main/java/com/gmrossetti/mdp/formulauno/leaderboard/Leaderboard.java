package com.gmrossetti.mdp.formulauno.leaderboard;

import com.gmrossetti.mdp.formulauno.driver.IDriver;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Leaderboard {
    private final LinkedList<LeaderboardEntry> entries = new LinkedList<>();

    public void addEntry(LeaderboardEntry leaderboardEntry){
        entries.addLast(leaderboardEntry);
    }

    public boolean isEmpty(){
        return entries.isEmpty();
    }

    public boolean containsDriver(IDriver driver){
        return entries.stream().anyMatch(leaderboardEntry -> leaderboardEntry.driver().equals(driver));
    }

    public LeaderboardEntry getLeaderboardEntry(IDriver driver){
        LeaderboardEntry result = entries.stream().filter(leaderboardEntry -> leaderboardEntry.driver().equals(driver))
                .findFirst().orElse(null);

        if(result == null) throw new NoSuchElementException("driver provided not found.");

        return result;
    }

    public int getPosition(IDriver driver) {
        if(getLeaderboardEntry(driver).isDisqualified()){
            throw new RuntimeException("Cannot call getPosition on a disqualified CarDriver.");
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
