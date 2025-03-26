package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;

public class HumanCarDriverFactory {
    private static final int MAX_INSTANCE_COUNT = 1;
    private static int instanceCount = 0;
    public static HumanCarDriver build(Circuit circuit){
        if(instanceCount >= MAX_INSTANCE_COUNT)
            throw new IllegalStateException("Cannot build more than " + MAX_INSTANCE_COUNT + " HumanCarDriver instances.");

        Car car = new Car(circuit.getRaceStartPoint());
        HumanCarDriver humanCarDriver = new HumanCarDriver(car, circuit);

        instanceCount++;

        return humanCarDriver;
    }
}
