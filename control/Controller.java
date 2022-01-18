package control;

import model.Maze;
import util.MazeSolver;
import view.MazeView;

import javax.swing.*;

public class Controller {

    public Controller() {
        newMazeView(new Maze(25));
    }

    public void startSolver(Maze maze) {
        try {
            new MazeSolver(maze);
        }
        // ignores an out-of-bounds exception
        catch (Exception ignored){
        }
    }

    public void newMazeView(Maze maze) {
        MazeView mazeView = new MazeView(this, new Maze(25), false);
        mazeView.setLocation(700, 200);
        mazeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
