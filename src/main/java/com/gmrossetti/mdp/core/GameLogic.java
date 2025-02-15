package com.gmrossetti.mdp.core;

import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import com.gmrossetti.mdp.model.GridLine;
import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Point;
import javafx.util.Pair;

import java.util.Set;

public class GameLogic {
    private GameState gameState;

    public GameLogic(GameState gameState) {
        this.gameState = gameState;
    }

    public void nextStep(CarDriver.Move move){
        // TODO: add all checks for player and bot moves

        HumanCarDriver humanCarDriver = gameState.getHumanCarDriver();

        if(humanCarDriver != null){
            GridLine driverTrace = humanCarDriver.makeMove(move);

            // TODO: add Human Move check
        }


    }

    /*public void handlePlayerMove(Set<CircuitGridPoint> gridPointsInTrajectory){
//        Stream<CircuitGridPoint> gridPointsStream =  gridPointsInTrajectory.stream();

        if(gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE)){
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE)) {
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.START)) {
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.END)) {
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE)){
            this.gameStatus = Status.GAME_OVER; // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.START)){
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            // RACE END
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.START
                || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE || gridPoint.type == CircuitGridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.START || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN START) ELSE ILLEGAL MOVE
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.START || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == CircuitGridPoint.GridPointType.INSIDE
                || gridPoint.type == CircuitGridPoint.GridPointType.OUTSIDE || gridPoint.type == CircuitGridPoint.GridPointType.START
                || gridPoint.type == CircuitGridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN 		OUTSIDE && END DISTANCE CLOSER THAN START)
        }
    }*/
}
