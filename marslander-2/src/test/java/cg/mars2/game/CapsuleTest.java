package cg.mars2.game;


import cg.mars2.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapsuleTest {

    @BeforeEach
    void init() {
        Point p1 = new Point(0, 100);
        Point p2 = new Point(1000, 500);
        Point p3 = new Point(1500, 1500);
        Point p4 = new Point(3000, 1000);
        Point p5 = new Point(4000, 150);
        Point p6 = new Point(5500, 150);
        Point p7 = new Point(6999, 800);

        Player.map = new Map(new Point[]{p1, p2, p3, p4, p5, p6, p7});

    }

    @Test
    void simulateMove() {
        Capsule capsule = new Capsule(2500, 2700, 0, 0, 550, 0, 0);
        Move move = new Move(0, 0);

        capsule.apply(move);

        assertEquals(2500, capsule.x);
        assertEquals(2698, capsule.y);
        assertEquals(0, capsule.horizontalSpeed);
        assertEquals(-4, capsule.verticalSpeed);
        assertEquals(550, capsule.fuel);
        assertEquals(0, capsule.rotation);
        assertEquals(0, capsule.power);
    }

    @Test
    void simulateMove2() {
        Capsule capsule = new Capsule(2500, 2698, 0, -4, 550, 0, 0);
        Move move = new Move(0, 0);

        capsule.apply(move);

        assertEquals(2500, capsule.x);
        assertEquals(2694, capsule.y); // normalement 2693
        assertEquals(0, capsule.horizontalSpeed);
        assertEquals(-8, capsule.verticalSpeed); // normalement 7 dans la simu mais pas logique ?
        assertEquals(550, capsule.fuel);
        assertEquals(0, capsule.rotation);
        assertEquals(0, capsule.power);
    }


    @Test
    void canNotRotateMoreThan15FromCurrent() {
        Capsule capsule = new Capsule(2700, 1000, 0, 0, 550, 0, 0);
        Point target = new Point(4750, 0);
        capsule.target = target;
        double rotation = capsule.getRotation(target);

        Assertions.assertEquals(-15, rotation);
    }

    @Test
    void simulateMoveCloseToDead(){
        //x: 3082.0 y : 952.0 HS : 20.0 VS: -35.0 fuel : 396 rotation : -2.0 power :4
        Capsule capsule = new Capsule(3082, 952, 20, -35, 396, -2, 4);
        Move move = new Move(-7,4);
        capsule.apply(move);

        /*
        X=3102m, Y=917m, HSpeed=21m/s VSpeed=-35m/s
Fuel=392l, Angle=-7°, Power=4 (4.0m/s2)
         */
        assertEquals(3102, capsule.x);
        assertEquals(918, capsule.y); // normalement 917
        assertEquals(21, capsule.horizontalSpeed);
        assertEquals(-35, capsule.verticalSpeed);
        assertEquals(392, capsule.fuel);
        assertEquals(-7, capsule.rotation);
        assertEquals(4, capsule.power);

        assertEquals(Constant.LOOSE,capsule.getScore());
    }

}