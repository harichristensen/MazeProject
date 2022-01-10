package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ImageIcons {
    String path = "Z:\\Documents\\Maze\\MazeProject\\images\\";
    Hashtable<ImageIcon, String> list;
    List<String> nameList;
    public ImageIcons(int size) {
        nameList = new ArrayList<>();
        nameList.add(path);
        list = new Hashtable<>();

    }
}
