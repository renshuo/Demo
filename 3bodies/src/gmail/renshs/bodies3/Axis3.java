package gmail.renshs.bodies3;

import java.io.Serializable;

/**
 * Created by sren on 15-10-29.
 */
public class Axis3 implements Serializable{

    static final long serialVersionUID = 4L;

    double x=0,y=0,z=0;


    public Axis3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Axis3() {
    }

    public Axis3(Axis3 value) {
        this.x = value.x;
        this.y = value.y;
        this.z = value.z;
    }


    public Axis3 jia(Axis3 ad){
        Axis3 a = new Axis3();
        a.x = this.x+ad.x;
        a.y = this.y+ad.y;
        a.z = this.z+ad.z;
        return a;
    }

    public Axis3 jian(Axis3 jian){
        Axis3 a = new Axis3();
        a.x = this.x-jian.x;
        a.y = this.y-jian.y;
        a.z = this.z-jian.z;
        return  a;
    }

    public Axis3 cheng(double ply){
        Axis3 a = new Axis3();
        a.x = this.x * ply;
        a.y = this.y * ply;
        a.z = this.z * ply;
        return  a;
    }

    public Axis3 chu(double d){
        Axis3 a = new Axis3();
        a.x = this.x/d;
        a.y = this.y/d;
        a.z = this.z/d;
        return a;
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Axis3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
