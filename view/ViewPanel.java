package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 150;

    public ViewPanel() {
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);


        JButton button25 = new JButton("25x25");
        button25.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button25.setBackground(Color.BLACK);
        button25.setForeground(Color.WHITE);
        button25.setAlignmentX(CENTER_ALIGNMENT);
        button25.addActionListener(e -> {
            MazeView mazeView = new MazeView(25);
        });
        add(button25);

        JButton button50 = new JButton("50x50");
        button50.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button50.setBackground(Color.BLACK);
        button50.setForeground(Color.WHITE);
        button50.setAlignmentX(CENTER_ALIGNMENT);
        button50.addActionListener(e -> {
            MazeView mazeView = new MazeView(50);
        });
        add(button50);

        JButton button100 = new JButton("100x100");
        button100.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button100.setBackground(Color.BLACK);
        button100.setForeground(Color.WHITE);
        button100.setAlignmentX(CENTER_ALIGNMENT);
        button100.addActionListener(e -> {
            MazeView mazeView = new MazeView(100);
        });
        add(button100);

        setVisible(true);
    }
}
