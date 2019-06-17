package com.jrbarbati.path;

import java.util.Objects;

public class Node
{
    private Coordinate coordinate;
    private int g;
    private int h;
    private Node parent;

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

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public boolean hasParent()
    {
        return parent != null;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(coordinate, g, h);
    }

    @Override
    public boolean equals(Object that)
    {
        return that instanceof Node && this.hashCode() == that.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("[%s - %d]", getCoordinate().toString(), getG() + getH());
    }
}
