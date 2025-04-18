package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.driver.move.DriverMoveValidator;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.leaderboard.LeaderboardEntry;

/**
 * GameLogic class handles the game logic for the Formula Uno game.
 * It processes the moves of each driver and updates the game state accordingly.
 */
public class GameLogic {
    private final GameState gameState;

    /**
     * Constructor for GameLogic.
     *
     * @param gameState The current state of the game.
     */
    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Processes the next step in the game.
     * It iterates through all drivers and makes their moves.
     * If a driver goes off track, it updates the leaderboard accordingly.
     */
    public void nextStep(){
        for (IDriver driver : gameState.getDriversStillPlaying()) {
            if(!gameState.isRaceActive()) return; // prevents making moves after HumanPlayer lost

            final GridLine driverTrace = driver.makeMove();

            updateGameState(driver, driverTrace);
        }
    }

    /**
     * Updates the game state based on the driver's move.
     * It checks if the move is valid and updates the leaderboard and circuit accordingly.
     *
     * @param driver The driver making the move.
     * @param driverTrace The trace of the driver's move.
     */
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
