package com.jrbarbati.gui;

import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.search.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;

public class SquaresPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    private char pressedKey = 0;
    private static final int NODE_SIZE = 20;
    private int speed;
    private Search searchAlgorithm;

    public SquaresPanel()
    {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.setFocusTraversalKeysEnabled(false);
        this.setFocusable(true);
        this.speed = 50;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawSquares(g, NODE_SIZE, Color.WHITE);
        fillInNodes(g, this.getSearchAlgorithm().getWallNodes(), Color.BLACK);
        fillInNodes(g, this.getSearchAlgorithm().getFringe(), Color.BLUE);
        fillInNodes(g, this.getSearchAlgorithm().getExplored(), Color.YELLOW);
        fillInNode(g, this.getSearchAlgorithm().getStartNode(), Color.GREEN);
        fillInNode(g, this.getSearchAlgorithm().getEndNode(), Color.RED);

        if (this.getSearchAlgorithm().pathFound())
            fillInNodes(g, this.getSearchAlgorithm().getPath().asList(), Color.CYAN);
    }

    private void drawSquares(Graphics g, int size, Color color)
    {
        g.setColor(color);

        for (int y = 0; y < this.getHeight(); y += size)
            for (int x = 0; x < this.getWidth(); x += size)
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
        if (e.getActionCommand() == null)
            return;

        if ("run".equals(e.getActionCommand()))
            this.getSearchAlgorithm().execute();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        this.pressedKey = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        this.pressedKey = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    private void draw(MouseEvent e)
    {
        Coordinate coordinate = calculateNodeCoordinate(e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e))
            if (shouldModifyStartNode())
                this.getSearchAlgorithm().setStartNode(new Node(coordinate));
            else if (shouldModifyEndNode())
                this.getSearchAlgorithm().setEndNode(new Node(coordinate));
            else
                this.getSearchAlgorithm().addWallNode(new Node(coordinate));

        if (SwingUtilities.isRightMouseButton(e))
            if (shouldModifyStartNode())
                this.getSearchAlgorithm().setStartNode(null);
            else if (shouldModifyEndNode())
                this.getSearchAlgorithm().setEndNode(null);
            else
                this.getSearchAlgorithm().removeWallNode(new Node(coordinate));

        this.repaint();
    }

    private boolean shouldModifyEndNode() {
        return this.pressedKey == 'e';
    }

    private boolean shouldModifyStartNode() {
        return this.pressedKey == 's';
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
        return this.searchAlgorithm;
    }

    public void setSearchAlgorithm(Search searchAlgorithm)
    {
        this.searchAlgorithm = searchAlgorithm;
    }
}
