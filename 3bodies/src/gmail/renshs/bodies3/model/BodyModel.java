package gmail.renshs.bodies3.model;

import com.sun.javafx.geom.Vec3d;
import gmail.renshs.bodies3.Axis3;
import gmail.renshs.bodies3.Position;
import gmail.renshs.bodies3.ui.BodyConfig;
import javafx.geometry.Point3D;

import java.io.Serializable;

/**
 * Created by sren on 15-10-27.
 */
public class BodyModel implements Serializable {

    static final long serialVersionUID = 2L;

    static int id_seq = 0;

    String name;
    int id;
    double mass;
    double radius;
    Position position= new Position();
    Axis3 velocity = new Axis3();


    public BodyModel(BodyConfig cfg) {
        this.name = cfg.getName();
        this.id = id_seq++;
        this.mass = cfg.getMass();
        this.radius = cfg.getRadius();
        this.position = new Position(cfg.getPosition().getX(), cfg.getPosition().getY(), cfg.getPosition().getZ());
        this.velocity = new Axis3(cfg.getVelocity().x, cfg.getVelocity().y, cfg.getVelocity().z);
    }


    public BodyConfig getBodyConfig(){
        BodyConfig bc = new BodyConfig();
        bc.setName(name);
        bc.setMass(mass);
        bc.setRadius(radius);
        bc.setPosition(new Point3D(position.getX(), position.getY(), position.getZ()));
        bc.setVelocity(new Vec3d(velocity.getX(), velocity.getY(), velocity.getZ()));
        return bc;
    }




    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Axis3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Axis3 velocity) {
        this.velocity = velocity;
    }
}
