package com.gmrossetti.mdp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Circuit {
    private final GridPoint[][] grid;
    private final GridPoint raceStartGridPoint;

    public Circuit(){
        char[][] gridLoaded = Circuit.loadFromFile("circuit1.dat");

        this.grid = new GridPoint[gridLoaded.length][gridLoaded[0].length];

        GridPoint tempStart = null;

        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {

                GridPoint gridPoint;
                switch (gridLoaded[x][y]){
                    case 'X':
                        gridPoint = new GridPoint(x,y, GridPoint.GridPointType.INSIDE);
                        break;

                    case 'O':
                        gridPoint = new GridPoint(x,y, GridPoint.GridPointType.OUTSIDE);
                        break;

                    case 'S':
                        gridPoint = new GridPoint(x,y, GridPoint.GridPointType.START);
                        tempStart = gridPoint;
                        break;

                    default:
                        throw new RuntimeException("Circuit File: contains invalid symbols");
                }

                this.grid[x][y] = gridPoint;
            }
        }

        if(tempStart == null) throw new RuntimeException("Circuit File: does NOT contain the starting point");

        this.raceStartGridPoint = tempStart;
    }

    public GridPoint getGridPoint(int x, int y){
        GridPoint gridPoint = this.grid[x][y];
        return (gridPoint == null) ? null : new GridPoint(gridPoint);
    }
    public GridPoint getGridPoint(GridPoint gridPoint){
        return this.getGridPoint(gridPoint.x,gridPoint.y);
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
