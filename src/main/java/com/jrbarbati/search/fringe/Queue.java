package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.ArrayList;
import java.util.Collection;

public class Queue implements Fringe
{
    private ArrayList<Node> queue = new ArrayList<>();

    @Override
    public void push(Node node)
    {
        queue.add(node);
    }

    @Override
    public Node pop()
    {
        return queue.remove(0);
    }

    @Override
    public void clear()
    {
        queue.clear();
    }

    @Override
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    @Override
    public Collection<Node> getFringe()
    {
        return queue;
    }
}
