package view;

import model.Cell;
import model.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MazeViewPanel extends JFrame {
    protected static boolean shouldFill = true;
    protected static boolean shouldWeightX = true;
    protected static boolean RIGHT_TO_LEFT = false;

    protected JPanel panel;

    protected JLabel image;
    protected Container pane;
    protected GridBagConstraints c;

    protected Maze maze;

    public MazeViewPanel(int size, JPanel panel){
        this.panel = panel;
        this.maze = new Maze(size);
        build(maze);
    }

        public void build(Maze maze) {
            JFrame frame = new JFrame("GridBagLayoutDemo");


            //Set up the content pane.
            createMaze(frame.getContentPane());
            //Display the window.
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(panel);

    }

    /**
     * When the maze changes, repaint the view.
     */
    public synchronized void gameChanged() {
        repaint();
    }


    public void createMaze(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JLabel image;
        ImageIcon imageIcon = new ImageIcon("z:\\Documents\\Maze\\MazeProject\\images\\explosion1.png");

        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        pane.setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }


        for (int x = 0; x < maze.getSize(); x++) {
                drawPane(imageIcon, maze.getCellList().get(x), pane);

            }
    }

    public void drawPane(ImageIcon imageIcon, List<Cell> cellList, Container pane) {
        for (int y = 0; y < maze.getSize(); y++) {
            Cell cell = cellList.get(y);
            image = new JLabel(imageIcon);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                c.gridx = cell.getX();
                c.gridy = cell.getY();
                pane.add(image, c);

        }
    }
}
