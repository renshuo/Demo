package gmail.renshs.bodies3.phy;

import gmail.renshs.bodies3.Axis3;
import gmail.renshs.bodies3.Position;

import java.util.List;

/**
 * Created by sren on 15-10-29.
 */
public class SpaceObj {

    String name;
    int id;
    double mass;
    Position position;
    Axis3 translate;
    Axis3 velocity;

    Axis3 force;


    /**
     * F = (G*m1*m2)/r^2
     * Fx = F*X/r, Fy=F*Y/r, Fz=F*Z/r
     * so: Fx = (G*m1*m2)*X/r^3
     * so: Fx = (G*m1*m2/r^3) * X
     * and so on Fy, Fz
     *
     * then: this.force = this.force+(Fx,Fy,Fz)
     * @param objs all objects which have force with this obj
     * @param G the G param for newton3
     */
    public void gForce(List<SpaceObj> objs, double G){
        force = new Axis3();
        for (SpaceObj obj : objs) {
            if (obj.equals(this)){
                continue;
            }
            force = force.jia(gForce(obj, G));
        }
    }

    /**
     * @see gForce(List<SpaceObj> objs, double g)
     * @param obj
     * @param G
     * @return
     */
    public Axis3 gForce(SpaceObj obj, double G){;
        double distance = obj.position.distance(this.position);
        double forceParam = G*this.mass * obj.mass/Math.pow(distance, 3);
        return obj.position.jian(this.position).cheng(forceParam);
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

    public Axis3 getForce() {
        return force;
    }

    public void setForce(Axis3 force) {
        this.force = force;
    }

    public Axis3 getTranslate() {
        return translate;
    }

    public void setTranslate(Axis3 translate) {
        this.translate = translate;
    }
}
