package gmail.renshs.bodies3;

import javafx.geometry.Pos;

import java.io.Serializable;

/**
 * Created by sren on 15-10-29.
 */
public class Position extends Axis3 implements Serializable{

    static final long serialVersionUID = 3L;

    public Position(){
        super();
    }

    public Position(double x, double y, double z) {
        super(x, y, z);
    }

    public Position(Axis3 position) {
        super(position);
    }

    public double distance(Position p){
        return distance(p.getX(), p.getY(), p.getZ());
    }

    public double distance(double x, double y, double z){
        double a = getX() - x;
        double b = getY() - y;
        double c = getZ() - z;
        return Math.sqrt(a * a + b * b + c * c);
    }


}
