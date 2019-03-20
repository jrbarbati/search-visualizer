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
    private Path path;
    private Node startNode;
    private Node endNode;
    protected static int MAX_BOUND = 100;
    protected static int MIN_BOUND = 0;

    public Search(Fringe fringe)
    {
        this.fringe = fringe;
    }

    protected abstract int calculateHeuristic(Node currentNode, Node endNode);

    public void setup()
    {
        getFringe().push(startNode);
    }

    public void executeIteration() throws Exception
    {
        Node currentNode = fringe.pop();

        if (isGoal(currentNode))
        {
            setPath(buildPath(startNode, currentNode));
            return;
        }

        for (Node node : validMoves(currentNode))
        {
            if (getExplored().contains(node))
                continue;

            node.setParent(currentNode);
            node.setG(calculateTotalCost(startNode, node));
            node.setH(calculateHeuristic(node, endNode));

            getFringe().push(node);
        }

        getExplored().add(currentNode);
    }

    private boolean isGoal(Node currentNode) {
        return currentNode.equals(endNode);
    }

    protected Path buildPath(Node startNode, Node endNode)
    {
        Path path = new Path();
        Node currentNode = endNode;

        while (currentNode != startNode.getParent())
        {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

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

            if (isValid(potentialNode))
                validNodes.add(potentialNode);
        }

        return validNodes;
    }

    private boolean isValid(Node potentialNode)
    {
        return inBounds(potentialNode) && !getWallNodes().contains(potentialNode);
    }

    protected boolean inBounds(Node potentialNode)
    {
        return potentialNode.getCoordinate().x() >= MIN_BOUND && potentialNode.getCoordinate().y() >= MIN_BOUND
                && potentialNode.getCoordinate().x() < MAX_BOUND && potentialNode.getCoordinate().y() < MAX_BOUND;
    }

    private int calculateTotalCost(Node startNode, Node endNode)
    {
        Path path = buildPath(startNode, endNode);
        return path.size() - 1;
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

    public void setWallNodes(Set<Node> nodes)
    {
        this.wall = nodes;
    }

    public Path getPath()
    {
        return path;
    }

    private void setPath(Path path)
    {
        this.path = path;
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

    public boolean isReady() {
        return startNode != null && endNode != null;
    }

    protected void setFringe(Fringe fringe)
    {
        this.fringe = fringe;
    }
}
