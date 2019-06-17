package com.jrbarbati.search;

import com.jrbarbati.gui.SquaresPanel;
import com.jrbarbati.path.Node;
import com.jrbarbati.path.Path;
import com.jrbarbati.search.fringe.PriorityQueue;
import com.jrbarbati.search.fringe.Queue;
import com.jrbarbati.search.fringe.Stack;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class SearchTest
{
    private Search search = new Search(null) {
        @Override
        protected int calculateHeuristic(Node currentNode, Node endNode) {
            return 0;
        }
    };

    @Before
    public void setup()
    {
        SquaresPanel.MAX_BOUND = 3;
        SquaresPanel.MIN_BOUND = 0;

        search.setStartNode(new Node(0, 0,  0,  0));
        search.addWallNode(new Node(1, 1, 20, 20));
        search.setEndNode(new Node(2, 2, 40, 40));
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
        assertEquals(search.getEndNode(), search.getPath().asList().get(search.getPath().size() - 1));
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
        assertEquals(search.getEndNode(), search.getPath().asList().get(search.getPath().size() - 1));
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
        assertEquals(search.getEndNode(), search.getPath().asList().get(search.getPath().size() - 1));
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
        assertEquals(search.getEndNode(), search.getPath().asList().get(search.getPath().size() - 1));
    }


    @Test
    public void buildPath()
    {

        Node startNode = new Node(0, 0, 0, 0);
        Node endNode = new Node(1, 0, 0, 0);
        endNode.setParent(startNode);

        Path path = search.buildPath(startNode, endNode);

        assertEquals(2, path.size());
        assertEquals(new Node(0, 0, 0, 0), path.asList().get(0));
        assertEquals(new Node(1, 0, 0, 0), path.asList().get(1));
    }

    @Test
    public void isValid()
    {
        assertTrue(search.isValid(new Node(0, 0, 0, 0)));
        assertFalse(search.isValid(new Node(1, 1, 20, 20)));
    }

    @Test
    public void pathFound_unstartedSearch()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        assertFalse("Empty Fringe and path is null since search is unstarted", localSearch.pathFound());
    }

    @Test
    public void pathFound_startedSearch()
    {
        Stack fringe = new Stack();
        fringe.push(new Node(0, 0, 0, 0));

        Search localSearch = new Search(fringe) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        assertFalse("Unempty fringe and path is null since search has started", localSearch.pathFound());
    }

    @Test
    public void pathFound_finishedSearch_endNodeFound()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        Path path = new Path();
        path.add(new Node(3, 3, 0, 0));

        localSearch.setPath(path);

        assertTrue("Empty fringe and path is not null since end node was found", localSearch.pathFound());
    }

    @Test
    public void pathFound_finishedSearch_endNodeNotFound()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        localSearch.setPath(new Path());

        assertFalse("Empty fringe and path is empty since fringe was exhausted and no end goal was reached", localSearch.pathFound());
    }

    @Test
    public void noPathPossible_unstartedSearch()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        assertTrue("Fringe empty and path is null since search is unstarted", localSearch.noPathPossible());
    }

    @Test
    public void noPathPossible_startedSearch()
    {
        Stack fringe = new Stack();
        fringe.push(new Node(0, 0, 0, 0));

        Search localSearch = new Search(fringe) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        assertFalse("Fringe non empty and path is null since has started", localSearch.noPathPossible());
    }

    @Test
    public void noPathPossbile_finishedSearch_endNodeFound()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        Path path = new Path();
        path.add(new Node(3, 3, 0, 0));

        localSearch.setPath(path);

        assertFalse("Empty fringe and path is not null since end node was found", localSearch.noPathPossible());
    }

    @Test
    public void noPathPossbile_finishedSearch_endNodeNotFound()
    {
        Search localSearch = new Search(new Stack()) {
            @Override
            protected int calculateHeuristic(Node currentNode, Node endNode)
            {
                return 0;
            }
        };

        localSearch.setPath(new Path());

        assertTrue("Empty fringe and path is empty since search ended and no end node was found", localSearch.noPathPossible());
    }

    @Test
    public void isGoal()
    {
        assertTrue(search.isGoal(new Node(2, 2, 40, 40)));
    }

    @Test
    public void isGoal_not()
    {
        assertFalse(search.isGoal(new Node(2, 1, 0, 0)));
    }
}