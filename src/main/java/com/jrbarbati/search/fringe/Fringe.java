package com.jrbarbati.search.fringe;

import com.jrbarbati.path.Node;

public interface Fringe
{
    void push(Node node);
    Node pop();
    boolean isEmpty();
}
