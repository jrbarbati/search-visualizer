package com.jrbarbati.gui;

import com.jrbarbati.gui.exception.MissingCriticalNodeException;
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
    public static final int NODE_SIZE = 20;
    private Search searchAlgorithm;
    private Timer timer;

    public SquaresPanel()
    {
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        timer = new Timer(75, this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (!getSearchAlgorithm().isDone())
            {
                getSearchAlgorithm().executeIteration();
            }
            else if (getSearchAlgorithm().pathFound())
            {
                timer.stop();
            }
            else if ("Run".equals(e.getActionCommand()))
            {
                if (!getSearchAlgorithm().isReady())
                    throw new MissingCriticalNodeException(
                            String.format("Missing node needed to run: startNode: %s \t endNode: %s",
                                    getSearchAlgorithm().getStartNode(),
                                    getSearchAlgorithm().getEndNode()
                            )
                    );
                getSearchAlgorithm().setup();
                timer.start();
                getSearchAlgorithm().executeIteration();
            }

            repaint();
        }
        catch (MissingCriticalNodeException mcne)
        {
            throw mcne;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        draw(e);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        draw(e);
    }

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

    private void draw(MouseEvent e)
    {
        Coordinate coordinate = calculateNodeCoordinate(e.getX(), e.getY());

        boolean leftMousePressed = SwingUtilities.isLeftMouseButton(e);

        System.out.printf("Left Mouse? %s\n", leftMousePressed);
        System.out.printf("Start Node? %s\n", shouldModifyStartNode());
        System.out.printf("End Node? %s\n", shouldModifyEndNode());

        if (shouldModifyStartNode())
            getSearchAlgorithm().setStartNode(leftMousePressed ? new Node(coordinate) : null);
        else if (shouldModifyEndNode())
            getSearchAlgorithm().setEndNode(leftMousePressed ? new Node(coordinate) : null);
        else
            if (leftMousePressed) getSearchAlgorithm().addWallNode(new Node(coordinate));
            else                  getSearchAlgorithm().removeWallNode(new Node(coordinate));

        repaint();
    }

    private boolean shouldModifyEndNode()
    {
        return pressedKey == 'e';
    }

    private boolean shouldModifyStartNode()
    {
        return pressedKey == 's';
    }

    protected Coordinate calculateNodeCoordinate(int x, int y)
    {
        return new Coordinate(x / NODE_SIZE, y / NODE_SIZE, calibrate(x, NODE_SIZE), calibrate(y, NODE_SIZE));
    }

    private int calibrate(int value, int base) {
        return value - (value % base);
    }

    public Search getSearchAlgorithm()
    {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(Search searchAlgorithm)
    {
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
