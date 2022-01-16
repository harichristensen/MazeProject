package util;

import model.Cell;
import model.Maze;
import org.w3c.dom.events.EventException;
import view.MazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver{
    protected Stack<Cell> cells;
    protected Maze maze;
    protected String result;
    protected MazeView mazeView;
    protected Timer solverDelay;

    /**
     * Initialize Maze Solver.
     *
     * @param maze the initial maze to solve
     */
    public MazeSolver(MazeView mazeView, Maze maze) {
        this.result = "Impossible maze";
        this.cells = new Stack<>();
        this.mazeView = mazeView;
        // Starting position
        cells.push(maze.getCell(0, 0));
        this.maze = maze;
        System.out.println("\n\n\nSTART SOLVE");
        solve();
        System.out.println(result);

    }


    public void solve() {
        try {
            while (!cells.isEmpty()) {
                Cell cell = cells.lastElement();
                System.out.println(cell.getItem());
                cell.setVisited();
                // No neighbours (dead end)
                if (cell.getNeighbours().size() == 0) {
                    cells.pop();
                    if (!cells.contains(cell)) {
                        cell.setRemoved();
                    }
                    // End of stack
                    if (cells.size() == 0) {
                        return;
                    }
                    solve();
                }

                // At end position
                if (cells.lastElement().getX() == maze.getSize()-1 && cells.lastElement().getY() == maze.getSize()-1) {
                    this.result = "Maze Completed!";
                    return;
                }
                Cell added = cells.push(cell.getNeighbours().get(cell.getNeighbours().size()-1));
                added.removeNeighbour(cell);
                cell.removeNeighbour(added);
                solve();
            }
        }
        catch (Exception ignored) {
        }
    }

}
