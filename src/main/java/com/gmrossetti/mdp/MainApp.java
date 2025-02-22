package com.gmrossetti.mdp;

import com.gmrossetti.mdp.core.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MainApp extends Application {
    private final GameManager gameManager = GameManager.getInstance();

    @Override
    public void start(Stage primaryStage) {
        final Scene gameScene = gameManager.getGameScene();

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
