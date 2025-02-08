package com.gmrossetti.mdp.model;

import javafx.util.Pair;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point[] getAdjacentPoints(){
        Point[] adjacentPoints = new Point[8];

        int i = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if(j == 1 && k == 1)
                    continue;

                int currentX = this.x + 1 - j;
                int currentY = this.y + 1 - k;

                adjacentPoints[i++] = new Point(currentX, currentY);
            }
        }

        return adjacentPoints;
    }

    public Point sum(Point point2sum){
        if(point2sum == null) return new Point(this);

        int newX = this.x + point2sum.x;
        int newY = this.y + point2sum.y;

        return new Point(newX, newY);
    }
    public Point sub(Point point2sub){
        int newX = this.x - point2sub.x;
        int newY = this.y - point2sub.y;

        return new Point(newX, newY);
    }
    public static Pair<Double,Double> calcIntersectionCoords(Pair<Point,Point> lineA, Pair<Point,Point> lineB) {
        double x1 = lineA.getKey().x;
        double y1 = lineA.getKey().y;
        double x2 = lineA.getValue().x;
        double y2 = lineA.getValue().y;

        double x3 = lineB.getKey().x;
        double y3 = lineB.getKey().y;
        double x4 = lineB.getValue().x;
        double y4 = lineB.getValue().y;

        // Calcola i parametri della retta 1 (linea 1)
        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denom == 0) {
            // Le linee sono parallele, non hanno intersezione
            return null; // oppure gestire l'errore come preferisci
        }

        // Calcola il punto di intersezione
        double intersectX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denom;
        double intersectY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denom;

        return new Pair<>(intersectX, intersectY);
    }

    public static Set<Point> findClosestIntegerPoints(Pair<Double,Double> coords){
        int floorX = (int) Math.floor(coords.getKey());
        int ceilX = (int) Math.ceil(coords.getKey());

        int floorY = (int) Math.floor(coords.getValue());
        int ceilY = (int) Math.ceil(coords.getValue());

        Set<Point> closestIntegerPoints = new HashSet<>();

        closestIntegerPoints.add(new Point(floorX,floorY));
        closestIntegerPoints.add(new Point(floorX,ceilY));
        closestIntegerPoints.add(new Point(ceilX,floorY));
        closestIntegerPoints.add(new Point(ceilX,ceilY));

        return closestIntegerPoints;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Point that = (Point) other;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}