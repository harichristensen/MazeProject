package model;

import java.util.List;

/**
 * The information provided by the GameModel for outside access.
 */
public interface GameInfoProvider {
    public void addObserver(GameObserver observer);

    public Maze getMaze();

    public Cell getCell(int x, int y);

    //public int getTick();

}