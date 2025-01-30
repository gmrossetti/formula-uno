package com.gmrossetti.mdp.model;

import com.gmrossetti.mdp.controller.GridPointController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Circuit {
    private final GridPointController[][] grid;
    private final GridPoint raceStartGridPoint;

    public Circuit(){
        char[][] gridLoaded = Circuit.loadFromFile("circuit1.dat");

        this.grid = createGrid(gridLoaded);
        this.raceStartGridPoint = findRaceStartPoint(gridLoaded);

        if (this.raceStartGridPoint == null) {
            throw new RuntimeException("Circuit File: does NOT contain the starting point");
        }
    }

    public Circuit(Circuit circuit){
        this.grid = circuit.grid;
        this.raceStartGridPoint = circuit.raceStartGridPoint;
    }

    public GridPointController getGridPointCtrl(int x, int y) {
        try {
            GridPointController gridPointCtrl = this.grid[x][y];
            return new GridPointController(gridPointCtrl.getModel());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new GridPointController(new GridPoint(x, y, GridPoint.GridPointType.OUTSIDE));
        }
    }

    public GridPointController getGridPointCtrl(Point point){
        return this.getGridPointCtrl(point.x,point.y);
    }

    public GridPoint getRaceStartPoint(){
        return this.raceStartGridPoint;
    }

    public int getGridWidth(){
        return this.grid.length;
    }

    public int getGridHeight(){
        return this.grid[0].length;
    }

    public void printGridToConsole(){
        System.out.println(Arrays.deepToString(this.grid));
    }

    private static char[][] loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            int rows = lines.size();
            int cols = lines.get(0).length();

            char[][] gridTemplate = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                gridTemplate[i] = lines.get(i).toCharArray();
            }
            return gridTemplate;
        } catch (IOException e) {
            throw new RuntimeException("Error reading circuit file", e);
        }
    }

    private static GridPointController[][] createGrid(char[][] gridLoaded) {
        GridPointController[][] grid = new GridPointController[gridLoaded.length][gridLoaded[0].length];

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                GridPoint gridPoint = fileCharToGridPoint(gridLoaded[x][y], x, y);
                grid[x][y] = new GridPointController(gridPoint);
            }
        }

        return grid;
    }

    private static GridPoint fileCharToGridPoint(char symbol, int x, int y) {
        return switch (symbol) {
            case 'X' -> new GridPoint(x, y, GridPoint.GridPointType.INSIDE);
            case 'O' -> new GridPoint(x, y, GridPoint.GridPointType.OUTSIDE);
            case 'S' -> new GridPoint(x, y, GridPoint.GridPointType.START);
            default -> throw new RuntimeException("Circuit File: contains invalid symbols");
        };
    }

    private static GridPoint findRaceStartPoint(char[][] gridLoaded) {
        for (int x = 0; x < gridLoaded.length; x++) {
            for (int y = 0; y < gridLoaded[x].length; y++) {
                if (gridLoaded[x][y] == 'S') {
                    return new GridPoint(x, y, GridPoint.GridPointType.START);
                }
            }
        }
        return null;
    }
}