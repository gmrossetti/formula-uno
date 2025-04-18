package com.gmrossetti.mdp.formulauno.pawn;

import com.gmrossetti.mdp.formulauno.cartesian.GridPoint;

import java.util.List;
import java.util.LinkedList;

/**
 * Represents a car pawn in the game.
 * It implements the IPawn interface and provides methods to manage the car's position, trail, and velocity.
 */
class Car implements IPawn {
    private GridPoint position;
    private final List<GridPoint> trail;

    /**
     * Constructor to create a Car object with an initial position.
     *
     * @param position The initial position of the car as a GridPoint.
     */
    public Car(GridPoint position) {
        this.position = position;
        this.trail = new LinkedList<>();
        this.trail.add(position);
    }

    @Override
    public GridPoint getPosition() { return position; }
    @Override
    public List<GridPoint> getTrail() { return trail; }
    @Override
    public boolean isStationary(){
        return getVelocityModule() == 0;
    }
    @Override
    public GridPoint getVelocityVector() {
        if(this.trail.size() < 2){
            return new GridPoint(0,0);
        }

        GridPoint lastPosition = this.trail.get(this.trail.size() - 2);

        return this.position.sub(lastPosition);
    }
    @Override
    public double getVelocityModule() {
        GridPoint gp = this.getVelocityVector();

        return Math.sqrt(Math.pow(gp.x, 2) + Math.pow(gp.y, 2));
    }
    @Override
    public void move(GridPoint position){
        this.position = position;
        this.trail.add(position);
    }
    @Override
    public final GridPoint getPivot(){
        return this.getPosition().sum(this.getVelocityVector());
    }
}
