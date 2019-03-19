package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.path.Path;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Search
{
    private Collection<Node> fringe;
    private Set<Node> explored;
    private Set<Node> wall;
    private boolean isRunning;
    private Path path;

    public Search()
    {
        this(null);
    }

    public Search(Collection<Node> fringe)
    {
        this.fringe = fringe;
        this.explored = new HashSet<>();
    }

    public void executeIteration(Node startNode, Node endNode)
    {
        this.isRunning = true;

        if (this.noPathPossible())
            this.isRunning = false;
    }

    public int calculateTotalCost(Node startNode, Node currentNode)
    {
        return 0;
    }

    public void addWallNode(Node node)
    {
        this.wall.add(node);
    }

    public boolean pathFound()
    {
        return this.path != null;
    }

    public boolean noPathPossible()
    {
        return this.fringe.isEmpty() && this.path == null;
    }

    public boolean isDone() {
        return noPathPossible() || pathFound();
    }

    abstract int calculateHeuristic(Node currentNode, Node endNode);

    public Collection<Node> getFringe()
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
}
