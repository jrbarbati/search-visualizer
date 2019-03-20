package com.jrbarbati.search;

import com.jrbarbati.gui.SquaresPanel;
import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.path.Path;
import com.jrbarbati.search.fringe.Fringe;
import com.jrbarbati.search.move.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Search
{
    private Fringe fringe;
    private Set<Node> explored;
    private Set<Node> wall;
    private boolean isRunning;
    private Path path;
    private Node startNode;
    private Node endNode;
    private long waitTimeMillis;

    public Search(Fringe fringe)
    {
        this.fringe = fringe;
    }

    protected abstract int calculateHeuristic(Node currentNode, Node endNode);

    public Path execute() throws Exception
    {
        isRunning = true;

        fringe.push(startNode);

        while (!fringe.isEmpty())
        {
            waitFor(waitTimeMillis);
            
            Node currentNode = fringe.pop();

            if (currentNode.equals(endNode))
                return buildPath(startNode, currentNode);

            for (Node node : validMoves(currentNode))
            {
                if (explored.contains(node))
                    continue;

                node.setG(calculateTotalCost(startNode, node));
                node.setH(calculateHeuristic(node, endNode));

                fringe.push(node);
            }

            explored.add(currentNode);
        }

        if (noPathPossible())
            isRunning = false;

        return null;
    }

    private Path buildPath(Node startNode, Node endNode)
    {
        Path path = new Path();
        Node currentNode = endNode;

        do
        {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        while (currentNode.hasParent());

        return path;
    }

    private List<Node> validMoves(Node node)
    {
        List<Node> validNodes = new ArrayList<>();

        for (Move move : Move.values())
        {
            Coordinate coordinate = node.getCoordinate();
            Coordinate potentialCoordinate = new Coordinate(
                    coordinate.x() + move.getXOffset(),
                    coordinate.y() + move.getYOffset(),
                    (coordinate.x() + move.getXOffset()) * SquaresPanel.NODE_SIZE,
                    (coordinate.y() + move.getYOffset()) * SquaresPanel.NODE_SIZE
            );

            Node potentialNode = new Node(potentialCoordinate);

            if (!getWallNodes().contains(potentialNode))
                validNodes.add(potentialNode);
        }

        return validNodes;
    }

    private int calculateTotalCost(Node startNode, Node endNode)
    {
        return buildPath(startNode, endNode).size();
    }

    public void addWallNode(Node node)
    {
        getWallNodes().add(node);
    }

    public void removeWallNode(Node node)
    {
        getWallNodes().remove(node);
    }

    public boolean pathFound()
    {
        return path != null;
    }

    public boolean noPathPossible()
    {
        return fringe.isEmpty() && path == null;
    }

    public boolean isDone()
    {
        return noPathPossible() || pathFound();
    }
    
    private void waitFor(long waitTimeMillis) 
    {
        try 
        {
            Thread.sleep(waitTimeMillis);        
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void reset()
    {
        wall = null;
        fringe = null;
        explored = null;
        path = null;
    }

    public Fringe getFringe()
    {
        return fringe;
    }

    public Set<Node> getExplored()
    {
        if (explored == null)
            explored = new HashSet<>();

        return explored;
    }

    public Set<Node> getWallNodes()
    {
        if (wall == null)
            wall = new HashSet<>();

        return wall;
    }

    public Path getPath()
    {
        return path;
    }

    public Node getStartNode()
    {
        return startNode;
    }

    public void setStartNode(Node node)
    {
        this.startNode = node;
    }

    public Node getEndNode()
    {
        return endNode;
    }

    public void setEndNode(Node node)
    {
        this.endNode = node;
    }
    
    public void setWaitTimeMillis(long waitTimeMillis)
    {
        this.waitTimeMillis = waitTimeMillis;
    }
}
