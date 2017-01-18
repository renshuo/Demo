package org.sren.rgis.ui;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * Created by sren on 17-1-17.
 */
public class Controller {
    public ImageView imageView;


    public void pressKey(KeyEvent keyEvent) {
        System.out.println(keyEvent);
    }
}
