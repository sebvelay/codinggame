package cg.mars2.math;

import cg.mars2.game.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SegmentTest {

    @Test
    void notCross() {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 100);

        Point q1 = new Point(2, 2);
        Point q2 = new Point(1, 1);


        boolean cross = Segment.intersect(p1, p2, q1, q2);

        assertFalse(cross);
    }

    @Test
    void cross() {

        Point p1 = new Point(100, 100);
        Point p2 = new Point(200, 100);

        Point q1 = new Point(150, 150);
        Point q2 = new Point(150, 0);


        boolean cross = Segment.intersect(p1, p2, q1, q2);

        assertTrue(cross);
    }

}