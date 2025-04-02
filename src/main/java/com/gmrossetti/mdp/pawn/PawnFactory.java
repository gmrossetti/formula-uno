package com.gmrossetti.mdp.pawn;

import com.gmrossetti.mdp.entity.cartesian.GridPoint;

// TODO: add type and support for ipotetical different pawns
public final class PawnFactory {
    public static IPawn buildPawn(GridPoint initialPosition){
        return new Car(initialPosition);
    }
}
