package cg.mars2.game;

public class Land {
    public Point target;
    Point p1;
    Point p2;

    public Land(Point[] points) {
        //for each points
        for (int i = 1; i < points.length; i++) {
            if (points[i].y == points[i - 1].y) {
                p1 = points[i - 1];
                p2 = points[i];
                break;
            }
        }

        target = new Point(p1.x + ((p2.x - p1.x) / 2), p1.y);
    }
}
