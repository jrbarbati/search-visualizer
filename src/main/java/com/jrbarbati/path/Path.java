package com.jrbarbati.path;

import java.util.ArrayList;
import java.util.List;

public class Path
{
    private List<Node> path;
    private int currentIndex;

    public Path()
    {
        this.path = new ArrayList<>();
        this.currentIndex = -1;
    }

    public void add(Node node)
    {
        path.add(0, node);
    }

    public boolean hasNext()
    {
        return size() > currentIndex + 1;
    }

    public Node next()
    {
        if (currentIndex + 1 >= size())
            return null;

        return path.get(++currentIndex);
    }

    public List<Node> asList()
    {
        if (path == null)
            path = new ArrayList<>();

        return path;
    }

    public int size()
    {
        return path.size();
    }

    public void clear() {
        path.clear();
        currentIndex = -1;
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }
}
