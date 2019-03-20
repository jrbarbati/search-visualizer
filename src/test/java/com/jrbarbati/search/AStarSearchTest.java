package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AStarSearchTest
{
    AStarSearch aStarSearch = new AStarSearch();

    @Test
    public void manhattanDistance()
    {
        assertEquals(1, aStarSearch.manhattanDistance(
                new Node(0, 0, 0, 0),
                new Node(0, 1, 0, 0)
        ));
        assertEquals(16, aStarSearch.manhattanDistance(
                new Node( 0, 0, 0, 0),
                new Node(13, 3, 0, 0)
        ));
        assertEquals(16, aStarSearch.manhattanDistance(
                new Node(13, 3, 0, 0),
                new Node( 0, 0, 0, 0)
        ));
    }
}