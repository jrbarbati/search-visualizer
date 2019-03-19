package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.PriorityQueue;

public class Queue implements Fringe
{
    PriorityQueue<Node> queue = new PriorityQueue<>();

    @Override
    public void push(Node node)
    {
        this.queue.add(node);
    }

    @Override
    public Node pop()
    {
        return this.queue.poll();
    }

    @Override
    public boolean isEmpty()
    {
        return this.queue.isEmpty();
    }
}
