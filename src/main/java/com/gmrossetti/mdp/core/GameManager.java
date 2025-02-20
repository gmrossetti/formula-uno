package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.BotCarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
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
        Circuit circuit = new Circuit();

        gameState = new GameState(circuit);

        view = new GameView(gameState);

        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameState, gameLogic, renderer);

        gameLoop.start();

        // TODO: verione dinamica da caricare tramite file
        Car car = new Car(gameState.getCircuit().getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car);

        gameState.addCarDriver(humanCarDriver);
        // ----------------

        for (int i = 0; i < 3; i++) {
            gameState.addCarDriver(new BotCarDriver(new Car(gameState.getCircuit().getRaceStartPoint())));
        }
    }

    public void reset(){
        init();

        gameScene.setRoot(view);
    }

    public void checkIfGameEnded() {
        if(gameState.isRaceActive()) return;

        DriverMoveValidator.MoveResult finalHumanCarDriverMoveResult =
                gameState.getCarDriverMoveResult(gameState.getHumanCarDriver());

        gameLoop.stop();

        showAlert(finalHumanCarDriverMoveResult);
        reset();
    }

    public void showAlert(DriverMoveValidator.MoveResult moveResult){

        String title;
        String message;
        switch (moveResult) {
            case OFFTRACK -> {
                title = "Squalificato!";
                message = "Sei uscito dalla pista!";
            }
            case FINISH -> {
                // TODO: aggiungere posizione in classifica
                title = "Complimenti!";
                message = "Hai completato la gara!";
            }
            case CHEAT -> {
                title = "Squalificato!";
                message = "Mossa non valida!";
            }
            default -> throw new IllegalStateException("Risultato inatteso: " + moveResult);
        }

        message += " Il gioco è stato resettato.";

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);

        // Aggiungi il pulsante OK
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.initStyle(StageStyle.UNDECORATED);

        // Mostra il dialogo in modo sicuro
        Platform.runLater(dialog::showAndWait);
    }
}
