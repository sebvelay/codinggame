package com.codinggame.game;

import com.codinggame.simulation.Move;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VitessePod {

    @Test
    void calculateVitesse(){
        Pod  pod = new Pod(0,0,0,0,0,0,new Checkpoint(0,1000,0));
        Move move = new Move(0,100);

        pod.apply(move,0);

        assertEquals(85, pod.vx);
        assertEquals(0, pod.vy);
        assertEquals(100,pod.x);
        assertEquals(0,pod.y);
    }

    @Test
    void calculateVitesse2(){
        Pod  pod = new Pod(0,120,10,0,0,0,new Checkpoint(0,87,1000));
        //only the angle care
        Move move = new Move(90,100);

        pod.apply(move,0);

        assertEquals(0, pod.vx);
        assertEquals(85, pod.vy);
        assertEquals(120,pod.x);
        assertEquals(110,pod.y);
    }

  /*  @Test
    void calculateVitesseBetween2Points(){
        Pod  pod = new Pod(0,100,0,new Checkpoint(1000,0));

        pod.applyLastThrust(new Point(0, 0),100);

        assertEquals(85, pod.vx);
        assertEquals(0, pod.vy);

    }*/
}
