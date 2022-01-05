package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MazeView extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public MazeView(int size) {
        setTitle("Maze Algorithm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MazeViewPanel mazeViewPanel = new MazeViewPanel(size);
        mazeViewPanel.setVisible(true);
    }
}
