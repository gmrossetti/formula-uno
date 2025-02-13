package com.gmrossetti.mdp.view;

import com.gmrossetti.mdp.core.GameState;
import com.gmrossetti.mdp.driver.CarDriver;
import com.gmrossetti.mdp.actor.Car;
import javafx.scene.Group;

import java.util.ArrayList;

public class RaceView extends Group {
    private CircuitView circuitView;
    private ArrayList<CarView> carViews = new ArrayList<>();

    public RaceView(GameState gameState) {
        this.circuitView = new CircuitView(gameState.getCircuit());

        for (CarDriver carDriver:
                gameState.getCarDrivers()) {

            Car car = carDriver.getCar();
            CarView carView = new CarView(car);

            this.carViews.add(carView);
        }

        this.getChildren().add(this.circuitView);
        this.getChildren().addAll(this.carViews);
    }

    public void update(GameState gameState){
        // TODO: implement support to circuit change

        ArrayList<Car> cars = new ArrayList<>();

        for (CarDriver carDriver:
                gameState.getCarDrivers()) {
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
                CarView carView = new CarView(car);
                this.carViews.add(carView);
                this.getChildren().add(carView);
            }
        }
    }
}
