package com.gmrossetti.mdp.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Point position;
    private final List<Point> trail;

    public Player(GridPoint position) {
        this.position = position;
        this.trail = new ArrayList<>();
        this.trail.add(position);
    }

    public Point getPosition() { return position; }
    public Point getVelocity() {
        if(this.trail.size() < 2){
            return new Point(0,0);
        }

        Point lastPosition = this.trail.get(this.trail.size() - 2);

        return this.position.sub(lastPosition);
    }

    public Point[] getReachablePoints(){
        return this.getPivot().getAdjacentPoints();
    }

    public Point getPivot(){
        return position.sum(this.getVelocity());
    }

    public List<Point> getTrail(){
        return this.trail;
    }

    public void makeMove(Point point2reach){
        Point[] reachablePoints = this.getReachablePoints();

        for (Point rp:
             reachablePoints) {
            if(rp.equals(point2reach)){
                this.position = point2reach;
                this.trail.add(point2reach);

                return;
            }
        }

        throw new IllegalArgumentException("Provided point is not reachable.");
    }
}
