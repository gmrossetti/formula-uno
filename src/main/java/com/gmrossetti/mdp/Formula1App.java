package com.gmrossetti.mdp;

import com.gmrossetti.mdp.controller.CircuitController;
import com.gmrossetti.mdp.controller.PlayerController;
import com.gmrossetti.mdp.model.Point;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Formula1App extends Application {

    private final StackPane stackPane;
    private final CircuitController circuitCtrl;
    private final ArrayList<PlayerController> players;

    public Formula1App() {
        this.stackPane = new StackPane();
        this.circuitCtrl = new CircuitController();
        this.players = new ArrayList<>();
        initView();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Crea la scena e imposta il StackPane come radice
        Scene scene = new Scene(stackPane);

        addPlayer();

        PlayerController playerCtrl = this.players.get(0);

        playerCtrl.setPosition(circuitCtrl.getRaceStartPoint());

        this.stackPane.getChildren().add(playerCtrl.getView());

        // Imposta il titolo della finestra
        primaryStage.setTitle("Formula 1");
        primaryStage.setScene(scene);
        primaryStage.show();

        onUpdate();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void onUpdate() {
        PlayerController playerCtrl = this.players.get(0);

        playerCtrl.setPosition(new Point(1,1, Point.PointType.INSIDE));

        this.stackPane.getChildren().remove(playerCtrl.getView());


        this.stackPane.getChildren().add(playerCtrl.getView());
    }

    private void initView(){
        stackPane.setStyle("-fx-padding: 20px;");

        Pane rootPane = new Pane();
        rootPane.setPrefSize(1920, 1000);
        rootPane.setStyle("-fx-background-color: #ccc; -fx-padding: 20px;");

        stackPane.getChildren().addAll(rootPane,circuitCtrl.getView());
    }

    private void addPlayer(){
        PlayerController playerCtrl = new PlayerController();

        this.players.add(playerCtrl);
    }
}
