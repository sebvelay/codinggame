package cg.mars2.game;


import cg.mars2.math.Segment;

public class Map {

    static public Point[] points;
    static Land land;
    static Point sommet;


    public Map(Point[] points) {
        this.points = points;

        land = new Land(this.points);

        sommet = points[0];
        for (Point p : points) {
            if (p.y > sommet.y) {
                sommet = p;
            }
        }
    }

    public Point getTarget() {
        return land.target;
    }


    public boolean crossLand(Point departure, Point destination) {

        return Segment.intersect(departure, destination, land.p1, land.p2);
    }

    public boolean safeZone(Point departure, Point destination) {

        for (int i = 0; i < points.length - 1; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1)];
            boolean cross = Segment.intersect(departure, destination, p1, p2);
            if (cross) {
                return false;
            }
        }


        return true;
    }
}
