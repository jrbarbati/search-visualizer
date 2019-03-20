package com.jrbarbati.path;

import java.util.Objects;

public class Coordinate
{
    private int x;
    private int rawX;
    private int y;
    private int rawY;

    public Coordinate(int x, int y, int rawX, int rawY)
    {
        this.x = x;
        this.y = y;
        this.rawX = rawX;
        this.rawY = rawY;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public int rawX()
    {
        return rawX;
    }

    public int rawY()
    {
        return rawY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object that) {
        return that instanceof Coordinate && hashCode() == that.hashCode();
    }

    /*
     * For assertEquals in tests
     */
    @Override
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }
}
