package gmail.renshs.bodies3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sren on 15-10-27.
 */
public class WorldModel implements Serializable{

    static final long serialVersionUID = 1L;

    String name;
    int epoch;
    int timeSpeed;

    Map<String, Object> phyParams = new HashMap<>();


    List<BodyModel> bodies = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public int getTimeSpeed() {
        return timeSpeed;
    }

    public void setTimeSpeed(int timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    public List<BodyModel> getBodies() {
        return bodies;
    }

    public void setBodies(List<BodyModel> bodies) {
        this.bodies = bodies;
    }

    public Object getPhyParams(String name){
        return phyParams.get(name);
    }

    public void setPhyParams(String name, Object value){
        phyParams.put(name, value);
    }

}

