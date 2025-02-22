package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.entity.GridLine;

public class GameLogic {
    private GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        for (CarDriver carDriver : gameState.getCarDriversStillPlaying()) {
            if(!gameState.isRaceActive()) return; // prevents making moves after HumanPlayer lost

            if (carDriver instanceof HumanCarDriver humanCarDriver) {
                handleHumanCarDriver(humanCarDriver, move);
            } else if (carDriver instanceof BotCarDriver botCarDriver) {
                handleBotCarDriver(botCarDriver);
            }
        }
    }

    private void handleHumanCarDriver(HumanCarDriver humanCarDriver, CarDriver.Move move) {
        // Gestisce i movimenti del driver umano
        GridLine driverTrace = humanCarDriver.makeMove(move);
        DriverMoveValidator.MoveResult moveResult = DriverMoveValidator.evaluateMove(driverTrace, gameState.getCircuit());
        gameState.updateCarDriverState(humanCarDriver, moveResult);


        // TODO: fix race end bug: after player passes end lines, games does not stop
    }

    private void handleBotCarDriver(BotCarDriver botCarDriver) {
        // Gestisce i movimenti del driver bot
        GridLine driverTrace = botCarDriver.makeMove();
        DriverMoveValidator.MoveResult moveResult = DriverMoveValidator.evaluateMove(driverTrace, gameState.getCircuit());
        gameState.updateCarDriverState(botCarDriver, moveResult);
    }
}
