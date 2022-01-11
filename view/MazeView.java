package view;

import java.util.Objects;
import java.util.Random;
import model.Cell;
import model.Maze;
import util.MazeSolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MazeView extends JFrame {
    protected static boolean shouldFill = true;
    protected static boolean RIGHT_TO_LEFT = false;

    protected JPanel panel;

    protected JLabel image;
    protected GridBagConstraints c;

    private static final String imagePath = "Z:\\Documents\\Maze\\MazeProject\\images\\";
    protected Maze maze;
    protected ImageIcon imageIcon;

    protected int imageSize;

    public MazeView(int size, JPanel panel){
        this.imageIcon = new ImageIcon(imagePath + "redcell.png");
        if (size == 25) {
            this.imageSize = 30;
        } else if (size == 50) {
            this.imageSize = 16;
        } else {
            this.imageSize = 11;
        }
        Image oldImage = imageIcon.getImage();
        Image newImage = oldImage.getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
        this.imageIcon = new ImageIcon(newImage);
        this.panel = panel;
        this.maze = new Maze(size);
        build(maze);
        MazeSolver solveMaze = new MazeSolver(maze);
    }

        public void build(Maze maze) {
            JFrame frame = new JFrame("Maze");


            //Set up the content pane.
            createMaze(frame.getContentPane());
            //Display the window.
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
            frame.setResizable(false);
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
                drawPane(maze.getCellList().get(x), pane);
            }
    }


    public void drawPane(List<Cell> cellList, Container pane) {
        for (int y = 0; y < maze.getSize(); y++) {
            Cell cell = cellList.get(y);
            image = new JLabel(imageIcon);

            //cell.getWalls().get("S"), cell.getWalls().get("W"),
            //                    cell.getWalls().get("N"), cell.getWalls().get("E"), Color.BLACK
            int top = 0;
            int bottom = 0;
            int right = 0;
            int left = 0;
            int redTop = 3;
            int redBottom = 3;
            int redRight = 3;
            int redLeft = 3;
            if (maze.getSize() != 25) {
                redTop = 1;
                redBottom = 1;
                redRight = 1;
                redLeft = 1;
            }
            if (Objects.equals(cell.getWalls().get("N"), "T")) {
                top = 3;
                if (maze.getSize() != 25) {
                top = 1;
                }
                redTop = 0;
            }
            if (Objects.equals(cell.getWalls().get("S"), "T")) {
                bottom = 3;
                if (maze.getSize() != 25) {
                    bottom = 1;
                }
                redBottom = 0;
            }
            if (Objects.equals(cell.getWalls().get("E"), "T")) {
                right = 3;
                if (maze.getSize() != 25) {
                    right = 1;
                }
                redRight = 0;
            }
            if (Objects.equals(cell.getWalls().get("W"), "T")) {
                left = 3;
                if (maze.getSize() != 25) {
                    left = 1;
                }
                redLeft = 0;
            }
            Border blackBorder = BorderFactory.createMatteBorder(top, left, bottom, right, Color.black);
            Border redBorder = BorderFactory.createMatteBorder(redTop, redLeft, redBottom, redRight, Color.red);

            CompoundBorder compoundBorder = new CompoundBorder(blackBorder, redBorder);
            image.setBorder(compoundBorder);
            c.weightx = 0.5;
            c.gridx = cell.getX();
            c.gridy = cell.getY();
            pane.add(image, c);
        }
    }
}
