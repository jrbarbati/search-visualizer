package com.jrbarbati.gui;

import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.search.Search;
import com.jrbarbati.search.fringe.Fringe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;

public class SquaresPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    private char pressedKey = 0;
    public static final int NODE_SIZE = 20;
    private int speed;
    private Search searchAlgorithm;

    public SquaresPanel()
    {
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        speed = 50;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawSquares(g, NODE_SIZE, Color.WHITE);
        fillInNodes(g, getSearchAlgorithm().getWallNodes(), Color.BLACK);
        fillInNodes(g, getSearchAlgorithm().getFringe().asList(), Color.BLUE);
        fillInNodes(g, getSearchAlgorithm().getExplored(), Color.YELLOW);
        fillInNode(g, getSearchAlgorithm().getStartNode(), Color.GREEN);
        fillInNode(g, getSearchAlgorithm().getEndNode(), Color.RED);

        if (getSearchAlgorithm().pathFound())
            fillInNodes(g, getSearchAlgorithm().getPath().asList(), Color.CYAN);
    }

    private void drawSquares(Graphics g, int size, Color color)
    {
        g.setColor(color);

        for (int y = 0; y < getHeight(); y += size)
            for (int x = 0; x < getWidth(); x += size)
                g.drawRect(x, y, size, size);
    }

    private void fillInNodes(Graphics g, Collection<Node> nodes, Color color)
    {
        for (Node node : nodes)
            fillInNode(g, node, color);
    }

    private void fillInNode(Graphics g, Node node, Color color)
    {
        if (node == null)
            return;

        g.setColor(color);
        g.fillRect(node.getCoordinate().rawX(), node.getCoordinate().rawY(), NODE_SIZE, NODE_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getActionCommand() == null)
                return;

            if ("run".equals(e.getActionCommand()))
                getSearchAlgorithm().execute();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        pressedKey = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        pressedKey = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    private void draw(MouseEvent e)
    {
        Coordinate coordinate = calculateNodeCoordinate(e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e))
            if (shouldModifyStartNode())
                getSearchAlgorithm().setStartNode(new Node(coordinate));
            else if (shouldModifyEndNode())
                getSearchAlgorithm().setEndNode(new Node(coordinate));
            else
                getSearchAlgorithm().addWallNode(new Node(coordinate));

        if (SwingUtilities.isRightMouseButton(e))
            if (shouldModifyStartNode())
                getSearchAlgorithm().setStartNode(null);
            else if (shouldModifyEndNode())
                getSearchAlgorithm().setEndNode(null);
            else
                getSearchAlgorithm().removeWallNode(new Node(coordinate));

        repaint();
    }

    private boolean shouldModifyEndNode() {
        return pressedKey == 'e';
    }

    private boolean shouldModifyStartNode() {
        return pressedKey == 's';
    }

    protected Coordinate calculateNodeCoordinate(int x, int y)
    {
        return new Coordinate(x / NODE_SIZE, y / NODE_SIZE, calibrate(x, NODE_SIZE), calibrate(y, NODE_SIZE));
    }

    private int calibrate(int value, int base) {
        return value - (value % base);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        draw(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e)
    {
        draw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int value)
    {
        this.speed = value;
    }

    public Search getSearchAlgorithm()
    {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(Search searchAlgorithm)
    {
        this.searchAlgorithm = searchAlgorithm;
    }
}
