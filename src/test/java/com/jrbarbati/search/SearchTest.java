package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;
import com.jrbarbati.search.fringe.Queue;
import com.jrbarbati.search.fringe.Stack;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class SearchTest
{
    Search search = new Search(null) {
        @Override
        protected int calculateHeuristic(Node currentNode, Node endNode) {
            return 0;
        }
    };

    @Before
    public void setup()
    {
        Search.MAX_BOUND = 3;
        Search.MIN_BOUND = 0;

        search.setStartNode(new Node(0, 0,  0,  0));
        search.setEndNode  (new Node(2, 2, 40, 40));
    }

    @Test
    public void executeIteration_DFS()
    {
        search.setFringe(new Stack());
        search.setup();

        while (!search.isDone())
        {
            search.executeIteration();
            System.out.printf("Explored: %s\n", Arrays.toString(search.getExplored().toArray(new Node[] {})));
            System.out.printf("Fringe:   %s\n\n", Arrays.toString(search.getFringe().asList().toArray(new Node[] {})));
        }

        System.out.println(Arrays.toString(search.getPath().asList().toArray(new Node[] {})));

        assertEquals(search.getStartNode(), search.getPath().asList().get(0));
        assertEquals(search.getEndNode()  , search.getPath().asList().get(search.getPath().size() - 1));
    }

    @Test
    public void executeIteration_BFS()
    {
        search.setFringe(new Queue());
        search.setup();

        while (!search.isDone())
        {
            search.executeIteration();
            System.out.printf("Explored: %s\n", Arrays.toString(search.getExplored().toArray(new Node[] {})));
            System.out.printf("Fringe:   %s\n\n", Arrays.toString(search.getFringe().asList().toArray(new Node[] {})));
        }

        System.out.println(Arrays.toString(search.getPath().asList().toArray(new Node[] {})));

        assertEquals(search.getStartNode(), search.getPath().asList().get(0));
        assertEquals(search.getEndNode()  , search.getPath().asList().get(search.getPath().size() - 1));
    }

    @Test
    public void executeIteration_UCS()
    {
        search.setFringe(new PriorityQueue(Comparator.comparingInt(Node::getG)));
        search.setup();

        while (!search.isDone())
        {
            search.executeIteration();
            System.out.printf("Explored: %s\n", Arrays.toString(search.getExplored().toArray(new Node[] {})));
            System.out.printf("Fringe:   %s\n\n", Arrays.toString(search.getFringe().asList().toArray(new Node[] {})));
        }

        System.out.println(Arrays.toString(search.getPath().asList().toArray(new Node[] {})));

        assertEquals(search.getStartNode(), search.getPath().asList().get(0));
        assertEquals(search.getEndNode()  , search.getPath().asList().get(search.getPath().size() - 1));
    }

    @Test
    public void executeIteration_AStar()
    {
        search.setFringe(new PriorityQueue(Comparator.comparingInt(node -> node.getG() + node.getH())));
        search.setup();

        while (!search.isDone())
        {
            search.executeIteration();
            System.out.printf("Explored: %s\n", Arrays.toString(search.getExplored().toArray(new Node[] {})));
            System.out.printf("Fringe:   %s\n\n", Arrays.toString(search.getFringe().asList().toArray(new Node[] {})));
        }

        System.out.println(Arrays.toString(search.getPath().asList().toArray(new Node[] {})));

        assertEquals(search.getStartNode(), search.getPath().asList().get(0));
        assertEquals(search.getEndNode()  , search.getPath().asList().get(search.getPath().size() - 1));
    }
}