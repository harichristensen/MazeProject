package view;

import java.util.Objects;
import java.util.Random;
import model.Cell;
import model.Maze;
import util.MazeSolver;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import java.util.List;

public class MazeView extends JFrame{
    protected static boolean shouldFill = true;
    protected static boolean RIGHT_TO_LEFT = false;


    protected JPanel panel;
    protected JFrame frame;
    protected boolean done;

    protected JLabel image;
    protected GridBagConstraints c;

    protected Maze maze;

    public MazeView(Maze maze, boolean done) {
        this.done = done;
        this.panel = new JPanel();
        this.maze = maze;
        build();
        if(!done) {
            solveFrame();
        }
    }


    /**
     * Builds the frame to display maze
     */
    public void build() {
            this.frame = new JFrame("Maze");

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
     * Sets layout and constraints of the frame content and calls draw pane
     */
    public void createMaze(Container pane) {
        pane.removeAll();
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


    /**
     * Draws cells  and cell borders on pane, sets border size, gets cell image, ands sets cell location
     */
    public void drawPane(List<Cell> cellList, Container pane) {
        for (int y = 0; y < maze.getSize(); y++) {
            Cell cell = cellList.get(y);
            image = new JLabel(cell.getCellImage());

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
            Border redBorder = BorderFactory.createMatteBorder(redTop, redLeft, redBottom, redRight, cell.getColour());

            CompoundBorder compoundBorder = new CompoundBorder(blackBorder, redBorder);
            image.setBorder(compoundBorder);
            c.weightx = 0.5;
            c.gridx = cell.getX();
            c.gridy = cell.getY();
            pane.add(image, c);
        }
    }

    /**
     * Creates a frame to open a new maze view frame with the updated maze that is solved.
     */
    public void solveFrame() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setLocation(this.frame.getX()-250, this.frame.getY());
        frame.setSize(250, 100);
        frame.setVisible(true);

        Dimension dimension = new Dimension();
        dimension.setSize(250, 100);

        JButton button = new JButton("Click to Solve");
        button.setSize(dimension);
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setMaximumSize(dimension);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            new MazeSolver(this, maze);
            MazeView mazeView = new MazeView(maze, true);
            mazeView.setLocation(700, 200);
            frame.setVisible(false);
            frame.dispose();
        });
        panel.add(button);
    }

}
