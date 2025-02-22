package com.gmrossetti.mdp.entity.cartesian;

import java.util.HashSet;
import java.util.Set;

public class Point extends AbstractPoint<Double> {
    public Point(double x, double y) {
        super(x, y);
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("x and y must be finite. x: " + x + ", y: " + y);
        }
    }
    public Point(Point point) {
        super(point.x,point.y);
    }

    public Point sum(Point point2sum){
        if(point2sum == null) return new Point(this);

        double newX = this.x + point2sum.x;
        double newY = this.y + point2sum.y;

        return new Point(newX, newY);
    }

    public Point sub(Point point2sub){
        if(point2sub == null) return new Point(this);

        double newX = this.x - point2sub.x;
        double newY = this.y - point2sub.y;

        return new Point(newX, newY);
    }

    public Set<GridPoint> getNearestDiscretePoints(){
        int floorX = (int) Math.floor(this.x);
        int ceilX = (int) Math.ceil(this.x);

        int floorY = (int) Math.floor(this.y);
        int ceilY = (int) Math.ceil(this.y);

        Set<GridPoint> closestIntegerPoints = new HashSet<>();

        closestIntegerPoints.add(new GridPoint(floorX,floorY));
        closestIntegerPoints.add(new GridPoint(floorX,ceilY));
        closestIntegerPoints.add(new GridPoint(ceilX,floorY));
        closestIntegerPoints.add(new GridPoint(ceilX,ceilY));

        return closestIntegerPoints;
    }
}