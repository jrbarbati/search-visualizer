package com.jrbarbati.gui;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuiTest
{
    Gui gui = new Gui();

    @Test
    public void calculateWaitTime()
    {
        assertEquals(0, gui.calculateWaitTime(0));
        assertEquals(300, gui.calculateWaitTime(10));
        assertEquals(1500, gui.calculateWaitTime(50));
    }
}