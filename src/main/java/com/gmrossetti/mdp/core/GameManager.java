package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.view.GameView;
import javafx.application.Platform;
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

    public GameView getView(){
        return view;
    }

    private GameManager(){
        init();
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
    }

    public void reset(){
        Circuit circuit = new Circuit();

        gameState = new GameState(circuit);
        renderer = new Renderer(gameState, view);

        gameLogic = new GameLogic(gameState);

        gameLoop = new GameLoop(gameState, gameLogic, renderer);

        gameLoop.start();

        // TODO: verione dinamica da caricare tramite file
        Car car = new Car(gameState.getCircuit().getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car);

        gameState.addCarDriver(humanCarDriver);
        // ----------------
    }

    public void checkIfGameEnded() {
        HumanCarDriver humanCarDriver = gameState.getHumanCarDriver();

        DriverMoveValidator.MoveResult humanCarDriverMoveResult = gameState.getCarDriverMoveResult(humanCarDriver);

        if(humanCarDriverMoveResult != DriverMoveValidator.MoveResult.OK){
            gameLoop.stop();

            showAlert(humanCarDriverMoveResult);
            reset();
        }
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

        ButtonType btnReset = new ButtonType("RESET");

        // Aggiungi il pulsante OK
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.initStyle(StageStyle.UNDECORATED);

        // Rendi il dialogo non chiudibile forzando l'utente a interagire
//        dialog.setOnCloseRequest(event -> event.consume()); // Blocca il pulsante "X"

        Platform.runLater(() -> {
            // Mostra il dialogo in modo sicuro
            dialog.showAndWait();
        });
    }
}
