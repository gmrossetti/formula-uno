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
import java.util.Set;
import java.util.stream.Stream;

public class Game {
    private final CircuitController circuitCtrl;
    private final ArrayList<PlayerController> playersCtrl;

    enum Status {
        GAME_START,
        GAME_IN_PROGRESS,
        PLAYER_OUT_OF_TRACK,
        BOT_WINS,
        PLAYER_WINS,
        GAME_OVER
    }

    private Status gameStatus;

    public Game(){
        Circuit circuit = new Circuit();

        circuitCtrl = new CircuitController(circuit);
        playersCtrl = new ArrayList<>();
        initGame();
    }

    public Status getGameStatus(){
        return this.gameStatus;
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

        gameStatus = Status.GAME_START;
    }

    public void nextStep(Player.Move move){
        for (PlayerController playerCtrl:
                playersCtrl) {

            gameStatus = Status.GAME_IN_PROGRESS;

            Point point2reach = playerCtrl.getModel().getMovesPoints().get(move);

            Set<Point> pointsInTrajectory = playerCtrl.getModel().getPointsInTrajectory(point2reach);

            Set<GridPoint> gridPointsInTrajectory = circuitCtrl.getModel().pointsToGridPoints(pointsInTrajectory);

            handlePlayerMove(gridPointsInTrajectory);
        }
    }

    public void handlePlayerMove(Set<GridPoint> gridPointsInTrajectory){
        Stream<GridPoint> gridPointsStream =  gridPointsInTrajectory.stream();

        if(gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE)){
            // OK
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE)) {
            throw new IllegalStateException();
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.START)) {
            // OK
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.END)) {
            throw new IllegalStateException();
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE)){
            this.gameStatus = Status.GAME_OVER; // GAME OVER
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.START)){
            // OK
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.START
                || gridPoint.type == GridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.START || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN START) ELSE ILLEGAL MOVE
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.START || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsStream.allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.START
                || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN 		OUTSIDE && END DISTANCE CLOSER THAN START)
        }
    }

    public StackPane getView(){
        Pane rootPane = new Pane();
//        rootPane.setPrefSize(1920, 1000);

        rootPane.setPrefSize(circuitCtrl.getModel().getGridWidth() * circuitCtrl.getView().SPACING - 20, circuitCtrl.getModel().getGridHeight() * circuitCtrl.getView().SPACING);


        rootPane.setStyle("-fx-margin: 20px;");


        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #333; -fx-padding: 20px;");

        stackPane.getChildren().addAll(rootPane, circuitCtrl.getView());

        for (PlayerController playerCtrl:
                this.getPlayersCtrl()) {
            stackPane.getChildren().add(playerCtrl.getView());
        }

        return stackPane;
    }
}
