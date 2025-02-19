package com.gmrossetti.mdp.driver;

import com.gmrossetti.mdp.actor.Car;
import com.gmrossetti.mdp.entity.GridLine;

import java.util.Random;

public class BotCarDriver extends CarDriver{
    public BotCarDriver(Car car) {
        super(car);
    }

    public final GridLine makeMove(){
        // TODO: add move decision logic (now is random)
        // Seleziona a caso una mossa
        Random random = new Random();
        Move move = Move.values()[random.nextInt(Move.values().length)];

        return super.processMove(move);
    }
}
