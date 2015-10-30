package gmail.renshs.bodies3.phy;

import gmail.renshs.bodies3.Axis3;
import gmail.renshs.bodies3.Position;
import gmail.renshs.bodies3.model.BodyModel;
import gmail.renshs.bodies3.model.WorldModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sren on 15-10-29.
 */
public class SpaceWorld {


    double G = 1;

    List<SpaceObj> objs = new ArrayList<>();


    public SpaceWorld(WorldModel model) {
        G = (Double) model.getPhyParams("G");
        model.getBodies().forEach(this::createObj);
    }

    public SpaceObj createObj(BodyModel model){
        SpaceObj obj = new SpaceObj();
        obj.setId(model.getId());
        obj.setName(model.getName());
        obj.setMass(model.getMass());
        obj.setPosition(model.getPosition());
        obj.setVelocity(model.getVelocity());
        objs.add(obj);
        return obj;
    }


    public void nextFrame(double time){
        calculateNextPosition(time);
    }


    private void calculateNextPosition(double time){
        calculateVelocity(time);
        for (SpaceObj obj : objs) {
            obj.translate = obj.velocity.cheng(time);
            obj.position = new Position(obj.position.jia(obj.translate));
        }
    }

    private void hitCheck(){

    }

    private void calculateVelocity(double time){
        for (SpaceObj obj : objs) {
            obj.gForce(objs, G);
            Axis3 a = obj.force.chu(obj.mass);
            obj.velocity = obj.velocity.jia(a.cheng(time));
        }
    }

    public void addObj(SpaceObj obj){
        objs.add(obj);
    }

    public void removeObj(SpaceObj obj){
        objs.remove(obj);
    }
    public void cleanObj(){
        objs.clear();
    }


    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public List<SpaceObj> getObjs() {
        return objs;
    }
}
