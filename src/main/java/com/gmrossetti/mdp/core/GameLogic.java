package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.cartesian.GridLine;
import com.gmrossetti.mdp.driver.DriverMoveValidator;
import com.gmrossetti.mdp.driver.IDriver;
import com.gmrossetti.mdp.leaderboard.LeaderboardEntry;

public class GameLogic {
    private final GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(){
        for (IDriver driver : gameState.getDriversStillPlaying()) {
            if(!gameState.isRaceActive()) return; // prevents making moves after HumanPlayer lost

            final GridLine driverTrace = driver.makeMove();

            updateGameState(driver, driverTrace);
        }
    }

    private void updateGameState(IDriver driver, GridLine driverTrace){
        boolean isMoveValid = DriverMoveValidator.isMoveValid(driverTrace, gameState.getCircuit());

        if(!isMoveValid){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(driver,true, "OFFTRACK"));
            return;
        }

        if(!driver.hasActiveWaypoint()){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(driver, false, ""));
        }

        gameState.getCircuit().getTile(driverTrace.getStart()).setOccupiedBy(null);
        gameState.getCircuit().getTile(driverTrace.getEnd()).setOccupiedBy(driver.getCar());
    }
}
