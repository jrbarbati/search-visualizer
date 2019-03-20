package com.jrbarbati.gui;

import com.jrbarbati.path.Node;
import com.jrbarbati.search.DepthFirstSearch;
import com.jrbarbati.search.factory.SearchFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class Gui
{
    private JFrame mainFrame = new JFrame("Search Visualizer");
    private SquaresPanel squaresPanel = new SquaresPanel();
    private List<JRadioButton> radioButtons = new ArrayList<>();
    private SearchFactory searchFactory = new SearchFactory();

    public void create()
    {
        squaresPanel.setBorder(new LineBorder(Color.BLACK));
        squaresPanel.setLayout(new BorderLayout());
        squaresPanel.setSearchAlgorithm(new DepthFirstSearch());

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new LineBorder(Color.BLACK));

        JButton run = new JButton("Run");
        run.addActionListener(squaresPanel);

        JButton clear = new JButton("Clear");
        clear.addActionListener(squaresPanel);

        JRadioButton dfs = new JRadioButton("DFS");
        JRadioButton bfs = new JRadioButton("BFS");
        JRadioButton ucs = new JRadioButton("UCS");
        JRadioButton aStar = new JRadioButton("A*");

        dfs.setName("DFS");
        dfs.setSelected(true);
        bfs.setName("BFS");
        ucs.setName("UCS");
        aStar.setName("ASTAR");

        radioButtons.addAll(Arrays.asList(dfs, bfs, ucs, aStar));

        addActionListeners(dfs, bfs, ucs, aStar);
        addTo(inputPanel, run, dfs, bfs, ucs, aStar, clear);

        squaresPanel.add(inputPanel, BorderLayout.SOUTH);

        mainFrame.setContentPane(squaresPanel);
        mainFrame.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    private void addTo(JPanel panel, java.awt.Component... components)
    {
        for (java.awt.Component component : components)
            panel.add(component);
    }

    private void addActionListeners(JRadioButton... components)
    {
        for(JRadioButton component : components)
            component.addActionListener(createActionListenerFor(component));
    }

    /**
     * Default is Depth First Search
     * @param button radio button that was pressed
     * @return {@code ActionListener} that deselects all other radio buttons and sets the search algorithm to be the
     * selected one if the current search is done.
     */
    private ActionListener createActionListenerFor(JRadioButton button)
    {
        return actionListenter -> radioButtons.forEach(radioButton -> {
            if (radioButton == button)
                return;

            if (button.isSelected())
                radioButton.setSelected(false);

            if (squaresPanel.getSearchAlgorithm().isDone())
            {
                Node startNode = squaresPanel.getSearchAlgorithm().getStartNode();
                Node endNode = squaresPanel.getSearchAlgorithm().getEndNode();
                Set<Node> wallNodes = squaresPanel.getSearchAlgorithm().getWallNodes();

                squaresPanel.setSearchAlgorithm(searchFactory.getSearch(
                        button.isSelected() ? button.getName() : "DFS"
                ));

                squaresPanel.getSearchAlgorithm().setStartNode(startNode);
                squaresPanel.getSearchAlgorithm().setEndNode(endNode);
                squaresPanel.getSearchAlgorithm().setWallNodes(wallNodes);
            }
        });
    }

    public void setVisible(boolean visible)
    {
        mainFrame.setVisible(visible);
    }

    public void setDefaultCloseOperation(int operation)
    {
        mainFrame.setDefaultCloseOperation(operation);
    }
}
