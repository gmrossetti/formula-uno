package com.gmrossetti.mdp.actor;

import com.gmrossetti.mdp.model.GridPoint;
import com.gmrossetti.mdp.model.Point;
import javafx.util.Pair;

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

    /*public List<Point> getTrajectoryPoints(Point targetPosition){
        Set<Pair<Double, Double>> gridIntersections = Point.getSegmentGridIntersections(this.position,targetPosition);

        Set<Point> descretGridIntersections = new HashSet<>();

        for (Pair<Double, Double> gridIntersection:
                gridIntersections) {
            descretGridIntersections.addAll(Point.getNearestDiscretePoints(gridIntersection));
        }

        return descretGridIntersections.stream().sorted((o1, o2) -> {
            double diff = Point.getDistance(position,o1) - Point.getDistance(position,o2);
            return (diff >= 0) ? (int) Math.ceil(diff) : (int) Math.floor(diff);
        }).toList();
    }*/


    public final GridPoint getPivot(){
        return this.getPosition().sum(this.getVelocity());
    }
}
