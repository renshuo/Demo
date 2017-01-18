package org.sren.rgis.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageWriter;

/**
 * Created by sren on 17-1-17.
 */
public class ViewManager {
    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 1280;

    byte[][] background = new byte[CANVAS_WIDTH][CANVAS_HEIGHT];


    private final WritableImage image;

    public ViewManager(ImageView view) {
        image = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
        view.setImage(image);
    }

}
