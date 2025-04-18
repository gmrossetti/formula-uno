package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.driver.move.Move;

/**
 * InputHandler class is a singleton that manages the input moves for the game.
 * It allows stashing and popping moves, and checking if there are any moves available.
 */
public class InputHandler {
    private static InputHandler instance;
    private Move lastMove = null;

    /**
     * Retrieves the singleton instance of the InputHandler.
     *
     * @return the singleton instance of InputHandler
     */
    private InputHandler() {} // Costruttore privato

    /**
     * Retrieves the singleton instance of the InputHandler.
     *
     * @return the singleton instance of InputHandler
     */
    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }

    /**
     * Stashes the input move.
     *
     * @param move The move to be stashed.
     */
    public void stashInput(Move move){
        this.lastMove = move;
    }

    /**
     * Pops the last stashed move.
     *
     * @return The last stashed move.
     */
    public Move popMove() {
        Move moveToReturn = this.lastMove;

        this.lastMove = null;

        return moveToReturn;
    }

    /**
     * Checks if there is a stashed move available.
     *
     * @return true if there is a stashed move, false otherwise.
     */
    public boolean hasMove(){
        return lastMove != null;
    }
}
