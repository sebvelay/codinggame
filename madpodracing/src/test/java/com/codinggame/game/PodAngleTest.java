package com.codinggame.game;

import com.codinggame.simulation.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.acos;

class PodAngleTest {


    @Test
    void toto(){
        int j=0;
        for(double i=-1;i<=1;i=(i+0.01)){
            j++;
            System.out.println(j+" "+Math.acos(i));
        }

    }


    @Test
    void getAngle() {
        Checkpoint checkpoint = new Checkpoint(0, 100, 1000);
        Pod pod = new Pod(0, 100, 0, 0, 0, -1, checkpoint);

        double angleToNextCheckpoint = pod.getAngleToNextCheckpoint();

        Assertions.assertEquals(90, angleToNextCheckpoint);
    }

    @Test
    void getAngle2() {
        Checkpoint checkpoint = new Checkpoint(0, 100, -1000);
        Pod pod = new Pod(0, 100, 0, 0, 0, -1, checkpoint);

        double angleToNextCheckpoint = pod.getAngleToNextCheckpoint();

        Assertions.assertEquals(270, angleToNextCheckpoint);
    }

    @Test
    void getAngle3() {
        Checkpoint checkpoint = new Checkpoint(0, -100, 0);
        Pod pod = new Pod(0, 100, 0, 0, 0, 0, checkpoint);

        double angleToNextCheckpoint = pod.getAngleToNextCheckpoint();

        Assertions.assertEquals(180, angleToNextCheckpoint);
    }


    @Test
    void getAngleRotationMax() {
        Checkpoint checkpoint = new Checkpoint(0, 100, 100);
        Pod pod = new Pod(0, 100, 0, 0, 0, 0, checkpoint);
        double angleToNextCheckpoint = pod.getRotationToNextCheckpoint();
        Assertions.assertEquals(18, angleToNextCheckpoint);
    }

    @Test
    void getAngleRotation0() {
        Checkpoint checkpoint = new Checkpoint(0, 100, 100);
        Pod pod = new Pod(0, 100, 0, 0, 0, 90, checkpoint);
        double angleToNextCheckpoint = pod.getRotationToNextCheckpoint();
        Assertions.assertEquals(0, Math.abs(angleToNextCheckpoint));
    }

    @Test
    void applyMove() {
        Checkpoint checkpoint = new Checkpoint(0, 100, 1000);
        Pod pod = new Pod(0, 100, 0, 0, 0, 90, checkpoint);

        Move move = new Move(0, 100);

        pod.apply(move,0);

        Assertions.assertEquals(100, pod.x);
        Assertions.assertEquals(100, pod.y);
    }


}
