package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;


public class AStarSearch extends Search
{
    public AStarSearch()
    {
        super(new PriorityQueue((obj1, obj2) -> Integer.compare(obj2.getG() + obj2.getH(), obj1.getG() + obj1.getH())));
    }

    @Override
    public int calculateHeuristic(Node currentnode, Node endNode)
    {
        return manhattanDistance(currentnode, endNode);
    }

    protected int manhattanDistance(Node startNode, Node endNode)
    {
        return (endNode.getCoordinate().x() - startNode.getCoordinate().x())
                + (endNode.getCoordinate().y() - startNode.getCoordinate().y());
    }
}
