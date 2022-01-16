package view;

import java.util.Objects;
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
            mazeOptionsFrame();
        } else {
            solveOptionsFrame();
            descriptionFrame("The purple path is the path found by the recursive maze solver. The red" +
                    "cells are cells that were visited and found to be a dead end. If the maze is impossible there " +
                    "will be no purple path and 'Impossible maze' will be printed.");
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
     * Creates a frame to hold options on what to do with the maze.
     */
    public void mazeOptionsFrame() {
        JFrame mazeFrame = new JFrame();
        JPanel mazePanel = new JPanel();
        setLayout(new BoxLayout(mazeFrame, BoxLayout.PAGE_AXIS));
        mazeFrame.add(mazePanel);
        mazeFrame.setLocation(this.frame.getX()-250, this.frame.getY());
        mazeFrame.setSize(250, 255);
        mazeFrame.setVisible(true);

        Dimension dimension = new Dimension();
        dimension.setSize(250, 100);

        JButton button = new JButton("New Maze");
        button.setSize(dimension);
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.addActionListener(e -> {
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            MazeView mazeView = new MazeView(new Maze(maze.getSize()), false);
            mazeView.setLocation(700, 200);
            mazeFrame.setVisible(false);
            mazeFrame.dispose();
        });
        mazePanel.add(button);

        button = new JButton("Click to Solve");
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.addActionListener(e -> {
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            try {
                new MazeSolver(this, maze);
            }
            catch (Exception ignored){
            }
            MazeView mazeView = new MazeView(maze, true);
            mazeView.setLocation(700, 200);
            mazeFrame.setVisible(false);
            mazeFrame.dispose();
        });
        mazePanel.add(button);



        /* Code for a back to menu button
        button = new JButton("Back to Menu");
        button.setSize(dimension);
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.addActionListener(e -> {
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            mazeFrame.setVisible(false);
            mazeFrame.dispose();
        });
        mazePanel.add(button);
         */
    }

    /**
     * Creates a frame to open a new maze view frame with the updated maze that is solved.
     */
    public void solveOptionsFrame() {
        JFrame solveOptions = new JFrame();
        JPanel solvePanel = new JPanel();
        setLayout(new BoxLayout(solveOptions, BoxLayout.PAGE_AXIS));
        solveOptions.add(solvePanel);
        solveOptions.setLocation(this.frame.getX()-250, this.frame.getY());
        solveOptions.setSize(250, 145);
        solveOptions.setVisible(true);

        Dimension dimension = new Dimension();
        dimension.setSize(250, 100);

        JButton button = new JButton("New Maze");
        button.setSize(dimension);
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.addActionListener(e -> {
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            MazeView mazeView = new MazeView(new Maze(maze.getSize()), false);
            mazeView.setLocation(700, 200);
            solveOptions.setVisible(false);
            solveOptions.dispose();
        });
        solvePanel.add(button);


        /* Code for a back to menu button
          button = new JButton("Back to Menu");
                  button.setSize(dimension);
                  button.setFont(new Font("Serif", Font.PLAIN, 35));
                  button.setMinimumSize(dimension);
                  button.setMaximumSize(dimension);
                  button.setBackground(Color.BLACK);
                  button.setForeground(Color.WHITE);
                  button.setAlignmentX(CENTER_ALIGNMENT);
                  button.setPreferredSize(dimension);
                  button.addActionListener(e -> {
                      this.frame.setVisible(false); //you can't see me!
                      this.frame.dispose();
                      solveOptions.setVisible(false);
                      solveOptions.dispose();
                  });
                  solvePanel.add(button);
         */
    }


    /**
     * Creates a frame to open a new maze view frame with the updated maze that is solved.
     */
    public void descriptionFrame(String text) {
        JFrame descriptionFrame = new JFrame();
        JPanel descriptionPanel = new JPanel();
        setLayout(new BoxLayout(descriptionFrame, BoxLayout.PAGE_AXIS));
        descriptionFrame.add(descriptionPanel);
        descriptionFrame.setLocation(this.frame.getX() - 400, this.frame.getY() + 500);
        descriptionFrame.setSize(400, 225);
        descriptionFrame.setVisible(true);

        Dimension dimension = new Dimension();
        dimension.setSize(350, 250);

        JTextArea description = new JTextArea(text);
        description.setFont(new Font("Serif", Font.PLAIN, 20));
        description.setForeground(Color.black);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setCursor(null);
        description.setOpaque(false);
        description.setFocusable(false);
        description.setSize(dimension);
        descriptionPanel.add(description);
    }

}
