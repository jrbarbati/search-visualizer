package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;

import java.util.Comparator;


public class UniformCostSearch extends Search
{
    public UniformCostSearch()
    {
        super(new PriorityQueue(Comparator.comparingInt(Node::getG)));
    }

    @Override
    public int calculateHeuristic(Node currentNode, Node endNode)
    {
        return 0;
    }
}
