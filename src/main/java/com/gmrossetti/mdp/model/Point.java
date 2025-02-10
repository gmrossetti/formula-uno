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

    public static Double getLineSlopeCoefficient(Pair<Point,Point> line){
        double deltaY = line.getKey().y - line.getValue().y;
        double deltaX = line.getKey().x - line.getValue().x;

        return deltaY / deltaX;
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

    // Metodo che calcola i punti di intersezione intermedi
    public static Set<Pair<Double,Double>> calculateIntersectionCoords(Point p1, Point p2) {
        final Set<Pair<Double,Double>> intersectionPoints = new HashSet<>();

        double m = getLineSlopeCoefficient(new Pair<>(p1, p2));
        double b = p1.y - m * p1.x;

        final int MIN_X = Math.min(p1.x, p2.x);
        final int MAX_X = Math.max(p1.x, p2.x);

        final int MIN_Y = Math.min(p1.y, p2.y);
        final int MAX_Y = Math.max(p1.y, p2.y);

        if(m == 0){
            for (int xi = MIN_X; xi <= MAX_X; xi++) {
                intersectionPoints.add(new Pair<>((double)xi, (double)p1.y));
            }
        } else if(m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY){
            for (int yi = MIN_Y; yi <= MAX_Y; yi++) {
                intersectionPoints.add(new Pair<>((double)p1.x, (double)yi));
            }
        } else {
            for (int xi = MIN_X; xi <= MAX_X; xi++) {
                double yi = m * xi + b;
                intersectionPoints.add(new Pair<>((double)xi, yi));
            }

            for (int yi = MIN_Y; yi <= MAX_Y; yi++) {
                double xi = (yi - b) / m;
                intersectionPoints.add(new Pair<>(xi, (double)yi));
            }
        }

        return intersectionPoints;
    }

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
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