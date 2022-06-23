package cg.mars2.game;

import static java.lang.Math.PI;
import static java.lang.Math.acos;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void debug(String name) {
        System.err.println(name + " : x: " + this.x + " y : " + this.y);
    }

    public double distance2(Point p) {
        return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
    }

    public double distance(Point p) {
        return Math.sqrt(this.distance2(p));
    }

    public double distanceY(Point p) {
        return Math.abs(this.y) - Math.abs(p.y);
    }

    public double getAngle(Point p2) {

        double d = this.distance(p2);

        double dx = (p2.x - x) / d;
        double dy = (p2.y - y) / d;
        double angle = acos(dy) * 180.0 / PI;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public Point closest(Point a, Point b) {
        double da = b.y - a.y;
        double db = a.x - b.x;
        double c1 = da * a.x + db * a.y;
        double c2 = -db * this.x + da * this.y;
        double det = da * da + db * db;
        double cx = 0;
        double cy = 0;

        if (det != 0) {
            cx = (da * c1 - db * c2) / det;
            cy = (da * c2 + db * c1) / det;
        } else {
            // The point is already on the line
            cx = this.x;
            cy = this.y;
        }

        return new Point(cx, cy);
    }


}
