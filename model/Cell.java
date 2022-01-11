package model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.*;

public class Cell {
    protected int x;
    protected int y;

    protected ImageIcon image;
    Maze maze;

    private static int WIDTH;
    private static int HEIGHT;
    protected Hashtable<String, String> walls;

    private static final Hashtable<String, String> wallList = new Hashtable<>();

    private ImageList imageList;

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
    public Cell(int x, int y, Maze maze) {
        this.x = x;
        this.y = y;
        this.walls = new Hashtable<>();

        this.imageList = new ImageList();

        walls.put("N", "T"); walls.put("S", "T"); walls.put("W", "T"); walls.put("E", "T");

        wallList.put("N", "S"); wallList.put("S", "N"); wallList.put("W", "E"); wallList.put("E", "W");


        this.maze = maze;

        int tickCount = 0;
            }

    public void removeWall(String wall) {
            this.walls.put(wall, "F");
            Cell otherCell = null;
            switch (wall) {
                case "N":
                    if (y != 0) {
                        otherCell = maze.getCell(x, y-1);
                    }
                    break;
                case "S":
                    if (y != maze.getSize() - 1) {
                        otherCell = maze.getCell(x, y+1);
                    }
                    break;
                case "W":
                    if (x != 0) {
                        otherCell = maze.getCell(x-1, y);
                    }
                    break;
                case "E":
                    if (x != maze.getSize() - 1) {
                        otherCell = maze.getCell(x+1, y);
                    }
                    break;
                default:
                    break;
            }
            if (otherCell != null) {
                otherCell.walls.put(Cell.wallList.get(wall), "F");
            }

    }

    public Hashtable<String, String> getWalls() {
        return walls;
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