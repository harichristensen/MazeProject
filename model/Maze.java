package model;

import util.ImageCache;

import java.util.*;

public class Maze{
    // 2D list of cells
    protected List<List<Cell>> cells;

    // size of maze
    protected int size;

    // Cache of images for cells
    protected ImageCache imageCache;

    /**
     * Initialize Maze.
     *
     * @param size the size of the maze
     */
    public Maze(int size) {
        // Reset in case maze size changes
        ImageCache.reset();
        // Define attributes
        this.imageCache = ImageCache.getInstance(size);
        this.size = size;

        // Create arraylist the size of the maze
        this.cells = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            cells.add(new ArrayList<>());
        }

        createMaze(size);


    }

    /**
     * Create a maze with new cells
     *
     * @param size the size of the maze
     */
    public void createMaze(int size) {
        // for every x up to size
        for (int x = 0; x < size; x++) {
            // create list to hold y cells
            List<Cell> list = new ArrayList<>();
            // for every y up to size
            for (int y = 0; y < size; y++) {
                Cell newCell = new Cell(x, y, this);
                list.add(newCell);
            }
            // add list to x coordinate
            cells.add(x, list);
        }
        // randomize the maze walls
        randomizeMaze();
        // set first and last cell to not have walls to lower chance of instance impossible mazes
        Cell initialCell = cells.get(0).get(0);
        initialCell.removeWall("E");
        initialCell.removeWall("S");
        Cell endCell = cells.get(size-1).get(size-1);
        endCell.removeWall("W");
        endCell.removeWall("N");
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
     * Get the list of cells from maze
     *
     */
    public List<List<Cell>> getCellList() {
        return cells;
    }

    /**
     * get size of maze
     *
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets maze ImageCache instance
     *
     */
    public ImageCache getImages() {
        return imageCache;
    }

    /**
     * Returns random int between a min and max
     *
     */
    public int randomInt(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    /**
     * Randomizes the cell walls
     *
     */
    public void randomizeMaze() {

        for (List<Cell> cellList: getCellList()) {
            for (Cell cell: cellList) {
                // The chance that determines if one of the cell's walls will be removed
                int change = randomInt(0, 14);
                while (change != 0) {
                    // which wall to be removed
                    int wall = randomInt(1, 4);
                    // if another of the cell's walls will be removed
                    change = randomInt(0, 1);
                    // removes wall
                    switch (wall) {
                        case 1 -> cell.removeWall("N");
                        case 2 -> cell.removeWall("S");
                        case 3 -> cell.removeWall("W");
                        case 4 -> cell.removeWall("E");
                        default -> {
                        }
                    }
                }
            }
        }
    }

}
