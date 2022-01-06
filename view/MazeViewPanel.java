package view;

import model.Maze;
import javax.swing.*;

public class MazeViewPanel extends JPanel {
    protected Maze maze;

    public MazeViewPanel(int size){
        this.maze = new Maze(size);
    }

}
