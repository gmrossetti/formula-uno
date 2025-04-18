package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.driver.move.Move;

public class InputHandler {
    private static InputHandler instance;
    private Move lastMove = null;

    private InputHandler() {} // Costruttore privato

    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }

    public void stashInput(Move move){
        this.lastMove = move;
    }

    public Move popMove() {
        Move moveToReturn = this.lastMove;

        this.lastMove = null;

        return moveToReturn;
    }

    public boolean hasMove(){
        return lastMove != null;
    }
}
