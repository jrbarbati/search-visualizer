package com.jrbarbati.path;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest
{
    @Test
    public void hasNext()
    {
        Path path = new Path();
        assertFalse(path.hasNext());

        path.add(new Node(0, 0, 0 , 0));
        assertTrue(path.hasNext());
    }

    @Test
    public void next()
    {
        Path path = new Path();
        assertNull(path.next());

        path.add(new Node(0, 0, 0, 0));
        assertEquals(new Node(0, 0, 0, 0), path.next());
    }
}