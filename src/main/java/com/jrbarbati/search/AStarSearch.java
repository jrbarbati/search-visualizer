package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;

import java.util.Comparator;


public class AStarSearch extends Search
{
    public AStarSearch()
    {
        super(new PriorityQueue(Comparator.comparingInt(node -> node.getG() + node.getH())));
    }

    @Override
    public int calculateHeuristic(Node currentnode, Node endNode)
    {
        return manhattanDistance(currentnode, endNode);
    }

    protected int manhattanDistance(Node startNode, Node endNode)
    {
        return Math.abs(endNode.getCoordinate().x() - startNode.getCoordinate().x())
                + Math.abs(endNode.getCoordinate().y() - startNode.getCoordinate().y());
    }
}
