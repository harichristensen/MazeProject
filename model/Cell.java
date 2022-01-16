package model;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Cell {
    protected int x;
    protected int y;


    protected ImageIcon imageIcon;
    protected Color colour;

    Maze maze;

    protected Hashtable<String, String> walls;
    protected List<Cell> neighbours;

    private static final Hashtable<String, String> wallList = new Hashtable<>();

    protected boolean visited;
    protected boolean removed;

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
        this.removed=false;



        this.walls = new Hashtable<>();
        this.neighbours = new ArrayList<>();
        walls.put("N", "T"); walls.put("S", "T"); walls.put("W", "T"); walls.put("E", "T");
        wallList.put("N", "S"); wallList.put("S", "N"); wallList.put("W", "E"); wallList.put("E", "W");



        this.maze = maze;

            }

    /**
     * Remove a cell wall and its neighbour wall.
     *
     * @param wall the wall to be removed
     */
    public void removeWall(String wall) {
        this.walls.put(wall, "F");
        Cell thisCell = this;
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
            if (!this.getNeighbours().contains(otherCell) && !otherCell.getNeighbours().contains(this)){
            this.neighbours.add(otherCell);
            otherCell.neighbours.add(thisCell);
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
     * Set visited to true
     *
     */
    public void setRemoved() {
        this.removed = true;
    }


    /**
     * Return a string of information of the cell
     *
     */
    public String getItem() {

        return "X: " + this.x + ", Y: " + this.y + ", Walls: " + this.walls + ", Neighbors: " + neighbours;
        }

    /**
     * Return a string of information of the cell
     *
     */
    public ImageIcon getCellImage() {
        if (!visited && !removed) {
            this.imageIcon = maze.getImages().getImage("pinkcell.png");
            this.colour= new Color(249,148,179);
        } else if (removed) {
            this.imageIcon = maze.getImages().getImage("redcell.png");
            this.colour = Color.red;
        }
        else {
            this.imageIcon = maze.getImages().getImage("purplecell.png");
            this.colour = new Color(72,4,66);
        }
        return imageIcon;
    }

    /**
     * Return a string of information of the cell
     *
     */
    public Color getColour() {
        return colour;
    }
    }