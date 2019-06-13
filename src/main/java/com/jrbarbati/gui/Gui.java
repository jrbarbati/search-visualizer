package com.jrbarbati.gui;

import com.jrbarbati.search.DepthFirstSearch;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Gui
{
    private JFrame mainFrame = new JFrame("Search Visualizer");
    private SquaresPanel squaresPanel = new SquaresPanel();
    private List<JRadioButton> radioButtons = new ArrayList<>();

    public void create()
    {
        squaresPanel.setBorder(new LineBorder(Color.BLACK));
        squaresPanel.setLayout(new BorderLayout());
        squaresPanel.setSearchAlgorithm(new DepthFirstSearch());

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new LineBorder(Color.BLACK));

        JRadioButton dfs = createRadioButton("DFS", "DFS", true);
        JRadioButton bfs = createRadioButton("BFS", "BFS", false);
        JRadioButton ucs = createRadioButton("UCS", "UCS", false);
        JRadioButton aStar = createRadioButton("A*", "ASTAR", false);

        radioButtons.addAll(Arrays.asList(dfs, bfs, ucs, aStar));

        addActionListenerTo(dfs, bfs, ucs, aStar);
        addTo(inputPanel,
                createButton("Run"),
                createButton("Start"),
                createButton("Stop"),
                dfs,
                bfs,
                ucs,
                aStar,
                createButton("Clear")
        );

        squaresPanel.add(inputPanel, BorderLayout.SOUTH);

        mainFrame.setContentPane(squaresPanel);
        mainFrame.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    private JRadioButton createRadioButton(String label, String name, boolean isSelected)
    {
        JRadioButton radioButton = new JRadioButton(label);
        radioButton.setName(name);
        radioButton.setSelected(isSelected);
        return radioButton;
    }

    private JButton createButton(String text)
    {
        JButton button = new JButton(text);
        button.addActionListener(squaresPanel);
        return button;
    }

    private void addTo(JPanel panel, java.awt.Component... components)
    {
        for (java.awt.Component component : components)
            panel.add(component);
    }

    private void addActionListenerTo(JRadioButton... components)
    {
        for (JRadioButton component : components)
            component.addActionListener(squaresPanel);
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
