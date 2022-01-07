package model;

import java.util.List;

/**
 * The information provided by the GameModel for outside access.
 */
public interface GameInfoProvider {
    void addObserver(MazeObserver observer);

    Maze getMaze();

    Cell getCell(int x, int y);

    void setNewMaze(boolean status);

    boolean getNewMaze();

    //int getTick();

}