package com.gmrossetti.mdp.entity;

import java.util.HashSet;
import java.util.Set;

public class GridPoint extends AbstractPoint<Integer> {
    public GridPoint(int x, int y) {
        super(Integer.valueOf(x), Integer.valueOf(y));
    }
    public GridPoint(GridPoint gridPoint) {
        super(gridPoint.x, gridPoint.y);
    }

    public GridPoint sum(GridPoint point2sum){
        if(point2sum == null) return new GridPoint(this);

        int newX = this.x + point2sum.x;
        int newY = this.y + point2sum.y;

        return new GridPoint(newX, newY);
    }

    public GridPoint sub(GridPoint point2sub){
        if(point2sub == null) return new GridPoint(this);

        int newX = this.x - point2sub.x;
        int newY = this.y - point2sub.y;

        return new GridPoint(newX, newY);
    }

    public Set<GridPoint> gridPointsInRange(int radius) {
        Set<GridPoint> pointsInradius = new HashSet<>();

        final int x0 = this.x;
        final int y0 = this.y;

        // Esplora i punti nel cerchio di radius R
        for (int x = x0 - radius; x <= x0 + radius; x++) {
            for (int y = y0 - radius; y <= y0 + radius; y++) {
                // Controlla se il punto (x, y) è all'interno del cerchio
                if ((Math.pow(x - x0, 2) + Math.pow(y - y0, 2)) <= Math.pow(radius, 2)) {
                    pointsInradius.add(new GridPoint(x, y));
                }
            }
        }

        return pointsInradius;
    }
}
