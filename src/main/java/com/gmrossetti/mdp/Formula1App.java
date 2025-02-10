package com.gmrossetti.mdp;

import com.gmrossetti.mdp.model.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class Formula1App extends Application {
    private Stage primaryStage;
    private Game game;
    private StackPane gameStackPane;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        game = new Game();

        gameStackPane = game.getView();

        VBox gameScreen = new VBox(gameStackPane, getPlayerControlsGrid());

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

        if(game.getGameStatus() != Game.Status.GAME_IN_PROGRESS){
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text(game.getGameStatus().toString()));

            Button btnRestartGame = new Button("RESTART");

            btnRestartGame.setOnAction(e -> {
                handleRestartGameButton();
                dialog.close();
            });

            dialogVbox.getChildren().add(btnRestartGame);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    private void handleRestartGameButton(){
        game = new Game();

        gameStackPane = game.getView();

        VBox gameScreen = new VBox(gameStackPane, getPlayerControlsGrid());

        Scene scene = new Scene(gameScreen);
        primaryStage.setScene(scene);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
