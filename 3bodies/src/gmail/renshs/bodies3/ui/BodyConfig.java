package gmail.renshs.bodies3.ui;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.*;
import javafx.geometry.Point3D;

/**
 * Created by sren on 15-10-22.
 */
public class BodyConfig {

    private final StringProperty name;
    private final DoubleProperty mass;
    private final DoubleProperty radius;
    private final ObjectProperty<Point3D> position;
    private final ObjectProperty<Vec3d> velocity;

    public BodyConfig() {
        this.name = new SimpleStringProperty("obj");
        this.mass = new SimpleDoubleProperty(1);
        this.radius = new SimpleDoubleProperty(1);
        this.position = new SimpleObjectProperty<>(new Point3D(0,0,0));
        this.velocity = new SimpleObjectProperty<>(new Vec3d(0,0,0));
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getMass() {
        return mass.get();
    }

    public DoubleProperty massProperty() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass.set(mass);
    }

    public double getRadius() {
        return radius.get();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius.set(radius);
    }

    public Point3D getPosition() {
        return position.get();
    }

    public ObjectProperty<Point3D> positionProperty() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position.set(position);
    }

    public Vec3d getVelocity() {
        return velocity.get();
    }

    public ObjectProperty<Vec3d> velocityProperty() {
        return velocity;
    }

    public void setVelocity(Vec3d velocity) {
        this.velocity.set(velocity);
    }
}
