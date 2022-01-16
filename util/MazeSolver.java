package util;

import model.Cell;
import model.Maze;
import view.MazeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        solve();
        System.out.println(result);

    }


    public void solve() {
        try {
            if (cells.size() != 0) {
                Cell cell = cells.lastElement();
                // No neighbours (dead end)
                if (cell.getNeighbours().size() == 0) {
                    cells.pop();
                    cell.setVisited();
                    // End of stack
                    if (cells.size() == 0) {
                        return;
                    }
                    solve();
                }

                // At end position
                if (cells.lastElement().getX() == 10 && cells.lastElement().getY() == 10) {
                    this.result = "Maze Completed!";
                    return;
                }

                Cell added = cells.push(cell.getNeighbours().get(0));
                added.removeNeighbour(cell);
                cell.removeNeighbour(added);
                solve();
            }
        } catch (Exception ignored) {
        }
    }

}
