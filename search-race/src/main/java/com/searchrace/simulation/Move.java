package com.searchrace.simulation;

import java.util.concurrent.ThreadLocalRandom;


public class Move {
    public double angle;
    public int thrust;

    public Move(double angle, int thrust) {
        this.angle = angle;
        this.thrust = thrust;

        if (this.thrust < 0) {
            throw new IllegalStateException("thrust must be positive");
        }
    }

    public Move clone() {
        return new Move(angle, thrust);
    }

    public void mutate(double amplitude) {
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0) {
            mutateAngle(amplitude);
        } else {
            mutateThurst(amplitude);
        }
    }

    protected void mutateThurst(double amplitude) {

        int pmin = 0;
        int pmax = Constant.MAX_THRUST / Constant.PAS_THRUST;

        int random = ThreadLocalRandom.current().nextInt(pmin, pmax + 1);
        thrust = random * Constant.PAS_THRUST;

    }

    protected void mutateAngle(double amplitude) {
        int ramin = Constant.ANGLE_MIN / Constant.PAS_ANGLE;
        int ramax = Constant.ANGLE_MAX / Constant.PAS_ANGLE;
        //random between ramin and ramax
        //angle = ramin + (ramax - ramin) * new Random().nextDouble();
        int rand = ThreadLocalRandom.current().nextInt(ramin, ramax);
        angle = rand * Constant.PAS_ANGLE;

    }
}
