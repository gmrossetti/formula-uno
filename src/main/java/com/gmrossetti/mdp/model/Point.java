package com.gmrossetti.mdp.model;

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