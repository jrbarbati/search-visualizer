package com.jrbarbati.search.move;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveTest
{
    @Test
    public void testOrder()
    {
        Move[] moves = Move.values();

        assertEquals(Move.UP, moves[0]);
        assertEquals(Move.RIGHT, moves[1]);
        assertEquals(Move.DOWN, moves[2]);
        assertEquals(Move.LEFT, moves[3]);
    }
}