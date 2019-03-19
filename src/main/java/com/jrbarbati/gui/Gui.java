package com.jrbarbati.gui;

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
        run.addActionListener(actionListener -> radioButtons.forEach(radioButton -> {
            if (radioButton.isSelected())
                squaresPanel.setSearchAlgorithm(searchFactory.getSearch(radioButton.getActionCommand()));
        }));

        JSlider speed = new JSlider(0, 100);
        speed.setValue(squaresPanel.getSpeed());

        speed.addChangeListener(changeEvent -> {
            speed.setValue(((JSlider) changeEvent.getSource()).getValue());
            squaresPanel.setSpeed(speed.getValue());
            squaresPanel.repaint();
        });

        JRadioButton dfs = new JRadioButton("DFS");
        JRadioButton bfs = new JRadioButton("BFS");
        JRadioButton ucs = new JRadioButton("UCS");
        JRadioButton aStar = new JRadioButton("A*");

        radioButtons.addAll(Arrays.asList(dfs, bfs, ucs, aStar));

        dfs.addActionListener(createActionListenerFor(dfs));
        dfs.setSelected(true);
        bfs.addActionListener(createActionListenerFor(bfs));
        ucs.addActionListener(createActionListenerFor(ucs));
        aStar.addActionListener(createActionListenerFor(aStar));

        inputPanel.add(run);
        inputPanel.add(speed);
        inputPanel.add(dfs);
        inputPanel.add(bfs);
        inputPanel.add(ucs);
        inputPanel.add(aStar);

        squaresPanel.add(inputPanel, BorderLayout.SOUTH);

        mainFrame.setContentPane(squaresPanel);
        mainFrame.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    /**
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
                squaresPanel.setSearchAlgorithm(searchFactory.getSearch(actionListenter.getActionCommand()));
        });
    }

    public void setVisible(boolean visible)
    {
        this.mainFrame.setVisible(visible);
    }

    public void setDefaultCloseOperation(int operation)
    {
        this.mainFrame.setDefaultCloseOperation(operation);
    }
}
