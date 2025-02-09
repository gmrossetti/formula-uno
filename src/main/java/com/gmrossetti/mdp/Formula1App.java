package com.gmrossetti.mdp;

import com.gmrossetti.mdp.model.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class Formula1App extends Application {
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        game = new Game();

        StackPane stackPane = game.getView();

        VBox gameScreen = new VBox(stackPane, getPlayerControlsGrid());

        Scene scene = new Scene(gameScreen);

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane getPlayerControlsGrid(){
        // Crea un GridPane per organizzare i pulsanti in griglia
        GridPane grid = new GridPane();
        grid.setHgap(10);  // Distanza orizzontale tra i pulsanti
        grid.setVgap(10);  // Distanza verticale tra i pulsanti
        grid.setAlignment(Pos.CENTER);  // Allinea la griglia al centro della finestra

        Player.Move[] moves = Player.Move.values();

        System.out.println(Arrays.deepToString(moves));

        int moveIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Player.Move move = moves[moveIndex++];

                Button btn = new Button(move.toString());

                btn.setOnAction(e -> handlePlayerButtonClick(move));

                grid.add(btn, j, i);
            }
        }

        grid.setStyle("-fx-padding: 20px;");

        return grid;
    }

    private void handlePlayerButtonClick(Player.Move move) {
        game.nextStep(move);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
