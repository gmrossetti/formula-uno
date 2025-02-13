package com.gmrossetti.mdp;

import com.gmrossetti.mdp.core.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Formula1App extends Application {
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) {
        gameManager = GameManager.getInstance();

        Scene scene = new Scene(gameManager.getView());

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
