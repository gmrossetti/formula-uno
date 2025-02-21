package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.core.DriverMoveValidator;
import com.gmrossetti.mdp.entity.CircuitGridPoint;
import com.gmrossetti.mdp.entity.GridLine;
import com.gmrossetti.mdp.entity.GridPoint;
import com.gmrossetti.mdp.entity.Point;

import java.util.*;

public class BotCarDriver extends CarDriver{
//    final ArrayList<GridPoint> waypoints;
    final Circuit circuit;

    final DriverMoveValidator driverMoveValidator;
    public BotCarDriver(Car car, Circuit circuit) {
        super(car);

        this.circuit = circuit;

        driverMoveValidator = new DriverMoveValidator();

        // TODO: refactor, waypoints are hardcoded
        /*waypoints = new ArrayList<>();

        waypoints.add(new GridPoint(18, 5)); // start point
        waypoints.add(new GridPoint(47, 5));
        waypoints.add(new GridPoint(53, 11));
        waypoints.add(new GridPoint(49, 18));
        waypoints.add(new GridPoint(55, 32));
        waypoints.add(new GridPoint(36, 39));
        waypoints.add(new GridPoint(32, 28));
        waypoints.add(new GridPoint(23, 24));
        waypoints.add(new GridPoint(10, 29));
        waypoints.add(new GridPoint(4, 22));
        waypoints.add(new GridPoint(3, 12));
        waypoints.add(new GridPoint(8, 5));
        waypoints.add(new GridPoint(17, 5));
        waypoints.add(new GridPoint(17, 5)); // end point*/
    }

    public final GridLine makeMove(){
        Move move = getNextMove();

        return super.processMove(move);
    }

    int currentWaypointIdx = 1;
    private Move getNextMove(){
        Map<Move, GridPoint> movesPoints = this.getMovesPoints();

        GridLine waypoint2waypoint = new GridLine(waypoints.get(currentWaypointIdx - 1), waypoints.get(currentWaypointIdx));
        Point medianPoint = waypoint2waypoint.getMedianPoint();

        List<GridPoint> sortedGpsToMedian = new ArrayList<>(movesPoints.values().stream().sorted(Comparator
                .comparingDouble(gp -> gp.distanceTo(medianPoint))).filter(gridPoint -> circuit.getGridPoint(gridPoint).type != CircuitGridPoint.GridPointType.OUTSIDE).toList());

        List<GridPoint> sortedGpsToNextWaypoint = new ArrayList<>(movesPoints.values().stream().sorted(Comparator
                .comparingDouble(gp -> gp.distanceTo(waypoints.get(currentWaypointIdx)))).toList());

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

        if(waypoints.get(currentWaypointIdx).gridPointsInRange(3).contains(gpChosen)){
            System.out.println("Waypoint n." +  currentWaypointIdx + " raggiunto.");
            currentWaypointIdx++;

            System.out.println("Prossimo waypoint: " +  waypoints.get(currentWaypointIdx));
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
