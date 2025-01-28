package com.gmrossetti.mdp.model;

public class Player {
    private Point position;
    private Point lastMove;


    public Point getPosition() { return position; }
    public Point getLastMove() { return lastMove; }

    public void setPosition(GridPoint gridPoint) {
        this.position = new GridPoint(gridPoint);
    }

    /*
    public Point[] getReachablePoints(){
        return this.getPivot().getAdjacentPoints();
    }

    public Point getMovePivot(){
        Point =
    }

    public Player(Point position){
        this.position = position;
        this.lastMove = new Point(position.x, position.y, false);
    }

    public Point getPivot(){
        return position.sum(lastMove);
    }
    public Point[] getReachablePoints(){
        return this.getPivot().getAdjacentPoints();
    }
    public void makeMove(Point point2reach){
        Point[] reachablePoints = this.getReachablePoints();

        for (Point rp:
             reachablePoints) {
            if(rp.equals(point2reach)){
                this.lastMove = point2reach.sub(position);
                this.position = point2reach;

                return;
            }
        }

        throw new IllegalArgumentException("Provided point is not reachable.");
    }*/
}
