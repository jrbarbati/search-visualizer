package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;


public class AStarSearch extends Search
{
    public AStarSearch()
    {
        // TODO: Write comparator for PQ
        super(new PriorityQueue((obj1, obj2) -> {
            return 0;
        }));
    }

    @Override
    int calculateHeuristic(Node currentnode, Node endNode)
    {
        return 0;
    }
}
