package com.jrbarbati.search.move;

public enum Move
{
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    private int xOffset;
    private int yOffset;

    Move(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getXOffset()
    {
        return xOffset;
    }

    public int getYOffset()
    {
        return yOffset;
    }
}
