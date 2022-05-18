package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    void getDistanceToPoint(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,1);

        Assertions.assertEquals(1,p1.getDistance(p2));

    }

    @Test
    void getDistanceToPoint2(){
        Point p1 = new Point(4,0);
        Point p2 = new Point(1,1);

        Assertions.assertEquals(3,p1.getDistance(p2));

    }

    @Test
    void getDistanceToPoint3(){
        Point p1 = new Point(0,1);
        Point p2 = new Point(2,0);

        Assertions.assertEquals(2,p1.getDistance(p2));

    }

    @Test
    void getDistanceToPoint4(){
        Point p1 = new Point(2,0);
        Point p2 = new Point(0,1);

        Assertions.assertEquals(2,p1.getDistance(p2));

    }

}
