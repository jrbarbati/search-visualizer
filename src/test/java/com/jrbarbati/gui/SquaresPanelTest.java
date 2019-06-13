package com.jrbarbati.gui;

import com.jrbarbati.path.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SquaresPanelTest
{
    private SquaresPanel squaresPanel = new SquaresPanel();

    @Before
    public void setup()
    {
        squaresPanel.setSize(1000, 1000);
    }

    @Test
    public void calculateNodeCoordinate()
    {
        assertTrue(new Coordinate(0 , 0 , 0  , 0  ).deepEquals(squaresPanel.calculateNodeCoordinate(15 , 15 )));
        assertTrue(new Coordinate(49, 49, 980, 980).deepEquals(squaresPanel.calculateNodeCoordinate(996, 985)));
        assertTrue(new Coordinate(8 , 11, 160, 220).deepEquals(squaresPanel.calculateNodeCoordinate(174, 233)));
    }
}