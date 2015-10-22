package gmail.renshs.bodies3;

import com.sun.javafx.geom.Vec3d;
import gmail.renshs.bodies3.ui.BodyConfig;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sren on 15-10-15.
 */
public class TheWorld {


    double timeSpend = 0;
    int times;
    int epoch = 0;
    List<Body> bodies = new ArrayList<>();
    double G = 1;


    Group  world = new Group();

    AnimationTimer timer =  new AnimationTimer() {
        @Override
        public void handle(long now) {
            nextTime(0.2D);
            if (getTimes() % 100 == 0) {
                log();
            }
        }
    };

    public TheWorld(Pane pane, ObservableList<BodyConfig> items){
        initSubScene(pane);
        initBodies(items);
//        for (Map<String, Object> body : bodyConfigTable.getTableView().getItems()) {
//            world.getChildren().add(BodyConfigTable.createBody(body).getShape());
//        }
    }

    private void initSubScene(Pane pane) {
        PerspectiveCamera camera1 = new PerspectiveCamera(true);
        camera1.setFarClip((double) 1000);
        camera1.getTransforms().addAll(new Translate(0, 0, -100));
        PerspectiveCamera camera = camera1;
        SubScene subScene = new SubScene(world, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);
        pane.getChildren().add(subScene);
    }

    public Body createBody(double mass, Point3D position){
        Body body = new Body(mass, position);
        bodies.add(body);
        body.setWorld(this);
        return body;
    }



    private void initBodies(List<BodyConfig> bodyConfigs){
        for (BodyConfig bodyConfig : bodyConfigs) {
            Body body = createBody(bodyConfig.getMass(), bodyConfig.getPosition());
            body.setRadius(bodyConfig.getRadius());
            body.setVelocity(new Vec3d(bodyConfig.getVelocity()));
            body.setName(bodyConfig.getName());
            body.setPosition(bodyConfig.getPosition());
            world.getChildren().add(body.getShape());
        }

    }

    public void clean(){
        bodies.clear();
        world.getChildren().clear();
    }

    public void reset(ObservableList<BodyConfig> items, Double aDouble){
        this.G = aDouble;
        pause();
        clean();
        initBodies(items);
        this.timeSpend = 0;
        this.times = 0;
        epoch ++;

    }


    public void start(){
        timer.start();
    }

    public void pause(){
        timer.stop();
    }

    public void nextTime(double time){
        timeSpend += time;
        times++;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Body body : bodies) {
            body.calculateForce(bodies);
        }

        for (Body body : bodies) {
            body.nextTime(time);
        }
    }


    public void log(){
        System.out.print("world: bodyies=" + bodies.size() + " timeSpend=" + timeSpend+", times="+times);
        System.out.println(" {");
        for (Body body : bodies) {
            body.log();
        }
        System.out.println("} " + world.getChildren().size());
    }


    public List<Body> getBodies() {
        return bodies;
    }

    public Group getWorld() {
        return world;
    }



    public double getTimeSpend() {
        return timeSpend;
    }

    public int getTimes() {
        return times;
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }
}
