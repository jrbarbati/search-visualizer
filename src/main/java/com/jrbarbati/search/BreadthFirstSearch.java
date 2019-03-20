package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.Queue;

public class BreadthFirstSearch extends Search {

    public BreadthFirstSearch()
    {
        super(new Queue());
    }

    @Override
    public int calculateHeuristic(Node currentNode, Node endNode)
    {
        return 0;
    }
}
