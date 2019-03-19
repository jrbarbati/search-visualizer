package com.jrbarbati.search;

import com.jrbarbati.path.Node;

import java.util.PriorityQueue;

public class BreadthFirstSearch extends Search {

    public BreadthFirstSearch()
    {
        super(new PriorityQueue<>());
    }

    @Override
    int calculateHeuristic(Node currentNode, Node endNode) {
        return 0;
    }
}
