package util;

import model.Cell;
import model.Maze;

import java.util.Stack;

public class MazeSolver{
    // Stack that holds cells
    protected Stack<Cell> cells;

    // Maze to be solved
    protected Maze maze;

    // Result of the solver
    protected String result;

    /**
     * Initialize Maze Solver.
     *
     * @param maze the initial maze to solve
     */
    public MazeSolver(Maze maze) {
        // Initialize attributes
        this.result = "Impossible maze";
        this.cells = new Stack<>();
        // Starting position
        cells.push(maze.getCell(0, 0));
        this.maze = maze;

        // start the solver
        solve();

        // print result of solver
        System.out.println(result);

    }


    public void solve() {
        // checks if stack is empty
        while (!cells.isEmpty()) {
            // initializes and defines working cell
            Cell cell = cells.lastElement();

            // sets cell to visited. Used to change colour of cell
            cell.setVisited();

            // if at end point of maze
            if (cell.getX() == maze.getSize()-1 && cells.lastElement().getY() == maze.getSize()-1) {
                this.result = "Maze Completed!";
                return;
            }
            // No neighbours (dead end)
            if (cell.getNeighbours().size() == 0) {
                // remove cell from stack
                cells.pop();

                // if removed cell is no longer in stack(path)
                if (!cells.contains(cell)) {
                    // sets cell to removed. Used to change cell colour
                    cell.setRemoved();
                }
                // if end of stack
                if (cells.size() == 0) {
                    return;
                }
                // recursive function call
                solve();
            }

            // adds next cell to stack from end of the list of current cell's neighbours
            Cell added = cells.push(cell.getNeighbours().get(cell.getNeighbours().size()-1));

            // removes the added cell from current cell's neighbour list
            added.removeNeighbour(cell);

            // removes the current cell from added cell's neighbour list
            cell.removeNeighbour(added);

            // recursive function call
            solve();

        }
    }
}
