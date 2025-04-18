package com.gmrossetti.mdp.formulauno.driver;

import com.gmrossetti.mdp.formulauno.driver.move.Move;
import com.gmrossetti.mdp.formulauno.cartesian.GridLine;
import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;
import com.gmrossetti.mdp.formulauno.circuit.waypoint.Waypoint;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a driver in the Formula Uno game.
 * It implements the IDriver interface and provides common functionality for all drivers.
 */
abstract class Driver implements IDriver {
    /**
     * Gets the pawn representing the driver.
     *
     * @return the pawn of the driver
     */
    public IPawn getPawn() {
        return pawn;
    }

    private Waypoint waypointTarget;
    private final IPawn pawn;

    /**
     * Constructor for the Driver class.
     *
     * @param pawn the pawn representing the driver
     * @param waypointHead the head of the waypoint list
     */
    public Driver(IPawn pawn, Waypoint waypointHead){
        this.pawn = pawn;
        this.waypointTarget = waypointHead.getNext();
    }
    @Override
    public boolean hasActiveWaypoint(){
        return waypointTarget != null;
    }
    @Override
    public Waypoint getWaypointTarget(){
        return waypointTarget;
    }
    @Override
    public final Map<Move, GridPoint> getMovesPoints(){
        final Map<Move,GridPoint> movesPoint = new HashMap<>();

        GridPoint pivot = this.pawn.getPivot();

        movesPoint.put(Move.TL, new GridPoint(pivot.x - 1, pivot.y - 1));
        movesPoint.put(Move.TM, new GridPoint(pivot.x, pivot.y - 1));
        movesPoint.put(Move.TR, new GridPoint(pivot.x + 1, pivot.y - 1));

        movesPoint.put(Move.CL, new GridPoint(pivot.x - 1, pivot.y));
        movesPoint.put(Move.CM, new GridPoint(pivot.x, pivot.y));
        movesPoint.put(Move.CR, new GridPoint(pivot.x + 1, pivot.y));

        movesPoint.put(Move.BL, new GridPoint(pivot.x - 1, pivot.y + 1));
        movesPoint.put(Move.BM, new GridPoint(pivot.x, pivot.y + 1));
        movesPoint.put(Move.BR, new GridPoint(pivot.x + 1, pivot.y + 1));

        return movesPoint;
    }

    /**
     * Processes a move for the driver.
     *
     * @param move the move to be processed
     * @return the trace of the move
     * @throws IllegalStateException if the driver has no waypoints left
     */
    protected final GridLine processMove(Move move){
        if(!hasActiveWaypoint()){
            throw new IllegalStateException("Driver has no waypoints left");
        }

        GridPoint point2reach = getMovesPoints().get(move);

        final GridLine trace = new GridLine(this.pawn.getPosition(),point2reach);

        this.pawn.move(point2reach);

        if(waypointTarget.isWithinRange(trace)){
            System.out.println("Waypoint " +  waypointTarget + " raggiunto.");

            waypointTarget = waypointTarget.getNext();

            System.out.println("Prossimo waypoint: " +  waypointTarget);
        }

        return trace;
    }
}
