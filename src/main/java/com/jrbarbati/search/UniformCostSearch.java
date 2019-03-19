package com.jrbarbati.search;

import com.jrbarbati.path.Node;

import java.util.PriorityQueue;

public class UniformCostSearch extends Search
{
    public UniformCostSearch()
    {
        // TODO: Write comparator for PQ
        super(new PriorityQueue<>());
    }

    @Override
    int calculateHeuristic(Node currentNode, Node endNode) {
        return 0;
    }
}
