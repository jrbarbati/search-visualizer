package com.jrbarbati.gui;

import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.search.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

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
        this.speed = 50;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        drawSquares(g, NODE_SIZE);
        fillInWalls(g, this.getSearchAlgorithm().getWallNodes());
    }

    private void drawSquares(Graphics g, int size)
    {
        g.setColor(Color.WHITE);

        for (int y = 0; y < this.getHeight(); y += size)
            for (int x = 0; x < this.getWidth(); x += size)
                g.drawRect(x, y, size, size);
    }

    private void fillInWalls(Graphics g, Set<Node> wallNodes) {
        g.setColor(Color.BLACK);

        for (Node node : wallNodes)
            g.fillRect(node.getCoordinate().rawX(), node.getCoordinate().rawY(), NODE_SIZE, NODE_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

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
    public void mouseClicked(MouseEvent e)
    {
        draw(e);
    }

    private void draw(MouseEvent e) {
        Coordinate coordinate = calculateNodeCoordinate(e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e))
        {
            this.getSearchAlgorithm().addWallNode(new Node(coordinate));
        }

        this.repaint();
    }

    protected Coordinate calculateNodeCoordinate(int x, int y)
    {
        return new Coordinate(x / NODE_SIZE, y / NODE_SIZE, calibrate(x, NODE_SIZE), calibrate(y, NODE_SIZE));
    }

    private int calibrate(int value, int base) {
        return value - (value % base);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

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
