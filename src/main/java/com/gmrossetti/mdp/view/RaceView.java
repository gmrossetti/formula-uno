package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.GameState;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.driver.HumanCarDriver;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class RaceView extends StackPane {
    private CircuitView circuitView;
    private ArrayList<CarView> carViews = new ArrayList<>();

    public RaceView(GameState gameState) {
        this.circuitView = new CircuitView(gameState.getCircuit());

        Rectangle clip = new Rectangle();

        // Usiamo le property per aggiornare il clip dinamicamente
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());

        this.setClip(clip); // Nasconde gli elementi fuori dal limite

        this.setStyle("-fx-padding: 20px;");

        for (CarDriver carDriver:
                gameState.getCarDrivers()) {

            Color color;

            if(carDriver instanceof HumanCarDriver){
                color = Color.YELLOW;
            } else {
                color = Color.MAGENTA;
            }

            Car car = carDriver.getCar();
            CarView carView = new CarView(car, color);

            this.carViews.add(carView);
        }

        this.getChildren().add(this.circuitView);
        this.getChildren().addAll(this.carViews);
    }

    public void update(GameState gameState){
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<CarDriver> carDrivers = new ArrayList<>();

        for (CarDriver carDriver:
                gameState.getCarDrivers()) {
            carDrivers.add(carDriver);
            cars.add(carDriver.getCar());
        }

        // Aggiorna le auto esistenti senza ricrearle
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);

            // Se l'auto esiste già nella view, aggiorniamo solo la posizione
            if (i < this.carViews.size()) {
                this.carViews.get(i).update(car);
            } else {
                // Se ci sono nuove auto, le creiamo e aggiungiamo alla lista
                Color color;

                if(carDrivers.get(i) instanceof HumanCarDriver){
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
