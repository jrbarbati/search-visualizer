package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.ArrayList;
import java.util.Collection;

public class Stack implements Fringe
{
    private ArrayList<Node> stack = new ArrayList<>();

    @Override
    public void push(Node node)
    {
        stack.add(node);
    }

    @Override
    public Node pop()
    {
        return stack.remove(stack.size() - 1);
    }

    @Override
    public void clear()
    {
        stack.clear();
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
