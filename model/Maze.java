package model;

import view.ImageCache;

import javax.swing.*;
import java.util.*;

public class Maze implements GameInfoProvider{
    protected List<List<Cell>> cells;
    protected int size;

    /** If the maze has just been created */
    protected boolean newMaze;

    /** The list of observers to be notified whenever the maze changes. */
    protected List<MazeObserver> observers;



    /**
     * Initialize Maze.
     *
     * @param size the size of the maze
     */
    public Maze(int size) {
        this.size = size;
        this.cells = new ArrayList<>(size);


        for (int i = 0; i < size; i++) {
            cells.add(new ArrayList<>());
        }
        createMaze(size);

        observers = new LinkedList<>();

    }

    /**
     * Create a maze with new cells
     *
     * @param size the size of the maze
     */
    public void createMaze(int size) {
        for (int x = 0; x < size; x++) {
            List<Cell> list = new ArrayList<>();
            for (int y = 0; y < size; y++) {
                Cell newCell = new Cell(x, y, this);
                list.add(newCell);
            }
            cells.add(x, list);
        }
        randomizeMaze();
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
     * Remove wall from cell and adjacent cell
     *
     * @param cell main cell to remove wall from
     * @param wall which wall to remove
     */
    public void removeWall(Cell cell, String wall) {
        cell.removeWall(wall);
    }

    /**
     * get size of maze
     *
     */
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        Maze testmaze = new Maze(100);
        System.out.println(testmaze.cells.size());
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                System.out.println(testmaze.getCell(x,y).getItem());
            }
        }
     }

    /**
     * Returns current instance of maze
     *
     */
    public Maze getMaze() {
        return this;
    }

    /**
     * Add observer to the list of observers.
     *
     * @param observer an observer of the maze
     */
    public void addObserver(MazeObserver observer) {
        observers.add(observer);
    }

    /**
     * Set new maze status
     *
     * @param status the new status of the maze
     */
    public void setNewMaze(boolean status) {
        this.newMaze = status;
    }

    /**
     * Get new maze status
     *
     */
    public boolean getNewMaze() {
        return newMaze;
    }

    /**
     * Invoke mazeChanged on all the observers of the maze.
     */
    private void notifyObservers() {
        for (MazeObserver obs : observers) {
            obs.mazeChanged();
        }
    }

    public int randomInt(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    public void randomizeMaze() {

        for (List<Cell> cellList: getCellList()) {
            for (Cell cell: cellList) {
                int change = randomInt(0, 4);
                while (change != 0) {
                    int wall = randomInt(1, 4);
                    change = randomInt(0, 1);
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
