package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.Collection;

public class Stack implements Fringe
{
    java.util.Stack<Node> stack = new java.util.Stack<>();

    @Override
    public void push(Node node)
    {
        stack.push(node);
    }

    @Override
    public Node pop()
    {
        return stack.pop();
    }

    @Override
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    @Override
    public Collection<Node> getFringe()
    {
        return stack;
    }
}
