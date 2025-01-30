package com.gmrossetti.mdp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Formula1App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        StackPane stackPane = game.getView();

        GridPane stepBtnGrid = new GridPane();

        Button btnNextStep = new Button("NEXT");

        stepBtnGrid.getChildren().add(btnNextStep);

        btnNextStep.setOnAction(event -> {
            System.out.println("Bottone cliccato!");
            game.nextStep();
        });

        stepBtnGrid.setStyle("-fx-padding: 20px;");

        VBox gameScreen = new VBox(stackPane, stepBtnGrid);

        Scene scene = new Scene(gameScreen);

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
