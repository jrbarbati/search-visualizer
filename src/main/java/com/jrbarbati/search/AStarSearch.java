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
        return absoluteDifferenceX(startNode, endNode) + absoluteDifferenceY(startNode, endNode);
    }

    private int absoluteDifferenceX(Node start, Node end)
    {
        return Math.abs(end.getCoordinate().x() - start.getCoordinate().x());
    }

    private int absoluteDifferenceY(Node start, Node end)
    {
        return Math.abs(end.getCoordinate().y() - start.getCoordinate().y());
    }
}
