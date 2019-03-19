package com.jrbarbati.search.factory;

import com.jrbarbati.search.AStarSearch;
import com.jrbarbati.search.BreadthFirstSearch;
import com.jrbarbati.search.DepthFirstSearch;
import com.jrbarbati.search.UniformCostSearch;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SearchFactoryTest
{
    @Test
    public void getSearch()
    {
        SearchFactory searchFactory = new SearchFactory();
        assertTrue(searchFactory.getSearch("DFS") instanceof DepthFirstSearch);
        assertTrue(searchFactory.getSearch("bfs") instanceof BreadthFirstSearch);
        assertTrue(searchFactory.getSearch("UCS") instanceof UniformCostSearch);
        assertTrue(searchFactory.getSearch("ASTAR") instanceof AStarSearch);
    }
}