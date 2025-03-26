package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class GameLogic {
    private final GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        for (CarDriver carDriver : gameState.getCarDriversStillPlaying()) {
            if(!gameState.isRaceActive()) return; // prevents making moves after HumanPlayer lost

            GridLine driverTrace = null;

            if (carDriver instanceof HumanCarDriver humanCarDriver) {
                driverTrace = humanCarDriver.makeMove(move);
            } else if (carDriver instanceof BotCarDriver botCarDriver) {
                driverTrace = botCarDriver.makeMove();
            }

            updateGameState(carDriver, driverTrace);
        }
    }

    private void updateGameState(CarDriver carDriver, GridLine driverTrace){
        boolean isMoveValid = DriverMoveValidator.isMoveValid(driverTrace, gameState.getCircuit());

        if(!isMoveValid){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver,true, "OFFTRACK"));
            return;
        }

        if(!carDriver.hasActiveWaypoint()){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver, false, ""));
        }

        gameState.getCircuit().getGridPoint(driverTrace.getStart()).setOccupiedBy(null);
        gameState.getCircuit().getGridPoint(driverTrace.getEnd()).setOccupiedBy(carDriver.getCar());
    }
}
