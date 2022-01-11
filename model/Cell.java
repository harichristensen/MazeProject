package model;

import java.util.*;
import java.util.List;

public class Cell {
    protected int x;
    protected int y;

    Maze maze;

    protected Hashtable<String, String> walls;
    protected List<Cell> neighbours;

    private static final Hashtable<String, String> wallList = new Hashtable<>();

    protected boolean visited;


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
        this.visited=false;

        this.walls = new Hashtable<>();
        this.neighbours = new ArrayList<>();
        walls.put("N", "T"); walls.put("S", "T"); walls.put("W", "T"); walls.put("E", "T");
        wallList.put("N", "S"); wallList.put("S", "N"); wallList.put("W", "E"); wallList.put("E", "W");



        this.maze = maze;

        int tickCount = 0;
            }

    /**
     * Remove a cell wall and its neighbour wall.
     *
     * @param wall the wall to be removed
     */
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
                this.neighbours.add(otherCell);
                if (!otherCell.getNeighbours().contains(this)) {
                    otherCell.neighbours.add(this);
                }
                otherCell.walls.put(Cell.wallList.get(wall), "F");
            }

    }

    /**
     * Get the neighbours of the cell
     *
     */
    public List<Cell> getNeighbours() {
        return neighbours;
    }

    /**
     * Remove of the cell neighbours
     *
     */
    public void removeNeighbour(Cell cell) {
        neighbours.remove(cell);
    }

    /**
     * Get the walls of the cell
     *
     */
    public Hashtable<String, String> getWalls() {
        return walls;
    }

    /**
     * Get the x-coordinate of the cell
     *
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of the cell
     *
     */
    public int getY() {
        return y;
    }

    /**
     * Set visited to true
     *
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * Return if cell has been visited or not
     *
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Return a string of information of the cell
     *
     */
    public String getItem() {

        return "X: " + this.x + ", Y: " + this.y + ", Walls: " + this.walls + ", Neighbors: " + neighbours;
        }
    }