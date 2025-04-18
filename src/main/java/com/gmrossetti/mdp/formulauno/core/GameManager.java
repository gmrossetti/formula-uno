package com.gmrossetti.mdp.formulauno.core;

import com.gmrossetti.mdp.formulauno.circuit.ICircuit;
import com.gmrossetti.mdp.formulauno.driver.HumanDriverFactory;
import com.gmrossetti.mdp.formulauno.view.GameView;
import com.gmrossetti.mdp.formulauno.parser.GameConfigObject;
import com.gmrossetti.mdp.formulauno.parser.GameConfigParser;
import com.gmrossetti.mdp.formulauno.view.RaceResult;
import javafx.scene.Scene;

/**
 * Manages the overall game lifecycle, including initialization, state management, and rendering.
 */
public class GameManager {
    private static GameManager instance = null;

    private Renderer renderer;
    private GameLoop gameLoop;
    private GameState gameState;
    private GameLogic gameLogic;
    private GameView view;
    private final Scene gameScene;

    /**
     * Retrieves the singleton instance of the GameManager.
     *
     * @return the singleton instance of GameManager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Private constructor to initialize the GameManager.
     */
    private GameManager() {
        init();
        gameScene = new Scene(view);
    }

    /**
     * Initializes the game by setting up the game state, view, renderer, logic, and game loop.
     */
    public void init() {
        GameConfigObject gameConfigObject = GameConfigParser.parse("game-config1");
        ICircuit circuit = gameConfigObject.circuit();
        gameState = new GameState(circuit, HumanDriverFactory.build(circuit), gameConfigObject.botCarDrivers());
        view = new GameView(gameState);
        renderer = new Renderer(gameState, view);
        gameLogic = new GameLogic(gameState);
        gameLoop = new GameLoop(gameLogic, renderer);
        gameLoop.start();
    }

    /**
     * Resets the game by reinitializing the game state and updating the game scene.
     */
    public void reset() {
        init();
        gameScene.setRoot(view);
    }

    /**
     * Checks if the game has ended and, if so, displays the race results and resets the game.
     */
    public void ifGameEndedShowResults() {
        if (gameState.isRaceActive()) return;
        gameLoop.stop();
        RaceResult raceResult = new RaceResult(gameState);
        raceResult.show();
        reset();
    }

    /**
     * Retrieves the game scene.
     *
     * @return the game scene
     */
    public Scene getGameScene() {
        return gameScene;
    }
}