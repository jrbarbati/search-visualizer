package com.jrbarbati.search;

import com.jrbarbati.path.Node;

import java.util.PriorityQueue;

public class AStarSearch extends Search
{
    public AStarSearch()
    {
        // TODO: Write comparator for PQ
        super(new PriorityQueue<>());
    }

    @Override
    int calculateHeuristic(Node currentnode, Node endNode)
    {
        return 0;
    }
}
