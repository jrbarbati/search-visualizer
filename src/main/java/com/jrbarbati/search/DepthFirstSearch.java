package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.Stack;


public class DepthFirstSearch extends Search
{
    public DepthFirstSearch()
    {
        super(new Stack());
    }

    @Override
    int calculateHeuristic(Node currentNode, Node endNode)
    {
        return 0;
    }
}
