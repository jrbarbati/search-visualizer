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
        return this.x;
    }

    public int y()
    {
        return this.y;
    }

    public int rawX()
    {
        return this.rawX;
    }

    public int rawY()
    {
        return this.rawY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object that) {
        return that instanceof Coordinate && this.hashCode() == that.hashCode();
    }

    /*
     * For assertEquals in tests
     */
    @Override
    public String toString()
    {
        return String.format("(%d, %d)", this.x, this.y);
    }
}
