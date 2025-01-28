package com.gmrossetti.mdp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Formula1App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Game game = new Game();

        // make some moves to test the algorithm
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();
        game.nextStep();

        StackPane stackPane = game.getView();

        Scene scene = new Scene(stackPane);

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
