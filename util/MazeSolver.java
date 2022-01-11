package util;

import model.Cell;
import model.Maze;

import java.util.Stack;

public class MazeSolver {
    protected Cell cell;
    protected Stack<Cell> cells;
    protected Maze maze;

    public MazeSolver(Maze maze) {

        this.cells = new Stack<>();
        cells.push(maze.getCell(0, 0));
        this.maze = maze;
        System.out.println("Starting the solver");
        int done = solve();
        if (done == 1) {
            System.out.println("Maze completed");
        } else {
            System.out.println("Impossible maze");
        }
    }

    public int solve () {
        Cell cell = cells.lastElement();
        System.out.println("Current Cell : " + cell.getItem());
        if(cell.getNeighbours().size() == 0) {
            System.out.println("Neighbours: " + cell.getNeighbours());
            Cell removed = cells.pop();
            cells.lastElement().removeNeighbour(removed);
            System.out.println("Removed: " + removed.getItem());
            if (cells.isEmpty()) {
                return 0;
            }
            solve();
        }
        if (cells.lastElement().getX()==10 && cells.lastElement().getY()==10) {
            return 1;
        }
        Cell added = cells.push(cell.getNeighbours().get(0));
        added.removeNeighbour(cell);
        System.out.println("Added: " + added.getItem());
        solve();
        return 0;
    }

    public static void main(String[] args) {
        Maze newMaze = new Maze(25);
        new MazeSolver(newMaze);
    }
}
