package model;
import view.View;

import javax.swing.*;
import java.util.*;

public class Maze implements GameInfoProvider{
    protected List<List<Cell>> cells;
    protected int size;

    /** The list of observers to be notified whenever the maze changes. */
    protected List<GameObserver> observers;

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
    }

    /**
     * Get a cell from maze
     *
     * @param x x coordinate of cell
     * @param y y coordinate of cell
     */
    @Override
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
    @Override
    public Maze getMaze() {
        return this;
    }

    /**
     * Add observer to the list of observers.
     *
     * @param observer an observer of the maze
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
}
