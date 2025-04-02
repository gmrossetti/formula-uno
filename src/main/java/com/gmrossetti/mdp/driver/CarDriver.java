package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.pawn.IPawn;
import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;
import com.gmrossetti.mdp.entity.waypoint.Waypoint;

import java.util.HashMap;
import java.util.Map;

abstract public class CarDriver {
    public IPawn getCar() {
        return car;
    }

    // TODO: togliere public
    public Waypoint waypointTarget;

    private final IPawn car;

    public enum Move {
        TL, TM, TR, CL, CM, CR, BL, BM, BR
    }

    public CarDriver(IPawn car, Waypoint waypointHead){
        this.car = car;
        this.waypointTarget = waypointHead.getNext();
    }

    public boolean hasActiveWaypoint(){
        return waypointTarget != null;
    }

    protected final GridLine processMove(CarDriver.Move move){
        if(!hasActiveWaypoint()){
            throw new IllegalStateException("CarDriver has no waypoints left");
        }

        GridPoint point2reach = getMovesPoints().get(move);

        final GridLine trace = new GridLine(this.car.getPosition(),point2reach);

        this.car.move(point2reach);

        if(waypointTarget.isWithinRange(trace)){
            System.out.println("Waypoint " +  waypointTarget + " raggiunto.");

            waypointTarget = waypointTarget.getNext();

            System.out.println("Prossimo waypoint: " +  waypointTarget);
        }

        return trace;
    }

    public final Map<CarDriver.Move,GridPoint> getMovesPoints(){
        final Map<CarDriver.Move,GridPoint> movesPoint = new HashMap<>();

        GridPoint pivot = this.car.getPivot();

        movesPoint.put(CarDriver.Move.TL, new GridPoint(pivot.x - 1, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TM, new GridPoint(pivot.x, pivot.y - 1));
        movesPoint.put(CarDriver.Move.TR, new GridPoint(pivot.x + 1, pivot.y - 1));

        movesPoint.put(CarDriver.Move.CL, new GridPoint(pivot.x - 1, pivot.y));
        movesPoint.put(CarDriver.Move.CM, new GridPoint(pivot.x, pivot.y));
        movesPoint.put(CarDriver.Move.CR, new GridPoint(pivot.x + 1, pivot.y));

        movesPoint.put(CarDriver.Move.BL, new GridPoint(pivot.x - 1, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BM, new GridPoint(pivot.x, pivot.y + 1));
        movesPoint.put(CarDriver.Move.BR, new GridPoint(pivot.x + 1, pivot.y + 1));

        return movesPoint;
    }
}
