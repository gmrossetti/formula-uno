package com.gmrossetti.mdp.formulauno.cartesian;

public class GridPoint extends AbstractPoint<Integer> {
    public GridPoint(int x, int y) {
        super(x, y);
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
}
