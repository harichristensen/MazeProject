package model;

import java.util.Hashtable;

public class ImageList {
    private final Hashtable<Hashtable<String, String>, String> imageList;
    Hashtable<String, String> redcell_right_left_top;
    Hashtable<String, String> redcell_left_top;
    Hashtable<String, String> redcell_left;
    Hashtable<String, String> redcell_right_left_top_bottom;
    Hashtable<String, String> redcell_right_left;
    Hashtable<String, String> redcell_right;
    //Hashtable<String, String> redcell_right_left;
    //Hashtable<String, String> redcell_right_left;
    //Hashtable<String, String> redcell_right_left;



    public ImageList() {
        imageList = new Hashtable<>();

        setWalls("redcell", "TTTT");

    }

    public void setWalls(String name, String string) {

        Hashtable<String, String> cell = new Hashtable<>();
        char charN = string.charAt(3);
        char charS = string.charAt(2);
        char charW = string.charAt(1);
        char charE = string.charAt(0);
        if (charN == 'T') {
            cell.put("N", "T");
        } else {
            cell.put("N", "F");
        }

        if (charS == 'T') {
            cell.put("S", "T");
        } else {
            cell.put("S", "F");
        }

        if (charW == 'T') {
            cell.put("W", "T");
        } else {
            cell.put("W", "F");
        }

        if (charE == 'T') {
            cell.put("E", "T");
        } else {
            cell.put("E", "F");
        }

        imageList.put(cell, name + ".png");
    }

    public Hashtable<Hashtable<String, String>, String> getImageList() {
        return imageList;
    }
}
