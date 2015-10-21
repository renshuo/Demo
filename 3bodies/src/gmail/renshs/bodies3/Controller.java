package gmail.renshs.bodies3;

import com.sun.javafx.geom.Vec3d;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import java.awt.*;


public class Controller {

    public TextField Gtext;

    TheWorld world = new TheWorld();


    public Button play;
    public Pane pane;

    int i = 0;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            world.nextTime(0.1D);

            if (world.getTimes() % 100 == 0) {
                world.logWorld();
            }
        }
    };

    private SubScene subScene;

    public void initialize(){
        System.out.println("init");

        world.setG(Double.parseDouble(Gtext.getText()));
        world.createCamera(1000, 0 ,0, -100);
        Body sun = world.createBody(1, -10,  0, 0);
        Body earth = world.createBody(1,  10,   00,  0);
        world.createBody(01, 0, 10, 0);

        // Use a SubScene
        subScene = new SubScene(world.getWorld(), 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(world.getCameras().get(0));
        pane.getChildren().add(subScene);

    }


    public void start(ActionEvent actionEvent) {
        if (play.getText().equals("start")){
            timer.start();
            System.out.println("timer start");
            play.setText("stop");
        }else{
            timer.stop();
            System.out.println("timer stop");
            play.setText("start");
        }
    }


    public void reset(ActionEvent actionEvent) {
        timer.stop();
        world.reset();
        world.setG(Double.parseDouble(Gtext.getText()));
        timer.start();
    }
}
