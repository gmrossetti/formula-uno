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

            if (carDriver instanceof HumanCarDriver humanCarDriver) {
                GridLine driverTrace = humanCarDriver.makeMove(move);
                boolean isMoveValid = DriverMoveValidator.isMoveValid(driverTrace, gameState.getCircuit());

                updateGameState(carDriver, isMoveValid);
            } else if (carDriver instanceof BotCarDriver botCarDriver) {
                GridLine driverTrace = botCarDriver.makeMove();
                boolean isMoveValid = DriverMoveValidator.isMoveValid(driverTrace, gameState.getCircuit());

                updateGameState(carDriver, isMoveValid);
            }
        }
    }

    private void updateGameState(CarDriver carDriver, boolean isMoveValid){
        if(!isMoveValid){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver,"OFFTRACK"));
            return;
        }

        if(!carDriver.hasActiveWaypoint()){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver));
        }
    }
}
