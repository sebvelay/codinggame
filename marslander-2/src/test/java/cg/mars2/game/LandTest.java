package cg.mars2.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LandTest {

    @Test
    void shouldDetermineTargetMap2() {
        /**
         * Point 0: 0.0 100.0
         * Point 1: 1000.0 500.0
         * Point 2: 1500.0 100.0
         * Point 3: 3000.0 100.0
         * Point 4: 3500.0 500.0
         * Point 5: 3700.0 200.0
         * Point 6: 5000.0 1500.0
         * Point 7: 5800.0 300.0
         * Point 8: 6000.0 1000.0
         * Point 9: 6999.0 2000.0
         */

        Point point = new Point(0, 100);
        Point point1 = new Point(1000, 500);
        Point point2 = new Point(1500, 100);
        Point point3 = new Point(3000, 100);
        Point point4 = new Point(3500, 500);

        Land land = new Land(new Point[]{point, point1, point2, point3, point4});

        Point target = land.target;

        Assertions.assertEquals(2250,target.x);
        Assertions.assertEquals(100,target.y);


    }
}
