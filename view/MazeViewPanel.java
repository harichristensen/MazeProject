package view;

import model.Maze;
import javax.swing.*;

public class MazeViewPanel extends JPanel {
    private Maze maze;
    public MazeViewPanel(int size){
        this.maze = new Maze(size);
    }

}
