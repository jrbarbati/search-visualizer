package com.jrbarbati.gui;

import com.jrbarbati.gui.exception.MissingCriticalNodeException;
import com.jrbarbati.path.Coordinate;
import com.jrbarbati.path.Node;
import com.jrbarbati.search.Search;
import com.jrbarbati.search.factory.SearchFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SquaresPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    private char pressedKey = '\0';
    public static final int NODE_SIZE = 20;
    private Search searchAlgorithm;
    private SearchFactory searchFactory = new SearchFactory();
    private Timer timer;

    public SquaresPanel()
    {
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        requestFocusInWindow();
        timer = new Timer(75, this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JRadioButton)
            radioButtonListener(e);
        else
            searchListener(e);

        requestFocusInWindow();
    }

    private void searchListener(ActionEvent e)
    {
        try
        {
            if ("Clear".equals(e.getActionCommand()))
                getSearchAlgorithm().reset();
            else if ("Run".equals(e.getActionCommand()))
            {
                if (!getSearchAlgorithm().isReady())
                    throw new MissingCriticalNodeException(
                            String.format("Missing node needed to run\n\tStart Node: %s\n\t End Node: %s",
                                    getSearchAlgorithm().getStartNode(),
                                    getSearchAlgorithm().getEndNode()
                            )
                    );
                getSearchAlgorithm().setup();
                timer.start();
                getSearchAlgorithm().executeIteration();
            }
            else if ("Start".equals(e.getActionCommand()))
                timer.start();
            else if ("Stop".equals(e.getActionCommand()))
                timer.stop();
            else if (!getSearchAlgorithm().isDone())
                getSearchAlgorithm().executeIteration();
            else if (getSearchAlgorithm().pathFound())
                timer.stop();
            else if (getSearchAlgorithm().noPathPossible())
            {
                timer.stop();
                JOptionPane.showMessageDialog(this, "No path is possible!", "Done!", JOptionPane.INFORMATION_MESSAGE);
            }

            repaint();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, String.format("Unable to continue.\n%s", ex.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void  radioButtonListener(ActionEvent e)
    {
        JRadioButton clickedRadioButton = (JRadioButton) e.getSource();

        for(JRadioButton radioButton : getRadioButtons())
        {
            if (radioButton == clickedRadioButton)
                continue;

            if (clickedRadioButton.isSelected())
                radioButton.setSelected(false);
        }

        if (getSearchAlgorithm().isDone())
        {
            Node startNode = getSearchAlgorithm().getStartNode();
            Node endNode = getSearchAlgorithm().getEndNode();
            Set<Node> wallNodes = getSearchAlgorithm().getWallNodes();

            setSearchAlgorithm(searchFactory.getSearchByName(
                    clickedRadioButton.isSelected() ? clickedRadioButton.getName() : "DFS"
            ));

            System.out.println(getSearchAlgorithm().getClass().getSimpleName());

            getSearchAlgorithm().setStartNode(startNode);
            getSearchAlgorithm().setEndNode(endNode);
            getSearchAlgorithm().setWallNodes(wallNodes);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        draw(e);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        draw(e);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        System.out.printf("Typed: %c\n", e.getKeyChar());
        pressedKey = e.getKeyChar();
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
        g.fillRect(
                node.getCoordinate().rawX() + 1,
                node.getCoordinate().rawY() + 1,
                NODE_SIZE - 1,
                NODE_SIZE - 1
        );
    }

    private void draw(MouseEvent e)
    {
        Coordinate coordinate = calculateNodeCoordinate(e.getX(), e.getY());

        boolean shouldAddToGrid = SwingUtilities.isLeftMouseButton(e);

        if (shouldModifyStartNode())
            getSearchAlgorithm().setStartNode(shouldAddToGrid ? new Node(coordinate) : null);
        else if (shouldModifyEndNode())
            getSearchAlgorithm().setEndNode(shouldAddToGrid ? new Node(coordinate) : null);
        else if (shouldAddToGrid)
            getSearchAlgorithm().addWallNode(new Node(coordinate));
        else
            getSearchAlgorithm().removeWallNode(new Node(coordinate));

        repaint();
    }

    protected Coordinate calculateNodeCoordinate(int x, int y)
    {
        return new Coordinate(x / NODE_SIZE, y / NODE_SIZE, calibrate(x, NODE_SIZE), calibrate(y, NODE_SIZE));
    }

    private boolean shouldModifyStartNode()
    {
        return pressedKeyIs('s');
    }

    private boolean shouldModifyEndNode()
    {
        return pressedKeyIs('e');
    }

    private boolean pressedKeyIs(char key)
    {
        return pressedKey == key;
    }

    private int calibrate(int value, int base)
    {
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

    private List<JRadioButton> getRadioButtons()
    {
        List<JRadioButton> radioButtons = new ArrayList<>();

        for (Component component : ((JPanel) this.getComponent(0)).getComponents())
            if (component instanceof JRadioButton)
                radioButtons.add((JRadioButton) component);

        return radioButtons;
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
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
