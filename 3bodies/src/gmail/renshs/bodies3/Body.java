package gmail.renshs.bodies3;

import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import java.util.List;
import java.util.Random;

/**
 * Created by sren on 15-10-21.
 */
public class Body {

    String name;

    double mass = 1;
    double radius = 1;

    Point3D position = Point3D.ZERO;
    Vec3d translate= new Vec3d(0,0,0);
    Vec3d force = new Vec3d(0,0,0);
    Vec3d velocity= new Vec3d(0,0,0);

    Sphere shape;

    Random random = new Random(System.currentTimeMillis());

    public Body(double mass, double radius, Point3D position) {
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        shape = new Sphere(radius);
        Color color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
        shape.setMaterial(new PhongMaterial(color));
        shape.getTransforms().add(new Translate(position.getX(), position.getY(), position.getZ()));
    }
    public Body(double mass,  Point3D position){
        this(mass, 1, position);
    }

    public Body(double mass, double x, double y, double z){
        this(mass, new Point3D(x, y, z));
    }


    public void reset(){
        Translate transform = (Translate) shape.getTransforms().get(0);
        shape.getTransforms().clear();;
        shape.getTransforms().add(transform);
        translate = new Vec3d(0,0,0);
        force = new Vec3d(0,0,0);
        velocity = new Vec3d(0,0,0);
        position = new Point3D(transform.getX(), transform.getY(), transform.getZ());
    }
    /**
     * calculate next position
     *
     * first, calculate force by all bodies,
     * then the force make velocity changed
     * the velocity make position changed.
     * @param time
     */
    public void nextTime(double time){
        velocity.x += (force.x / mass)*time;
        velocity.y += (force.y / mass)*time;
        velocity.z += (force.z / mass)*time;
        translate = new Vec3d(velocity.x*time, velocity.y*time, velocity.z*time);
        position = position.add(translate.x, translate.y, translate.z);
        shape.getTransforms().add(new Translate(translate.x, translate.y, translate.z));
    }

    /**
     * F = (G*m1*m2)/r^2
     * Fx = F*X/r, Fy=F*Y/r, Fz=F*Z/r
     * so: Fx = (G*m1*m2)*X/r^3
     * so: Fx = (G*m1*m2/r^3) * X
     * and so on Fy, Fz
     *
     * then: this.force = this.force+(Fx,Fy,Fz)
     * @param bodies
     */
    public void calculateForce(List<Body> bodies, double G){
        calculateG(bodies , G);
    }

    private void calculateG(List<Body> bodies, double G) {
        force= new Vec3d(0,0,0);
        for (Body body : bodies) {
            if (body.equals(this)){
                continue;
            }
            double distance = position.distance(body.position);
            if (distance<radius){
                force = new Vec3d(0,0,0);
                velocity = new Vec3d(0,0,0);
            }else{
                double forceParam = G* this.mass *body.mass/Math.pow(distance, 3);
                force.x += forceParam*(body.position.getX()-this.position.getX());
                force.y += forceParam*(body.position.getY()-this.position.getY());
                force.z += forceParam*(body.position.getZ()-this.position.getZ());
            }
        }
    }

    public void log(){
        System.out.print(position);
        System.out.print(velocity);
        System.out.println(force);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMass() {
        return mass;
    }

    public Point3D getPosition() {
        return position;
    }

    public Vec3d getTranslate() {
        return translate;
    }

    public Vec3d getForce() {
        return force;
    }

    public Vec3d getVelocity() {
        return velocity;
    }

    public Sphere getShape() {
        return shape;
    }

    public void setVelocity(Vec3d velocity) {
        this.velocity = velocity;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public void setForce(Vec3d force) {
        this.force = force;
    }

}
