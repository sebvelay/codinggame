package cg.mars2.game;

public class Land {
    Point p1;
    Point p2;

    public Point target;

    public Land(Point[] points) {
        //for each points
        for (int i = 1; i < points.length; i++) {
            if (points[i].y == points[i - 1].y) {
                p1 = points[i - 1];
                p2 = points[i];
                break;
            }
        }

        target=p1;
    }
}
