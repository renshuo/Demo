package gmail.renshs.bodies3;

import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
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


    List<Camera> cameras = new ArrayList<>();
    Group  world = new Group();



    public Body createBody(double mass, Point3D position){
        Body body = new Body(mass, position);
        bodies.add(body);
        world.getChildren().add(body.getShape());
        body.setWorld(this);
        return body;
    }
    public Body createBody(double mass, double x, double y, double z){
        return createBody(mass, new Point3D(x, y ,z));
    }


    public void createCamera(double farClip, int x ,int y,int z){
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(farClip);
        camera.getTransforms().addAll(new Translate(x, y, z));
        cameras.add(camera);
        world.getChildren().add(camera);
    }


    public void reset(){
        for (Body body : bodies) {
            body.reset();
        }
        this.timeSpend = 0;
        this.times = 0;
        epoch ++;
    }

    public void nextTime(double time){
        timeSpend += time;
        times++;

        for (Body body : bodies) {
            body.calculateForce(bodies);
        }

        for (Body body : bodies) {
            body.nextTime(bodies, time);
        }
    }



    public void logWorld(){
        System.out.print("world: bodyies=" + bodies.size() + " timeSpend=" + timeSpend+", times="+times);
        System.out.println(" {");
        for (Body body : bodies) {
            body.log();
        }
        System.out.println("}");
    }


    public List<Body> getBodies() {
        return bodies;
    }

    public Group getWorld() {
        return world;
    }

    public List<Camera> getCameras() {
        return cameras;
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
