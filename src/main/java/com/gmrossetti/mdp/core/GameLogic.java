package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;

public class GameLogic {
    private GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        // TODO: add all checks also for bot moves
        for (CarDriver carDriver:
            gameState.getCarDrivers()) {

            if(carDriver instanceof HumanCarDriver){
                gameState.applyMove(carDriver, move);

                continue;
            }

            // TODO: add bot moves
        }
    }
}
