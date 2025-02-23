package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.cartesian.GridLine;
import com.gmrossetti.mdp.entity.cartesian.GridPoint;

import java.util.*;

public class Car {
    private GridPoint position;
    private final List<GridPoint> trail;

    public GridPoint getPosition() { return position; }
    public List<GridPoint> getTrail() { return trail; }
    public GridPoint getPreviousPosition() {
        if(trail.size() == 1){
            return position;
        }

        return trail.get(trail.size() - 2);
    }

    public boolean isStationary(){
        return getVelocityModule() == 0;
    }

    public GridPoint getVelocityVector() {
        if(this.trail.size() < 2){
            return new GridPoint(0,0);
        }

        GridPoint lastPosition = this.trail.get(this.trail.size() - 2);

        return this.position.sub(lastPosition);
    }

    public double getVelocityModule() {
        GridPoint gp = this.getVelocityVector();

        return Math.sqrt(Math.pow(gp.x, 2) + Math.pow(gp.y, 2));
    }

    public Car(GridPoint position) {
        this.position = position;
        this.trail = new LinkedList<>();
        this.trail.add(position);
    }

    public void move(GridPoint position){
        this.position = position;
        this.trail.add(position);
    }

    public final GridPoint getPivot(){
        return this.getPosition().sum(this.getVelocityVector());
    }
}
