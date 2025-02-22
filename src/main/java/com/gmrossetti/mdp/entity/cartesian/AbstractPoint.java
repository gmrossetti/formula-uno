package com.gmrossetti.mdp.entity.cartesian;

import java.util.Objects;

public abstract class AbstractPoint<T extends Number> {
    public final T x;
    public final T y;

    public AbstractPoint(T x, T y) {
        if (!(x instanceof Integer || x instanceof Double)) {
            throw new IllegalArgumentException("Only Integer and Double are allowed");
        }

        this.x = x;
        this.y = y;
    }
    public double distanceTo(AbstractPoint<?> other) {
        return Math.sqrt(Math.pow(other.x.doubleValue() - this.x.doubleValue(), 2)
                + Math.pow(other.y.doubleValue() - this.y.doubleValue(), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPoint<?> that)) return false;
        return x.equals(that.x) && y.equals(that.y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}