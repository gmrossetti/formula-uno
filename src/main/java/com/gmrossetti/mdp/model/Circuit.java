package com.gmrossetti.mdp.model;

import com.gmrossetti.mdp.ImageToCircuit;

import java.io.IOException;
import java.util.Arrays;

public class Circuit {
    private final GridPoint[][] grid;
    private final GridPoint raceStartGridPoint;

    public Circuit(){
        try {
            this.grid = ImageToCircuit.parseImageToGrid("circuit1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.raceStartGridPoint = findRaceStartPoint(this.grid);
    }

    public Circuit(Circuit circuit){
        this.grid = circuit.grid;
        this.raceStartGridPoint = circuit.raceStartGridPoint;
    }

    public GridPoint getGridPoint(int x, int y) {
        try {
            GridPoint gridPoint = this.grid[y][x];
            return new GridPoint(gridPoint);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new GridPoint(x, y, GridPoint.GridPointType.OUTSIDE);
        }
    }

    public GridPoint getGridPoint(Point point){
        return this.getGridPoint(point.x,point.y);
    }

    public GridPoint getRaceStartPoint(){
        return this.raceStartGridPoint;
    }

    public int getGridWidth(){
        return this.grid[0].length;
    }

    public int getGridHeight(){
        return this.grid.length;
    }

    public void printGridToConsole(){
        System.out.println(Arrays.deepToString(this.grid));
    }

    private static GridPoint findRaceStartPoint(GridPoint[][] grid) {
        GridPoint raceStartPoint = null;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                GridPoint gridPoint = grid[y][x];

                if (gridPoint.type == GridPoint.GridPointType.START) {

                    if(raceStartPoint != null){
                        throw new RuntimeException("Race start point must be unique.");
                    }

                    raceStartPoint = gridPoint;
                }
            }
        }

        if(raceStartPoint == null){
            throw new RuntimeException("Race start point must have 1 start point.");
        }

        return raceStartPoint;
    }
}