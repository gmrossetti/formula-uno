package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.HumanDriverFactory;
import com.gmrossetti.mdp.leaderboard.Leaderboard;
import com.gmrossetti.mdp.leaderboard.LeaderboardEntry;
import com.gmrossetti.mdp.parser.GameConfigObject;
import com.gmrossetti.mdp.parser.GameConfigParser;
import com.gmrossetti.mdp.view.GameView;
import com.gmrossetti.mdp.view.RaceResult;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

public class GameManager {
    private static GameManager instance = null;
    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }

        return instance;
    }

    private Renderer renderer;
    private GameLoop gameLoop;
    private GameState gameState;
    private GameLogic gameLogic;

    private GameView view;

    public Scene getGameScene() {
        return gameScene;
    }

    private final Scene gameScene;

    private GameManager(){
        init();

        gameScene = new Scene(view);
    }

    public void init(){

        GameConfigObject gameConfigObject = GameConfigParser.parse("game-config1");

        ICircuit circuit = gameConfigObject.circuit();

        gameState = new GameState(circuit, HumanDriverFactory.build(circuit), gameConfigObject.botCarDrivers());

        view = new GameView(gameState);

        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameLogic, renderer);

        gameLoop.start();
    }

    public void reset(){
        init();

        gameScene.setRoot(view);
    }

    public void ifGameEndedShowResults() {
        if(gameState.isRaceActive()) return;

        gameLoop.stop();

        RaceResult raceResult = new RaceResult(gameState);

        raceResult.show();
        reset();
    }
}
