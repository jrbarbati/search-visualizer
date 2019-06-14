package com.jrbarbati.search;

import com.jrbarbati.gui.SquaresPanel;
import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.path.Path;
import com.jrbarbati.search.fringe.Fringe;
import com.jrbarbati.search.move.Move;

import java.util.*;

public abstract class Search
{
    private Fringe fringe;
    private Set<Node> explored;
    private Set<Node> wall;
    private Path path;
    private Node startNode;
    private Node endNode;
    protected static int MAX_BOUND = 15;
    protected static int MIN_BOUND = 0;

    public Search(Fringe fringe)
    {
        this.fringe = fringe;
    }

    public void setup()
    {
        getFringe().push(startNode);
    }

    public void executeIteration()
    {
        Node currentNode = fringe.pop();

        if (isGoal(currentNode))
        {
            System.out.printf("Found Goal! Goal Node: %s | Current Node: %s\n", endNode, currentNode);
            setPath(buildPath(startNode, currentNode));
            return;
        }

        List<Node> validMoves = validMoves(currentNode);

        System.out.printf("Valid moves for %s - %s\n", currentNode, Arrays.toString(validMoves.toArray(new Node[] {})));

        for (Node node : validMoves)
        {
            if (getExplored().contains(node) || getFringe().asList().contains(node))
                continue;

            node.setParent(currentNode);
            node.setG(calculateTotalCost(startNode, node));
            node.setH(calculateHeuristic(node, endNode));

            getFringe().push(node);
        }

        getExplored().add(currentNode);
    }

    protected boolean isGoal(Node currentNode)
    {
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

    protected boolean isValid(Node potentialNode)
    {
        boolean inBounds = inBounds(potentialNode);
        boolean isWall = getWallNodes().contains(potentialNode);

        System.out.printf("Considering %s: inBounds -> %s | isWall -> %s\n", potentialNode, inBounds, isWall);

        return inBounds && !isWall;
    }

    private boolean inBounds(Node potentialNode)
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

    public void reset()
    {
        startNode = null;
        wall = new HashSet<>();
        fringe.clear();
        explored = new HashSet<>();
        path = null;
        endNode = null;
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

    protected void setPath(Path path)
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

    public boolean isReady()
    {
        return startNode != null && endNode != null;
    }

    public void setFringe(Fringe fringe)
    {
        this.fringe = fringe;
    }

    protected abstract int calculateHeuristic(Node currentNode, Node endNode);
}
