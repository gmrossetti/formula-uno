package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.entity.cartesian.GridLine;

public class GameLogic {
    private GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        for (CarDriver carDriver : gameState.getCarDriversStillPlaying()) {
            if(!gameState.isRaceActive()) return; // prevents making moves after HumanPlayer lost

            if (carDriver instanceof HumanCarDriver humanCarDriver) {
                GridLine driverTrace = humanCarDriver.makeMove(move);
                DriverMoveValidator.MoveResult moveResult = DriverMoveValidator.evaluateMove(driverTrace, gameState.getCircuit());

                System.out.println("Human moveResult: " + moveResult);

                updateGameState(carDriver, moveResult);
            } else if (carDriver instanceof BotCarDriver botCarDriver) {
                GridLine driverTrace = botCarDriver.makeMove();
                DriverMoveValidator.MoveResult moveResult = DriverMoveValidator.evaluateMove(driverTrace, gameState.getCircuit());

                updateGameState(carDriver, moveResult);
            }
        }
    }

    private void updateGameState(CarDriver carDriver, DriverMoveValidator.MoveResult moveResult){
        if(moveResult != DriverMoveValidator.MoveResult.OK){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver,moveResult.toString()));

            return;
        }

        if(!carDriver.hasActiveWaypoint()){
            gameState.getLeaderboard().addEntry(new LeaderboardEntry(carDriver));
        }
    }
}
