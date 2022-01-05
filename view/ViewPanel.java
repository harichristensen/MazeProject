package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {
    private static final int BUTTON_WIDTH = 600;
    private static final int BUTTON_HEIGHT = 400;

    public ViewPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalStrut(30));



        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);

        JLabel title = new JLabel("Choose Maze Size");
        title.setFont(new Font("Serif", Font.PLAIN, 45));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setMaximumSize(title.getPreferredSize());
        add(title);

        add(Box.createVerticalStrut(30));

        Dimension ButtonDimension = new Dimension();
        ButtonDimension.setSize(500, 500);

        JButton button25 = new JButton("25x25");
        button25.setSize(ButtonDimension);
        button25.setFont(new Font("Serif", Font.PLAIN, 35));
        button25.setMaximumSize(ButtonDimension);
        button25.setBackground(Color.BLACK);
        button25.setForeground(Color.WHITE);
        button25.setAlignmentX(CENTER_ALIGNMENT);
        button25.addActionListener(e -> {
            MazeView mazeView = new MazeView(25);
        });
        add(button25);

        add(Box.createVerticalStrut(30));

        JButton button50 = new JButton("50x50");
        button50.setSize(ButtonDimension);
        button50.setFont(new Font("Serif", Font.PLAIN, 35));
        button50.setMaximumSize(ButtonDimension);
        button50.setBackground(Color.BLACK);
        button50.setForeground(Color.WHITE);
        button50.setAlignmentX(CENTER_ALIGNMENT);
        button50.addActionListener(e -> {
            MazeView mazeView = new MazeView(50);
        });
        add(button50);

        add(Box.createVerticalStrut(30));

        JButton button100 = new JButton("100x100");
        button100.setSize(ButtonDimension);
        button100.setFont(new Font("Serif", Font.PLAIN, 35));
        button100.setMaximumSize(ButtonDimension);
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
