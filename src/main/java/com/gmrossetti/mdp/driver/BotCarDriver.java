package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.entity.*;

import java.util.*;

public class BotCarDriver extends CarDriver{
    final Circuit circuit;
    final DriverMoveValidator driverMoveValidator;
    public BotCarDriver(Car car, Circuit circuit) {
        super(car, circuit.getWaypointsHead());

        this.circuit = circuit;

        driverMoveValidator = new DriverMoveValidator();
    }

    public final GridLine makeMove(){
        Move move = getNextMove();

        return super.processMove(move);
    }

    private Move getNextMove(){
        Map<Move, GridPoint> movesPoints = this.getMovesPoints();

        Waypoint currentWaypoint = this.waypointTarget;

        if(!currentWaypoint.hasPrevious()) throw new RuntimeException("CarDriver must be initialized with the second waypoint");

        Waypoint previuosWaypoint = this.waypointTarget.getPrevious();

        GridLine waypoint2waypoint = new GridLine(previuosWaypoint.getCenter(), currentWaypoint.getCenter());
        Point medianPoint = waypoint2waypoint.getMedianPoint();

        List<GridPoint> sortedGpsToMedian = new ArrayList<>(movesPoints.values().stream().sorted(Comparator
                .comparingDouble(gp -> gp.distanceTo(medianPoint))).filter(gridPoint -> circuit.getGridPoint(gridPoint).type != CircuitGridPoint.GridPointType.OUTSIDE).toList());

        List<GridPoint> sortedGpsToNextWaypoint = new ArrayList<>(movesPoints.values().stream().sorted(Comparator
                .comparingDouble(gp -> gp.distanceTo(currentWaypoint.getCenter()))).toList());

        sortedGpsToMedian = filterOutUnusableGP(sortedGpsToMedian);
        sortedGpsToNextWaypoint = filterOutUnusableGP(sortedGpsToNextWaypoint);

        if(Math.max(sortedGpsToMedian.size(), sortedGpsToNextWaypoint.size())  == 0){ // nessuna mossa valida
            return Move.BR; // ritorna una mossa qualunque
        }

        final boolean allPivotValidPoints = sortedGpsToMedian.size() == 9;

        final double velocityModule = this.getCar().getVelocityModule();

        GridPoint gpChosen;

        if(velocityModule <= 2 && allPivotValidPoints){
            gpChosen = sortedGpsToNextWaypoint.get(0);
        } else {
            gpChosen = sortedGpsToMedian.get(0);
        }

        for (Move move:
        movesPoints.keySet()) {
            if(movesPoints.get(move).equals(gpChosen)){
                return move;
            }
        }

        return null;
    }

    private List<GridPoint> filterOutUnusableGP(List<GridPoint> gridPoints){
        List<GridPoint> gridPointsModified = new ArrayList<>(gridPoints);

        Iterator<GridPoint> it = gridPointsModified.iterator();
        while (it.hasNext()) {
            GridPoint gp = it.next();

            if(this.getCar().isStationary() && this.getCar().getPosition().equals(gp)){
                it.remove();
                continue;
            }

            DriverMoveValidator.MoveResult ipoteticMoveResult =
                    DriverMoveValidator.evaluateMove(new GridLine(this.getCar().getPosition(), gp), circuit);

            if(ipoteticMoveResult == DriverMoveValidator.MoveResult.CHEAT ||
                    ipoteticMoveResult == DriverMoveValidator.MoveResult.OFFTRACK){
                it.remove();
//                System.out.println("Rimosso punto");
            }
        }

        return gridPointsModified;
    }
}
