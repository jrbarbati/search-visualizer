package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.Collection;
import java.util.Comparator;

public class PriorityQueue implements Fringe
{
    java.util.PriorityQueue<Node> priorityQueue;

    public PriorityQueue(Comparator<Node> comparator)
    {
        priorityQueue = new java.util.PriorityQueue<>(comparator);
    }

    @Override
    public void push(Node node)
    {
        priorityQueue.add(node);
    }

    @Override
    public Node pop()
    {
        return priorityQueue.poll();
    }

    @Override
    public boolean isEmpty()
    {
        return priorityQueue.isEmpty();
    }

    @Override
    public Collection<Node> getFringe()
    {
        return priorityQueue;
    }
}
