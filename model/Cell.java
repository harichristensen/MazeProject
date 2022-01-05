package model;

import java.util.*;

public class Cell {
    private int x;
    private int y;

    private static int WIDTH;
    private static int HEIGHT;
    protected Hashtable<String, String> walls;

    private static final Hashtable<String, String> wallList = new Hashtable<>();

    /** How frequently (in terms of ticks) the cell is to change. */
    public static final int CHANGE_FREQ = 4;

    /** A count of the number of ticks since creation. */
    private int tickCount;

    /**
     * Initialize cell.
     *
     * @param x the initial x-coordinate for the cell
     * @param y the initial y-coordinate for the cell
     */
    public Cell(int x, int y) {
            this.walls = new Hashtable<>();
            walls.put("N", "T"); walls.put("S", "T"); walls.put("W", "T"); walls.put("E", "T");

            wallList.put("N", "S"); wallList.put("S", "N"); wallList.put("W", "E"); wallList.put("E", "W");

        int tickCount = 0;
            }

    public void removeWall(String wall, Cell other) {
            this.walls.put(wall, "F");

            other.walls.put(Cell.wallList.get(wall), "F");
            }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getItem() {
            return "X: " + this.x + ", Y: " + this.y + ", Walls: " + this.walls;
            }
    }