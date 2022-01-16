package view;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

/**
 * A singleton that is a container storing images. When an image is to be accessed, first search the
 * container. If it isn't found, read the image from disk and store it in the container for future
 * accesses.
 */
public class ImageCache {
    /* The one instance for this Singleton class. */
    private static ImageCache instance = null;

    /**
     * @return the actual instance of the class
     */
    public static ImageCache getInstance(int size) {
        if (instance == null) {
            instance = new ImageCache(size);
        }
        return instance;
    }

    /** The name of the directory that contains the images. */
    public static final String IMAGE_DIRECTORY = "Z:\\Documents\\Maze\\MazeProject\\images\\";

    /** The container where the images are actually stored. */
    private HashMap<String, ImageIcon> cache;


    /** Size to calculate size of images. */
    private final int imageSize;

    private ImageCache(int size) {
        cache = new HashMap<>();
        if (size == 25) {
            this.imageSize = 30;
        } else if (size == 50) {
            this.imageSize = 16;
        } else {
            this.imageSize = 11;
        }
    }

    /**
     * @param fileName the name of the file containing the image to be retrieved
     * @return the image with name fileName
     */
    public ImageIcon getImage(String fileName) {
        ImageIcon image = cache.get(fileName);

        if (image == null) {
            try {
                image = new ImageIcon(IMAGE_DIRECTORY + fileName);
                Image oldImage = image.getImage();
                Image newImage = oldImage.getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                image = new ImageIcon(newImage);
                cache.put(fileName, image);
            } catch (Exception e) {
                throw new IllegalArgumentException("The image in " + IMAGE_DIRECTORY + fileName
                        + " could not be read");
            }
        }
        return image;
    }
}
