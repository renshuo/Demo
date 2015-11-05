package gmail.renshs.bodies3;

import gmail.renshs.bodies3.model.WorldModel;
import gmail.renshs.bodies3.phy.SpaceWorld;
import gmail.renshs.bodies3.ui.BodyConfig;
import gmail.renshs.bodies3.view.ViewWorld;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * Created by sren on 15-10-15.
 */
public class WorldLine {

    double timeSpeed = 1;
    long startTime = 0;
    double timeSpend = 0;
    int times;
    int epoch = 0;



    SpaceWorld phyWorld;
    ViewWorld viewWorld;

    Pane pane;
    WorldModel worldModel;

    public WorldLine(Pane pane, WorldModel model){
        this.pane = pane;
        this.worldModel = model;

        phyWorld = new SpaceWorld(model);
        viewWorld = new ViewWorld(this.pane, model, phyWorld);
    }

    public void start(){
        viewWorld.start();
    }

    public void stop(){
        viewWorld.stop();
    }

    public void pause(){
        viewWorld.stop();
    }

    public void clean(){

    }


    public void reset(ObservableList<BodyConfig> items, Double aDouble){
        clean();
        this.timeSpend = 0;
        this.times = 0;
        this.startTime = 0;
        epoch ++;

    }


    public void log(){

    }

    public double getTimeSpeed() {
        return timeSpeed;
    }

    public void setTimeSpeed(double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    public ViewWorld getViewWorld() {
        return viewWorld;
    }
}
