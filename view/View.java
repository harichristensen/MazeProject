package view;

import javax.swing.JFrame;
import java.awt.*;

/**
 * IMPORTANT
 * This code is not currently being used as the maze solver can not handle larger mazes yet.
 * When it can this code can be used to create a frame to display different maze size options.
 */

public class View extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public View() {
        setTitle("Maze Size Selection");
        setSize(WIDTH, HEIGHT);

        ViewPanel viewPanel = new ViewPanel();
        viewPanel.setSize(WIDTH, HEIGHT);
        viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(viewPanel);
    }


    public static final long serialVersionUID = 1;
}
