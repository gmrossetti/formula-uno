package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.actor.Circuit;
import com.gmrossetti.mdp.strategy.IStrategy;
import com.gmrossetti.mdp.strategy.StrategyFactory;
import com.gmrossetti.mdp.strategy.StrategyParameters;

public class BotCarDriverFactory {
    public static BotCarDriver build(Circuit circuit, StrategyParameters strategyParameters){
        Car botCar = new Car(circuit.getRaceStartPoint());
        IStrategy strategy = StrategyFactory.buildStrategy(strategyParameters);

        return new BotCarDriver(botCar, circuit, strategy);
    }
}
