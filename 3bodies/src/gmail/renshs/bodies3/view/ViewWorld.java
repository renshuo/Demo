package gmail.renshs.bodies3.view;

import gmail.renshs.bodies3.Axis3;
import gmail.renshs.bodies3.Position;
import gmail.renshs.bodies3.model.BodyModel;
import gmail.renshs.bodies3.model.WorldModel;
import gmail.renshs.bodies3.phy.SpaceObj;
import gmail.renshs.bodies3.phy.SpaceWorld;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sren on 15-10-29.
 */
public class ViewWorld {

    Group world = new Group();



    List<ViewObj> objs = new ArrayList<>();
    Map<Integer, ViewObj> objMap = new HashMap<>();

    SpaceWorld phyWorld;


    long frame;

    double timeSpeed  =1;


    public ViewObj createObj(BodyModel model){
        ViewObj obj = new ViewObj();
        obj.setName(model.getName());
        obj.setId(model.getId());
        obj.setRadius(model.getRadius());
        Sphere shape = new Sphere(model.getRadius());
        Position position = model.getPosition();
        shape.getTransforms().add(new Translate(position.getX(), position.getY(), position.getZ()));
        world.getChildren().add(shape);
        obj.setShape(shape);
        objs.add(obj);
        objMap.put(model.getId(), obj);
        return obj;
    }


    public ViewWorld(Pane pane, WorldModel model, SpaceWorld phyWorld){
        world = new Group();

        this.timeSpeed = model.getTimeSpeed();
        this.phyWorld = phyWorld;
        model.getBodies().forEach(this::createObj);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip((double) 1000);
        camera.getTransforms().addAll(new Translate(0, 0, -100));

        SubScene subScene = new SubScene(world, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);
        pane.getChildren().clear();
        pane.getChildren().add(subScene);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    AnimationTimer timer =  new AnimationTimer() {
        @Override
        public void handle(long now) {
            NextFrame(now);
        }
    };


    long lastNow;
    private void NextFrame(long now){
        if (lastNow==0){
            lastNow = now;
            return;
        }
        double dueTime = ((now-lastNow))/1000000000D*timeSpeed;
        lastNow = now;
        frame++;

        phyWorld.nextFrame(dueTime);
        for (SpaceObj spaceObj : phyWorld.getObjs()) {
            ViewObj vo = objMap.get(spaceObj.getId());
            Axis3 translate = spaceObj.getTranslate();
            vo.getShape().getTransforms().add(new Translate(translate.getX(), translate.getY(), translate.getZ()));
        }

    }


    public SpaceWorld getPhyWorld() {
        return phyWorld;
    }

    public void setPhyWorld(SpaceWorld phyWorld) {
        this.phyWorld = phyWorld;
    }
}
