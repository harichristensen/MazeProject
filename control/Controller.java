package control;

import model.Maze;
import util.MazeSolver;
import view.MazeView;

import javax.swing.*;

public class Controller {

    /**
     * Initialize Controller class. Creates a new maze view
     *
     */
    public Controller() {
        newMazeView(this, new Maze(25), false);
    }

    /**
     * Starts the maze solver.
     *
     * @param maze the maze to be solved
     */
    public void startSolver(Maze maze) {
        try {
            new MazeSolver(maze);
        }
        // ignores an out-of-bounds exception
        catch (Exception ignored){
        }
    }

    /**
     * Initializes new MazeView.
     *
     * @param controller the controller of the algorithm
     * @param maze the maze to be displayed
     * @param done if the maze has been solved and updated
     */
    public void newMazeView(Controller controller, Maze maze, boolean done) {
        MazeView mazeView = new MazeView(controller, maze, done);
        mazeView.setLocation(700, 200);
        mazeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
