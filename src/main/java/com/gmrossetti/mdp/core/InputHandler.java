package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;

public class InputHandler {
    private static InputHandler instance;
    private CarDriver.Move lastMove = null;

    private InputHandler() {} // Costruttore privato

    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }

    public void stashInput(CarDriver.Move move){
        this.lastMove = move;
    }

    public CarDriver.Move popMove() {
        CarDriver.Move moveToReturn = this.lastMove;

        this.lastMove = null;

        return moveToReturn;
    }

    public boolean hasMove(){
        return lastMove != null;
    }
}
