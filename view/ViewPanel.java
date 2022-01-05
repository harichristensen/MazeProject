package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;

    public ViewPanel() {
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);


        JButton button25 = new JButton();
        button25.setBackground(Color.BLACK);
        button25.setForeground(Color.WHITE);
        button25.setAlignmentX(CENTER_ALIGNMENT);
        button25.addActionListener(e -> {
            MazeView mazeView = new MazeView(25);
        });
        add(button25);

        setVisible(true);
    }
}
