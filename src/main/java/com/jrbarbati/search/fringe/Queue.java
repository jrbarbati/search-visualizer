package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Queue implements Fringe
{
    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> 0);

    @Override
    public void push(Node node)
    {
        queue.add(node);
    }

    @Override
    public Node pop()
    {
        return queue.poll();
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
