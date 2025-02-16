package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.entity.GridPoint;

import java.util.*;

public class Car {
    private GridPoint position;
    private final List<GridPoint> trail;

    public GridPoint getPosition() { return position; }
    public List<GridPoint> getTrail() { return trail; }

    public GridPoint getVelocity() {
        if(this.trail.size() < 2){
            return new GridPoint(0,0);
        }

        GridPoint lastPosition = this.trail.get(this.trail.size() - 2);

        return this.position.sub(lastPosition);
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
        return this.getPosition().sum(this.getVelocity());
    }
}
