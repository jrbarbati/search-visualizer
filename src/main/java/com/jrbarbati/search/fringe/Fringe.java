package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Fringe
{
    void push(Node node);
    Node pop();
    boolean isEmpty();
    Collection<Node> getFringe();

    default List<Node> asList()
    {
        return new ArrayList<>(getFringe());
    }
}
