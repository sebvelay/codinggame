package com.searchrace.game;


import com.searchrace.simulation.Constant;

public class Unit extends Point {

    public int id;
    public double r; // 400 or 600 ?
    public double vx;
    public double vy;

    public Unit(int id, double x, double y, double vx, double vy) {
        super(x, y);
        this.id = id;
        this.vx = vx;
        this.vy = vy;
    }

    public Collision collision(Unit u) {
        // Square of the distance
        double dist = this.distance(u);

        if (u instanceof Checkpoint) {
            //default : 360000
            if (dist < Constant.COLISION_CHECKPOINT_DISTANCE2) {
                // Objects are already touching each other. We have an immediate collision.
                return new Collision(this, u, 0.0);
            }
        }
        /*if (u instanceof Pod) {
            if (dist <= Constant.COLISION_POD_DISTANCE) {
                return new Collision(this, u, 0.0);
            }
        }*/
        return null;

    }

}
