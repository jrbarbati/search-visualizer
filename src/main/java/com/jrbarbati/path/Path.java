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
        this.path.add(node);
    }

    public boolean hasNext()
    {
        return this.size() > this.currentIndex + 1;
    }

    public Node next()
    {
        if (this.currentIndex + 1 >= this.size())
            return null;

        return this.path.get(++this.currentIndex);
    }

    public int size()
    {
        return this.path.size();
    }
}
