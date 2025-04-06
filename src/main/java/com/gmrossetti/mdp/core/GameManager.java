package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.driver.*;
import com.gmrossetti.mdp.parser.GameConfigObject;
import com.gmrossetti.mdp.parser.GameConfigParser;
import com.gmrossetti.mdp.view.GameView;
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

        GameConfigObject gameConfigObject = GameConfigParser.parse("game-config2");

        ICircuit circuit = gameConfigObject.circuit();

        gameState = new GameState(circuit);

        gameState.addCarDriver(gameConfigObject.botCarDrivers());

        view = new GameView(gameState);

        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameLogic, renderer);

        gameLoop.start();

        HumanCarDriver humanCarDriver = HumanCarDriverFactory.build(circuit);
        gameState.addCarDriver(humanCarDriver);
    }

    public void reset(){
        init();

        gameScene.setRoot(view);
    }

    public void checkIfGameEnded() {
        if(gameState.isRaceActive()) return;

        gameLoop.stop();

        Leaderboard leaderBoard = gameState.getLeaderboard();

        LeaderboardEntry humanCarDriverLeaderboardEntry = leaderBoard.getLeaderboardEntry(gameState.getHumanCarDriver());

        // TODO: fix -> se Human arriva in terza posizione, la scritta appare ma il gioco non viene resettato

        String title;
        String message;

        if(humanCarDriverLeaderboardEntry.isDisqualified()){
            title = "Squalificato!";
            message = "Sei uscito dalla pista!";
        } else {
            title = "Complimenti!";
            message = "Hai concluso la gara in " + leaderBoard.getPosition(gameState.getHumanCarDriver()) + "^ posizione!";
        }

        message += "\nIl gioco è stato resettato.";

        showAlert(title, message);
        reset();
    }

    public void showAlert(String title, String message){
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.initStyle(StageStyle.UNDECORATED);

        Platform.runLater(dialog::showAndWait);
    }
}
