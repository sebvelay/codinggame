package com.codinggame.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.PI;
import static java.lang.Math.acos;

public class AngleTest {


    @RepeatedTest(100)
    void test(){
        int startX = ThreadLocalRandom.current().nextInt(-1000, 1000);
        int startY = ThreadLocalRandom.current().nextInt(-1000, 1000);
        int endX = ThreadLocalRandom.current().nextInt(-1000, 1000);
        int endY = ThreadLocalRandom.current().nextInt(-1000, 1000);
        Point start = new Point(startX, startY);
        Point target = new Point(endX, endY);


        //method 1
        double d = start.distance(target);
        double dx = (target.x - start.x) / d;
        double dy = (target.y - start.y) / d;

        // Simple trigonometry. We multiply by 180.0 / PI to convert radiants to degrees.
        double a = acos(dx) * 180.0 / PI;

        // If the point I want is below me, I have to shift the angle for it to be correct
        if (dy < 0) {
            a = 360.0 - a;
        }


        //method 2
        float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

        if(angle < 0){
            angle += 360;
        }


        Assertions.assertEquals(Math.round(a*100.0)/100, Math.round(angle*100.0)/100);

    }
}
