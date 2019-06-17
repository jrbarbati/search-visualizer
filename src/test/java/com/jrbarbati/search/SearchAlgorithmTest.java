package com.jrbarbati.search;

import org.junit.Test;

import static com.jrbarbati.search.SearchAlgorithm.*;
import static org.junit.Assert.*;

public class SearchAlgorithmTest {

    @Test
    public void valueOf_DFS() {
        assertEquals(DFS, SearchAlgorithm.valueOf("DFS"));
    }

    @Test
    public void valueOf_BFS() {
        assertEquals(BFS, SearchAlgorithm.valueOf("BFS"));
    }

    @Test
    public void valueOf_UCS() {
        assertEquals(UCS, SearchAlgorithm.valueOf("UCS"));
    }

    @Test
    public void valueOf_ASTAR() {
        assertEquals(ASTAR, SearchAlgorithm.valueOf("ASTAR"));
    }
}