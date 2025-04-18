package com.gmrossetti.mdp.formulauno.view;

import com.gmrossetti.mdp.formulauno.core.GameState;
import com.gmrossetti.mdp.formulauno.driver.IDriver;
import com.gmrossetti.mdp.formulauno.pawn.IPawn;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * RaceView is a JavaFX StackPane that represents the race track and the cars on it.
 * It displays the circuit and the cars, and updates their positions based on the game state.
 */
public class RaceView extends StackPane {
    private CircuitView circuitView;
    private ArrayList<CarView> carViews = new ArrayList<>();

    /**
     * Constructor for RaceView.
     * @param gameState The GameState object that contains the current state of the game.
     */
    public RaceView(GameState gameState) {
        this.circuitView = new CircuitView(gameState.getCircuit());

        Rectangle clip = new Rectangle();

        // Usiamo le property per aggiornare il clip dinamicamente
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());

        this.setClip(clip); // Nasconde gli elementi fuori dal limite

        this.setStyle("-fx-padding: 20px;");

        for (IDriver driver:
                gameState.getDrivers()) {

            Color color;

            if(driver.equals(gameState.getHumanDriver())){
                color = Color.YELLOW;
            } else {
                color = Color.MAGENTA;
            }

            IPawn car = driver.getPawn();
            CarView carView = new CarView(car, color);

            this.carViews.add(carView);
        }

        this.getChildren().add(this.circuitView);
        this.getChildren().addAll(this.carViews);
    }

    /**
     * Updates the RaceView with the current game state.
     * It updates the positions of the cars and adds new cars if necessary.
     * @param gameState The GameState object that contains the current state of the game.
     */
    public void update(GameState gameState){
        ArrayList<IPawn> cars = new ArrayList<>();
        ArrayList<IDriver> drivers = new ArrayList<>();

        for (IDriver driver:
                gameState.getDrivers()) {
            drivers.add(driver);
            cars.add(driver.getPawn());
        }

        // Aggiorna le auto esistenti senza ricrearle
        for (int i = 0; i < cars.size(); i++) {
            IPawn car = cars.get(i);

            // Se l'auto esiste già nella view, aggiorniamo solo la posizione
            if (i < this.carViews.size()) {
                this.carViews.get(i).update(car);
            } else {
                // Se ci sono nuove auto, le creiamo e aggiungiamo alla lista
                Color color;

                if(drivers.get(i).equals(gameState.getHumanDriver())){
                    color = Color.YELLOW;
                } else {
                    color = Color.MAGENTA;
                }
                CarView carView = new CarView(car, color);
                this.carViews.add(carView);
                this.getChildren().add(carView);
            }
        }
    }
}
