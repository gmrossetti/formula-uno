package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.circuit.ICircuit;
import com.gmrossetti.mdp.actor.IPawn;
import com.gmrossetti.mdp.actor.PawnFactory;
import com.gmrossetti.mdp.strategy.IStrategy;
import com.gmrossetti.mdp.strategy.StrategyFactory;
import com.gmrossetti.mdp.strategy.StrategyParameters;

public class BotCarDriverFactory {
    public static BotCarDriver build(ICircuit circuit, StrategyParameters strategyParameters){
        IPawn botCar = PawnFactory.buildPawn(circuit.getRaceStartPoint());
        IStrategy strategy = StrategyFactory.buildStrategy(strategyParameters);

        return new BotCarDriver(botCar, circuit, strategy);
    }
}
