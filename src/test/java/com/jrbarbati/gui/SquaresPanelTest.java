package com.jrbarbati.gui;

import com.jrbarbati.path.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquaresPanelTest
{
    SquaresPanel squaresPanel = new SquaresPanel();

    @Before
    public void setup()
    {
        squaresPanel.setSize(1000, 1000);
    }

    @Test
    public void calculateNodeCoordinate()
    {
        assertEquals(new Coordinate(0, 0, 15, 15), squaresPanel.calculateNodeCoordinate(15, 15));
        assertEquals(new Coordinate(49, 49, 996, 985), squaresPanel.calculateNodeCoordinate(996, 985));
        assertEquals(new Coordinate(8, 11, 174, 233), squaresPanel.calculateNodeCoordinate(174, 233));
    }
}