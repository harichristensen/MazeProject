package MazeAlgorithm;

import model.Maze;
import view.MazeView;

import javax.swing.*;

public class MazeAlgorithm {
    public static void main(String[] args) {
        MazeView mazeView = new MazeView(new Maze(25), false);
        mazeView.setLocation(700, 200);
        mazeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
