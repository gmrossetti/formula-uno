package com.gmrossetti.mdp.formulauno;

import com.gmrossetti.mdp.formulauno.core.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for launching the Formula 1 game.
 */
public class MainApp extends Application {
    private final GameManager gameManager = GameManager.getInstance();

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize the game manager and load the game scene
        final Scene gameScene = gameManager.getGameScene();

        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main entry point for the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
