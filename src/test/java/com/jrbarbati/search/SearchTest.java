package com.jrbarbati.search;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.fringe.PriorityQueue;
import com.jrbarbati.search.fringe.Queue;
import com.jrbarbati.search.fringe.Stack;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTest
{
    Search search = new Search(null) {
        @Override
        protected int calculateHeuristic(Node currentNode, Node endNode) {
            return 0;
        }
    };

    @Test
    public void inBounds()
    {
        assertFalse(search.inBounds(new Node(-1, 0, 0, 0)));
        assertFalse(search.inBounds(new Node(0, -1, 0, 0)));
        assertFalse(search.inBounds(new Node(-1, -1, 0, 0)));
        assertTrue(search.inBounds(new Node(6, 0, 0, 0)));
    }

    @Test
    public void buildPath()
    {
        Node endNode = new Node(3, 3, 0, 0);
        Node node2 = new Node(3, 2, 0, 0);
        Node node3 = new Node(2, 2, 0, 0);
        Node node4 = new Node(2, 1, 0, 0);
        Node node5 = new Node(1, 1, 0, 0);
        Node node6 = new Node(1, 0, 0, 0);
        Node startNode = new Node(0, 0, 0, 0);

        endNode.setParent(node2);
        node2.setParent(node3);
        node3.setParent(node4);
        node4.setParent(node5);
        node5.setParent(node6);
        node6.setParent(startNode);

        List<Node> nodes = search.buildPath(startNode, endNode).asList();

        assertEquals(7, nodes.size());
        assertEquals(new Node(0, 0, 0, 0), nodes.get(0));
        assertEquals(new Node(3, 3, 0, 0), nodes.get(6));
    }

    @Test
    public void buildPath_realWorld()
    {
        Node endNode = new Node(2, 0, 0, 0);
        Node node3 = new Node(3, 0, 0, 0);
        Node node2 = new Node(3, 1, 0, 0);
        Node startNode = new Node(3, 2, 0, 0);

        endNode.setParent(node3);
        node3.setParent(node2);
        node2.setParent(startNode);

        assertEquals(4, search.buildPath(startNode, endNode).size());
    }

    @Test
    public void executeIteration() throws Exception
    {
        Search.MAX_BOUND = 3;
        Search.MIN_BOUND = 0;

        search.setFringe(new Stack());
        search.setStartNode(new Node(0, 0, 0, 0));
        search.setEndNode(new Node(2, 2, 40, 40));
        search.setup();

        while (!search.isDone())
        {
            search.executeIteration();
            System.out.printf("Explored: %s\n", Arrays.toString(search.getExplored().toArray(new Node[] {})));
            System.out.printf("Fringe:   %s\n\n", Arrays.toString(search.getFringe().asList().toArray(new Node[] {})));
        }

        System.out.println(Arrays.toString(search.getPath().asList().toArray(new Node[] {})));

        assertEquals(search.getStartNode(), search.getPath().asList().get(0));
        assertEquals(search.getEndNode(), search.getPath().asList().get(search.getPath().size() - 1));
    }
}