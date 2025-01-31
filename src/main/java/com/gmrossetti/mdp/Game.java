package com.gmrossetti.mdp;

import com.gmrossetti.mdp.controller.CircuitController;
import com.gmrossetti.mdp.controller.PlayerController;
import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Player;
import com.gmrossetti.mdp.model.Point;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Game {
    private final CircuitController circuitCtrl;
    private final ArrayList<PlayerController> playersCtrl;

    public Game(){
        Circuit circuit = new Circuit();

        circuitCtrl = new CircuitController(circuit);
        playersCtrl = new ArrayList<>();
        initGame();
    }

    public CircuitController getCircuitCtrl() {
        return circuitCtrl;
    }
    public ArrayList<PlayerController> getPlayersCtrl(){
        return playersCtrl;
    }

    private void addPlayer(){
        GridPoint raceStartGridPoint = circuitCtrl.getModel().getRaceStartPoint();

        Player player = new Player(raceStartGridPoint);

        PlayerController playerCtrl = new PlayerController(player);
        playersCtrl.add(playerCtrl);
    }

    public void initGame(){
        // adding N players
        for (int i = 0; i < 1; i++) {
            addPlayer();
        }
    }

    public void nextStep(){
        for (PlayerController playerCtrl:
                playersCtrl) {

            ArrayList<GridPoint> availableGridPoints = getAvailablePlayerPoints(playerCtrl, circuitCtrl);

            playerCtrl.nextMove(availableGridPoints);
        }
    }

    public StackPane getView(){

        Pane rootPane = new Pane();
        rootPane.setPrefSize(1920, 1000);
        rootPane.setStyle("-fx-padding: 20px;");


        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #ccc; -fx-padding: 20px;");

        stackPane.getChildren().addAll(rootPane, circuitCtrl.getView());

        for (PlayerController playerCtrl:
                this.getPlayersCtrl()) {
            stackPane.getChildren().add(playerCtrl.getView());
        }

        return stackPane;
    }

    public static ArrayList<GridPoint> getAvailablePlayerPoints(PlayerController playerCtrl, CircuitController circuitCtrl){
        Point[] playerReachablePoints = playerCtrl.getModel().getReachablePoints();

        ArrayList<GridPoint> usablePoints = new ArrayList<>();

        for (Point playerReachablePoint:
                playerReachablePoints) {

            boolean isWalkable = circuitCtrl.getModel().getGridPoint(playerReachablePoint).isWalkable();

            if(isWalkable) usablePoints.add(circuitCtrl.getModel().getGridPoint(playerReachablePoint));
        }

        return usablePoints;
    }
}
