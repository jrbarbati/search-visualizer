package com.jrbarbati.path;

import java.util.Objects;

public class Node
{
    private Coordinate coordinate;
    private int g;
    private int h;

    public Node(int x, int y, int rawX, int rawY)
    {
        this(new Coordinate(x, y, rawX, rawY));
    }

    public Node(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate()
    {
        return coordinate;
    }

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public int getH()
    {
        return h;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.coordinate);
    }

    @Override
    public boolean equals(Object that)
    {
        return that instanceof Node && this.hashCode() == that.hashCode();
    }
}
