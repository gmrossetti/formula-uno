package com.gmrossetti.mdp.model;

import com.gmrossetti.mdp.ImageToCircuit;

import java.io.IOException;
import java.util.*;

public class Circuit {
    private final GridPoint[][] grid;
    private final List<GridPoint> raceStartLine;
    private final GridPoint raceStartGridPoint;

    public Circuit(){
        try {
            this.grid = ImageToCircuit.parseImageToGrid("circuit1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.raceStartLine = new ArrayList<>();
        initRaceStartEndLines();

        raceStartGridPoint = raceStartLine.get(raceStartLine.size() / 2);
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

    private void initRaceStartEndLines() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                GridPoint gridPoint = grid[y][x];

                if (gridPoint.type == GridPoint.GridPointType.START) {
                    this.raceStartLine.add(gridPoint);
                }
            }
        }

        if(raceStartLine.isEmpty()){
            throw new RuntimeException("Race start line not provided.");
        }
    }

    public boolean isValidRoute(Set<Point> routePoints){
        for (Point routePoint:
                routePoints) {
            GridPoint routeGridPoint = this.getGridPoint(routePoint);

            if(routeGridPoint.type == GridPoint.GridPointType.OUTSIDE){
                return false;
            }
        }

        return true;
    }

    public Set<GridPoint> pointsToGridPoints(Set<Point> points){
        Set<GridPoint> gridPoints = new HashSet<>();

        for (Point point:
                points) {
            gridPoints.add(this.getGridPoint(point));
        }

        return gridPoints;
    }
}