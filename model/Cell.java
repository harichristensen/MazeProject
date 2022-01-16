package model;

import util.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * An instance of a cell of a maze
 */
public class Cell {
    // x-coordinate of cell
    protected int x;
    // y-coordinate of cell
    protected int y;

    // Colour of the cell
    protected Color colour;

    protected static Maze maze;

    // List of cell walls that can be T(exist) or F(removed)
    protected Hashtable<String, String> walls;

    // List of neighbouring cells
    protected List<Cell> neighbours;

    // List of walls and their opposite
    private static final Hashtable<String, String> wallList = new Hashtable<>();

    // If the cell has been visited in the solver
    protected boolean visited;

    // If the cell has been removed in the solver
    protected boolean removed;

    /**
     * Initialize cell.
     *
     * @param x the initial x-coordinate for the cell
     * @param y the initial y-coordinate for the cell
     * @param maze the maze that contains the cell
     */
    public Cell(int x, int y, Maze maze) {
        // Initialize attributes
        this.x = x;
        this.y = y;
        Cell.maze = maze;

        this.visited=false;
        this.removed=false;

        this.neighbours = new ArrayList<>();
        this.walls = new Hashtable<>();
        // all walls are set to T(exist) when cell is initialized
        walls.put("N", "T"); walls.put("S", "T"); walls.put("W", "T"); walls.put("E", "T");

        wallList.put("N", "S"); wallList.put("S", "N"); wallList.put("W", "E"); wallList.put("E", "W");




            }

    /**
     * Remove a cell wall and its neighbour wall.
     *
     * @param wall the wall to be removed
     */
    public void removeWall(String wall) {
        // removes wall from this cell
        this.walls.put(wall, "F");

        // Initialize other cell that shares the wall removed from this cell
        Cell otherCell = null;

        // Defines other cell and checks to see if this cell is on the edge of maze(no neighbouring cell)
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
        // if neighbour exists
        if (otherCell != null) {
            // if wall was not already removed
            if (!this.getNeighbours().contains(otherCell) && !otherCell.getNeighbours().contains(this)){
                // add other cell to this cell's neighbours
                this.neighbours.add(otherCell);
                // add this cell to other cell's neighbours
                otherCell.neighbours.add(this);
                // change other cell's corresponding wall to F
                otherCell.walls.put(wallList.get(wall), "F");
            }
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
     * Remove one of cell's neighbours
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
     * Set removed to true
     *
     */
    public void setRemoved() {
        this.removed = true;
    }

    /**
     * Return a string of information about the cell
     *
     */
    public String getItem() {
        return "X: " + this.x + ", Y: " + this.y + ", Walls: " + this.walls + ", Neighbors: " + neighbours;
        }

    /**
     * Return the image of cell. The colour depends on visited and removed attributes and if the cell is the first or
     * end cell.
     *
     */
    public ImageIcon getCellImage() {
        // initialize image
        ImageIcon image;
        // Initial and end cell
        if((x == 0 && y==0) || (x== maze.getSize()-1 && y== maze.getSize()-1)) {
            image = maze.getImages().getImage("yellowcell.png");
            this.colour = new Color(184, 150, 0);
        }
        // if solver has not visited or removed this cell
        else if (!visited && !removed) {
            image = maze.getImages().getImage("pinkcell.png");
            this.colour= new Color(249,148,179);
        }
        // if solver has removed this cell
        else if (removed) {
            image = maze.getImages().getImage("redcell.png");
            this.colour = Color.red;
        }
        // if solver has visited but has not removed this cell
        else {
            image = maze.getImages().getImage("purplecell.png");
            this.colour = new Color(72,4,66);
        }
        return image;
    }

    /**
     * Return the colour of the cell
     *
     */
    public Color getColour() {
        return colour;
    }
    }