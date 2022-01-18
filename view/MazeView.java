package view;

import java.util.Objects;

import control.Controller;
import model.Cell;
import model.Maze;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import java.util.List;

public class MazeView extends JFrame{
    // pane attributes
    protected static boolean shouldFill = true;
    protected static boolean RIGHT_TO_LEFT = false;
    protected GridBagConstraints c;

    // this panel and frame and description frame
    protected JPanel panel;
    protected JFrame frame;
    protected JFrame descriptionFrame;

    // if the maze has been solved and updated
    protected boolean done;

    // image of current cell
    protected JLabel image;

    // maze to be shown
    protected Maze maze;

    // Controller object
    protected Controller controller;

    /**
     * Initialize MazeView.
     *
     * @param controller The controller of the algorithm
     * @param maze the maze to be displayed
     * @param done if the maze has been solved and updated
     */
    public MazeView(Controller controller, Maze maze, boolean done) {
        // Initialize attributes
        this.done = done;
        this.panel = new JPanel();
        this.maze = maze;
        this.controller = controller;

        // build frame
        build();

        // if maze has been solved
        if(!done) {
            mazeOptionsFrame();
        } else {
            solveOptionsFrame();
            descriptionFrame("The golden cells are the start and end cells (top left to bottom right). " +
                    "The purple path is the path found by the recursive maze solver. The red" +
                    "cells are cells that were visited and found to be a dead end. If the maze is impossible there " +
                    "will be no purple path and 'Impossible maze' will be printed.");
        }

    }


    /**
     * Builds the frame to display maze
     */
    public void build() {
            this.frame = new JFrame("Maze");

            // set up the content pane.
            createMaze(frame.getContentPane());
            // display the window.
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
        // resets pane
        pane.removeAll();
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        // set pane layout
        pane.setLayout(new GridBagLayout());
        this.c = new GridBagConstraints();
        // if pane should fill frame
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        // draw cells on pane
        // for each row
        for (int x = 0; x < maze.getSize(); x++) {
                drawPane(maze.getCellList().get(x), pane);
            }
    }


    /**
     * Draws cells and cell borders on pane, sets border size, gets cell image, and sets cell location onto pane
     */
    public void drawPane(List<Cell> cellList, Container pane) {
        // for each column
        for (int y = 0; y < maze.getSize(); y++) {

            // gets cell with current coordinates
            Cell cell = cellList.get(y);

            // gets the current cell's image
            image = new JLabel(cell.getCellImage());

            // set black border sizes
            int top = 0;
            int bottom = 0;
            int right = 0;
            int left = 0;
            // set colour border sizes
            int colourTop = 3;
            int colourBottom = 3;
            int colourRight = 3;
            int colourLeft = 3;
            // changes border sizes if image sizes are bigger
            if (maze.getSize() != 25) {
                colourTop = 1;
                colourBottom = 1;
                colourRight = 1;
                colourLeft = 1;
            }
            // if the cell has a wall then it adds black border and removes colour border
            if (Objects.equals(cell.getWalls().get("N"), "T")) {
                top = 3;
                if (maze.getSize() != 25) {
                top = 1;
                }
                colourTop = 0;
            }
            if (Objects.equals(cell.getWalls().get("S"), "T")) {
                bottom = 3;
                if (maze.getSize() != 25) {
                    bottom = 1;
                }
                colourBottom = 0;
            }
            if (Objects.equals(cell.getWalls().get("E"), "T")) {
                right = 3;
                if (maze.getSize() != 25) {
                    right = 1;
                }
                colourRight = 0;
            }
            if (Objects.equals(cell.getWalls().get("W"), "T")) {
                left = 3;
                if (maze.getSize() != 25) {
                    left = 1;
                }
                colourLeft = 0;
            }

            // creates black border
            Border blackBorder = BorderFactory.createMatteBorder(top, left, bottom, right, Color.black);
            // creates colour border
            Border colourBorder = BorderFactory.createMatteBorder(colourTop, colourLeft, colourBottom, colourRight, cell.getColour());

            // merges black and colour border
            CompoundBorder compoundBorder = new CompoundBorder(blackBorder, colourBorder);

            // sets border to the merged border
            image.setBorder(compoundBorder);
            c.weightx = 0.5;
            c.gridx = cell.getX();
            c.gridy = cell.getY();
            // adds cell to pane
            pane.add(image, c);
        }
    }

    /**
     * Creates a frame to hold options on what to do with the maze.
     */
    public void mazeOptionsFrame() {
        // create frame and pane
        JFrame mazeFrame = new JFrame("Options");
        JPanel mazePanel = new JPanel();
        // set frame layout
        setLayout(new BoxLayout(mazeFrame, BoxLayout.PAGE_AXIS));
        // add panel and make visible
        mazeFrame.add(mazePanel);
        mazeFrame.setLocation(this.frame.getX()-250, this.frame.getY());
        mazeFrame.setSize(250, 255);
        mazeFrame.setVisible(true);

        // size of button
        Dimension dimension = new Dimension();
        dimension.setSize(250, 100);

        // create button to make a new maze
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
            // closes and deletes previous maze frame
            this.frame.setVisible(false);
            this.frame.dispose();
            // creates new maze frame
            controller.newMazeView(controller, new Maze(maze.getSize()), false);
            // closes and deletes options frame
            mazeFrame.setVisible(false);
            mazeFrame.dispose();
        });
        // add button
        mazePanel.add(button);

        // create button to solve the maze
        button = new JButton("Click to Solve");
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.addActionListener(e -> {
            // deletes old maze view
            this.frame.setVisible(false);
            this.frame.dispose();

            // starts the maze solver
            controller.startSolver(maze);
            // creates new maze view with the update maze
            controller.newMazeView(controller, maze, true);
            // closes and deletes options frame
            mazeFrame.setVisible(false);
            mazeFrame.dispose();
        });
        // add button
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
     * Creates a frame to provide options for the solved maze.
     */
    public void solveOptionsFrame() {
        // create frame and panel
        JFrame solveOptions = new JFrame("Options");
        JPanel solvePanel = new JPanel();
        // set frame layout and add panel to frame
        setLayout(new BoxLayout(solveOptions, BoxLayout.PAGE_AXIS));
        solveOptions.add(solvePanel);
        solveOptions.setLocation(this.frame.getX()-250, this.frame.getY());
        solveOptions.setSize(250, 145);
        solveOptions.setVisible(true);

        // sets button dimensions
        Dimension dimension = new Dimension();
        dimension.setSize(250, 100);

        // creates button to make a new maze
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
            // closes and deletes old maze frame
            this.frame.setVisible(false); //you can't see me!
            this.frame.dispose();
            // creates new maze frame
            controller.newMazeView(controller, new Maze(maze.getSize()), false);
            // closes and deletes previous options frame
            solveOptions.setVisible(false);
            solveOptions.dispose();
            descriptionFrame.setVisible(false);
            descriptionFrame.dispose();
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
     * Creates a frame to display the description of the maze solver results.
     *
     * @param text the description text to display
     */
    public void descriptionFrame(String text) {
        // create frame and panel
        this.descriptionFrame = new JFrame("Maze Description");
        JPanel descriptionPanel = new JPanel();
        setLayout(new BoxLayout(descriptionFrame, BoxLayout.PAGE_AXIS));
        descriptionFrame.add(descriptionPanel);
        descriptionFrame.setLocation(this.frame.getX() - 400, this.frame.getY() + 500);
        descriptionFrame.setSize(400, 235);
        descriptionFrame.setVisible(true);

        Dimension dimension = new Dimension();
        dimension.setSize(350, 250);

        // new description set to given text
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
