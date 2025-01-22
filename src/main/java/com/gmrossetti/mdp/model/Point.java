package com.gmrossetti.mdp.model;

public class Point {

    public enum PointType {
        OUTSIDE,  // Fuori mappa
        INSIDE,           // Dentro la mappa
        START
    }
    public final int x;
    public final int y;
    private Player occupiedBy;
    public final PointType type;

    public boolean isBusy() {
        return this.occupiedBy != null;
    }
    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public Point(int x, int y, PointType type) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.occupiedBy = null;
    }
    public Point(int x, int y, PointType type, Player occupiedBy) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.occupiedBy = occupiedBy;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.type = point.type;
        this.occupiedBy = point.occupiedBy;
    }
    /*public Point[] getAdjacentPoints(){
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
    }*/
    /*public Point sum(Point point2sum){
        int newX = this.x + point2sum.x;
        int newY = this.y + point2sum.y;

        return new Point(newX, newY);
    }
    public Point sub(Point point2sub){
        int newX = this.x - point2sub.x;
        int newY = this.y - point2sub.y;

        return new Point(newX, newY);
    }*/
    public boolean equals(Point p) {
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}