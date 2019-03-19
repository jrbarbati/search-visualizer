package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

public class Stack implements Fringe
{
    java.util.Stack<Node> stack = new java.util.Stack<>();

    @Override
    public void push(Node node)
    {
        this.stack.push(node);
    }

    @Override
    public Node pop()
    {
        return this.stack.pop();
    }

    @Override
    public boolean isEmpty()
    {
        return this.stack.isEmpty();
    }
}
