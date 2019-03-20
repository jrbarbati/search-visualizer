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

    /**
     * Goes beyond {@code Coordinate.equals()} and checks each individual value on each object to be sure they are equal
     * {@code Coordinate.equals()} only checks if the (x,y) for each {@code Coordinate} is the same rather than the
     * entire object
     * Used in tests
     * @param that {@code Coordinate} to compare to
     * @return true if x, y, rawX, rawY are all equivalent on {@code this} and {@code that}, false otherwise.
     */
    public boolean deepEquals(Coordinate that)
    {
        return this.x() == that.x() && this.y() == that.y() && this.rawX() == that.rawX() && this.rawY() == this.rawY();
    }

    @Override
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }
}
