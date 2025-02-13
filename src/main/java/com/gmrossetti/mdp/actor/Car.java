package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.model.Point;

import java.util.List;
import java.util.LinkedList;

public class Car {
    private Point position;
    private final List<Point> trail;

    public Point getPosition() { return position; }
    public List<Point> getTrail() { return trail; }

    public Point getVelocity() {
        if(this.trail.size() < 2){
            return new Point(0,0);
        }

        Point lastPosition = this.trail.get(this.trail.size() - 2);

        return this.position.sub(lastPosition);
    }

    public Car(Point position) {
        this.position = position;
        this.trail = new LinkedList<>();
        this.trail.add(position);
    }

    public void move(Point position){
        this.position = position;
        this.trail.add(position);
    }
}
