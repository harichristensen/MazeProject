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

    public void createMaze(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells.get(x).set(y, new Cell(x, y));
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells.get(x).get(y);
    }

    public void removeWall(Cell cell, String wall) {
        int currentX = cell.getX();
        int currentY = cell.getY();
        Cell otherCell = cells.get(currentX).get(currentY);
        cell.removeWall(wall, otherCell);
    }

}
