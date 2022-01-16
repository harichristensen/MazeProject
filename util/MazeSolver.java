package util;

import model.Cell;
import model.Maze;
import view.MazeView;

import javax.swing.*;
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
            while (!cells.isEmpty()) {
                Cell cell = cells.lastElement();
                cell.setVisited();
                if (cell.getX() == maze.getSize()-1 && cells.lastElement().getY() == maze.getSize()-1) {
                    this.result = "Maze Completed!";
                    return;
                }
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
                System.out.println(cell.getItem());

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
