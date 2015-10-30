package gmail.renshs.bodies3.view;

import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

/**
 * Created by sren on 15-10-29.
 */
public class ViewObj {

    String name;
    int id;

    double radius;

    Shape3D shape;


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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Shape3D getShape() {
        return shape;
    }

    public void setShape(Shape3D shape) {
        this.shape = shape;
    }
}
