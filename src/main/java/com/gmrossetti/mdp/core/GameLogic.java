package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.entity.GridLine;

public class GameLogic {
    private GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        // TODO: add all checks also for bot moves

        HumanCarDriver humanCarDriver = gameState.getHumanCarDriver();

        if(humanCarDriver != null){
            GridLine driverTrace = humanCarDriver.makeMove(move);

            DriverMoveValidator.MoveResult moveResult = DriverMoveValidator.evaluateMove(driverTrace, gameState);

            System.out.println(moveResult);
        }
    }
}
