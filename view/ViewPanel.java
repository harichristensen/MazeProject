package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * IMPORTANT
 * This code is not currently being used as the maze solver can not handle larger mazes yet.
 * When it can this code can be used to create a frame to display different maze size options.
 */

public class ViewPanel extends JPanel {
    /**
     * Initialize view panel.
     *
     */
    public ViewPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalStrut(5));

        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);

        JLabel title = new JLabel("Choose Maze Size");
        title.setFont(new Font("Serif", Font.PLAIN, 45));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setMaximumSize(title.getPreferredSize());
        add(title);

        add(Box.createVerticalStrut(5));

        addSizeButton(25);
        addSizeButton(50);
        addSizeButton(75);
    }

    /**
     * Add a button to the panel that opens a maze of the selected size
     *
     * @param size the size of maze to be created
     */
    public void addSizeButton(int size) {
        Dimension ButtonDimension = new Dimension();
        ButtonDimension.setSize(500, 500);

        JButton button = new JButton(size + "x" + size);
        button.setSize(ButtonDimension);
        button.setFont(new Font("Serif", Font.PLAIN, 35));
        button.setMaximumSize(ButtonDimension);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            MazeView mazeView = new MazeView(new Maze(size), false);
            mazeView.setLocation(700, 200);
        });
        add(button);

        add(Box.createVerticalStrut(30));
    }

}
