package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.IDriver;

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

    public boolean containsCarDriver(IDriver carDriver){
        return entries.stream().anyMatch(leaderboardEntry -> leaderboardEntry.driver().equals(carDriver));
    }

    public LeaderboardEntry getLeaderboardEntry(IDriver carDriver){
        LeaderboardEntry result = entries.stream().filter(leaderboardEntry -> leaderboardEntry.driver().equals(carDriver))
                .findFirst().orElse(null);

        if(result == null) throw new NoSuchElementException("carDriver provided not found.");

        return result;
    }

    public int getPosition(IDriver carDriver) {
        if(getLeaderboardEntry(carDriver).isDisqualified()){
            throw new RuntimeException("Cannot call getPosition on a disqualified CarDriver.");
        }

        int positionCnt = 1;

        for (LeaderboardEntry leaderboardEntry:
             entries) {

            if(leaderboardEntry.driver().equals(carDriver)){
                return positionCnt;
            }

            if(!leaderboardEntry.isDisqualified()) positionCnt++;
        }

        return positionCnt;
    }
}
