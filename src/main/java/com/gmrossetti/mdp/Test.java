package com.gmrossetti.mdp;

import com.gmrossetti.mdp.model.Circuit;
import com.gmrossetti.mdp.model.Point;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Circuit circuit = new Circuit();

        circuit.printGridToConsole();

        System.out.println(circuit.getGridPointCtrl(3,3));

    }
}
