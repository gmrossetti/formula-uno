package com.gmrossetti.mdp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Circuit {
    private final Point[][] grid;
    private final Point raceStartPoint;

    public Circuit(){
        char[][] gridLoaded = Circuit.loadFromFile("circuit1.dat");

        this.grid = new Point[gridLoaded.length][gridLoaded[0].length];

        Point tempStart = null;

        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {

                Point point;
                switch (gridLoaded[x][y]){
                    case 'X':
                        point = new Point(x,y, Point.PointType.INSIDE);
                        break;

                    case 'O':
                        point = new Point(x,y, Point.PointType.OUTSIDE);
                        break;

                    case 'S':
                        point = new Point(x,y, Point.PointType.START);
                        tempStart = point;
                        break;

                    default:
                        throw new RuntimeException("Circuit File: contains invalid symbols");
                }

                this.grid[x][y] = point;
            }
        }

        if(tempStart == null) throw new RuntimeException("Circuit File: does NOT contain the starting point");

        this.raceStartPoint = tempStart;
    }

    public Point getGridPoint(int x, int y){
        Point pointRequested = this.grid[x][y];
        return (pointRequested == null) ? null : new Point(pointRequested);
    }

    public Point getRaceStartPoint(){
        return this.raceStartPoint;
    }

    public Point getGridPoint(Point point){
        Point pointRequested = this.grid[point.x][point.y];
        return (pointRequested == null) ? null : new Point(pointRequested);
    }

    /*public void setGridPointIsBusy(Point point){
        Point pointRequested = this.grid[point.x][point.y];
        pointRequested.setOccupiedBy(point.isBusy());
    }*/

    public int getGridWidth(){
        return this.grid.length;
    }

    public int getGridHeight(){
        return this.grid[0].length;
    }

    public void printGridToConsole(){
        System.out.println(Arrays.deepToString(this.grid));
    }

    public static char[][] loadFromFile(String fileName){
        // TODO: validate file format

        char[][] gridTemplate;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Leggi la prima riga del file
            String line = reader.readLine();

            // Determina la dimensione della matrice
            int rows = 0;
            int cols = 0;

            while (line != null) {
                rows++;
                cols = line.length();
                line = reader.readLine();
            }

            // Riposizionamento del BufferedReader all'inizio del file
            reader.close();

            // Ora inizializziamo la matrice
            gridTemplate = new char[rows][cols];

            // Riapriamo il file per leggere nuovamente riga per riga
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
            int rowIndex = 0;
            while ((line = reader2.readLine()) != null) {
                for (int colIndex = 0; colIndex < line.length(); colIndex++) {
                    gridTemplate[rowIndex][colIndex] = line.charAt(colIndex);
                }
                rowIndex++;
            }
            reader2.close();

            // Visualizza la matrice letta
            System.out.println("Matrice letta dal file:");
            for (int i = 0; i < gridTemplate.length; i++) {
                for (int j = 0; j < gridTemplate[i].length; j++) {
                    System.out.print(gridTemplate[i][j]);
                }
                System.out.println();
            }

            return gridTemplate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
