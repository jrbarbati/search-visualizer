package com.jrbarbati.search.factory;

import com.jrbarbati.search.AStarSearch;
import com.jrbarbati.search.BreadthFirstSearch;
import com.jrbarbati.search.DepthFirstSearch;
import com.jrbarbati.search.UniformCostSearch;
import com.jrbarbati.search.factory.exception.UnableToConstructSearchException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SearchFactoryTest
{
    @Test
    public void getSearch()
    {
        assertTrue(SearchFactory.getByName("DFS") instanceof DepthFirstSearch);
        assertTrue(SearchFactory.getByName("bfs") instanceof BreadthFirstSearch);
        assertTrue(SearchFactory.getByName("UCS") instanceof UniformCostSearch);
        assertTrue(SearchFactory.getByName("ASTAR") instanceof AStarSearch);
    }

    @Test(expected = UnableToConstructSearchException.class)
    public void getSearch_nonExistent()
    {
        SearchFactory.getByName("INVALID SEARCH");
    }
}