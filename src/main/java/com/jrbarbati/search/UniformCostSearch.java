package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;


public class UniformCostSearch extends Search
{
    public UniformCostSearch()
    {
        super(new PriorityQueue((obj1, obj2) -> Integer.compare(obj2.getG(), obj1.getG())));
    }

    @Override
    public int calculateHeuristic(Node currentNode, Node endNode)
    {
        return 0;
    }
}
