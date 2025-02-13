package com.gmrossetti.mdp.core;

public class GameLogic {

    /*public void handlePlayerMove(Set<GridPoint> gridPointsInTrajectory){
//        Stream<GridPoint> gridPointsStream =  gridPointsInTrajectory.stream();

        if(gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE)){
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE)) {
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.START)) {
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.END)) {
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE)){
            this.gameStatus = Status.GAME_OVER; // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.START)){
            // OK
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.START
                || gridPoint.type == GridPoint.GridPointType.END)){
            throw new IllegalStateException();
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.START)){
            // GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.START || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN START) ELSE ILLEGAL MOVE
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.OUTSIDE
                || gridPoint.type == GridPoint.GridPointType.START || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN OUTSIDE) ELSE GAME OVER
        } else if (gridPointsInTrajectory.stream().allMatch(gridPoint -> gridPoint.type == GridPoint.GridPointType.INSIDE
                || gridPoint.type == GridPoint.GridPointType.OUTSIDE || gridPoint.type == GridPoint.GridPointType.START
                || gridPoint.type == GridPoint.GridPointType.END)){
            // RACE END (SE END DISTANCE CLOSER THAN 		OUTSIDE && END DISTANCE CLOSER THAN START)
        }
    }*/
}
