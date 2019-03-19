package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.Comparator;

public class PriorityQueue implements Fringe
{
    java.util.PriorityQueue<Node> priorityQueue;

    public PriorityQueue(Comparator<Node> comparator)
    {
        this.priorityQueue = new java.util.PriorityQueue<>(comparator);
    }

    @Override
    public void push(Node node)
    {
        this.priorityQueue.add(node);
    }

    @Override
    public Node pop()
    {
        return this.priorityQueue.poll();
    }

    @Override
    public boolean isEmpty()
    {
        return this.priorityQueue.isEmpty();
    }
}
