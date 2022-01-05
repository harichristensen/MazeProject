package model;
import java.util.*;

public class Maze {
    protected List<List<Cell>> cells;
    protected int size;

    /**
     * Initialize Maze.
     *
     * @param size the size of the maze
     */
    public Maze(int size) {
        this.size = size;
        this.cells = new ArrayList<>(size);

        createMaze(size);
    }

    /**
     * Create a maze with new cells
     *
     * @param size the size of the maze
     */
    public void createMaze(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells.get(x).set(y, new Cell(x, y, this));
            }
        }
    }

    /**
     * Get a cell from maze
     *
     * @param x x coordinate of cell
     * @param y y coordinate of cell
     */
    public Cell getCell(int x, int y) {
        return cells.get(x).get(y);
    }

    /**
     * Remove wall from cell and adjacent cell
     *
     * @param cell main cell to remove wall from
     * @param wall which wall to remove
     */
    public void removeWall(Cell cell, String wall) {
        cell.removeWall(wall);
    }

    public int getSize() {
        return size;
    }

}
