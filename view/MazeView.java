package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MazeView extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public MazeView(int size) {
        removeAll();
        setTitle("Maze Algorithm");
        setSize(WIDTH, HEIGHT);
        MazeViewPanel mazeViewPanel = new MazeViewPanel(size);
        mazeViewPanel.setSize(WIDTH, HEIGHT);
        mazeViewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mazeViewPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(mazeViewPanel);
        validate();
    }
}
