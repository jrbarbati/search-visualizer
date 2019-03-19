package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.path.Path;
import com.jrbarbati.search.fringe.Fringe;

import java.util.HashSet;
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

    public Search()
    {
        this(null);
    }

    public Search(Fringe fringe)
    {
        this.fringe = fringe;
    }

    public void execute()
    {
        this.isRunning = true;

        this.fringe.push(this.startNode);

        while (!fringe.isEmpty())
        {
            Node currentNode = this.fringe.pop();
        }

        if (this.noPathPossible())
            this.isRunning = false;
    }

    public int calculateTotalCost(Node startNode, Node currentNode)
    {
        return 0;
    }

    public void addWallNode(Node node)
    {
        this.getWallNodes().add(node);
    }

    public void removeWallNode(Node node)
    {
        this.getWallNodes().remove(node);
    }

    public boolean pathFound()
    {
        return this.path != null;
    }

    public boolean noPathPossible()
    {
        return this.fringe.isEmpty() && this.path == null;
    }

    public boolean isDone()
    {
        return noPathPossible() || pathFound();
    }

    public void reset()
    {
        this.wall = null;
        this.fringe = null;
        this.explored = null;
        this.path = null;
    }

    abstract int calculateHeuristic(Node currentNode, Node endNode);

    public Fringe getFringe()
    {
        return this.fringe;
    }

    public Set<Node> getExplored()
    {
        if (this.explored == null)
            this.explored = new HashSet<>();

        return this.explored;
    }

    public Set<Node> getWallNodes()
    {
        if (this.wall == null)
            this.wall = new HashSet<>();

        return this.wall;
    }

    public Path getPath()
    {
        return path;
    }

    public Node getStartNode()
    {
        return this.startNode;
    }

    public void setStartNode(Node node)
    {
        this.startNode = node;
    }

    public Node getEndNode()
    {
        return this.endNode;
    }

    public void setEndNode(Node node)
    {
        this.endNode = node;
    }
}
