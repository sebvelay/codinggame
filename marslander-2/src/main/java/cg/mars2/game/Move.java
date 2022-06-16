package cg.mars2.game;

import java.util.concurrent.ThreadLocalRandom;

public class Move {

    public int rotate;
    public int power;


    public Move(int rotate, int power) {
        this.rotate = rotate;
        this.power = power;


    }

    public void debug(String msg) {
        System.err.println(msg + " : rotate : " + this.rotate + " power : " + this.power);
    }

    public void mutate() {
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0) {
            mutateRotate();
        } else {
            mutatePower();
        }
    }

    public void mutatePower() {

        if (power == 0) {
            power = 1;
            return;
        }
        if (power == 4) {
            power = 3;
            return;
        }
        boolean plus = ThreadLocalRandom.current().nextBoolean();
        if (plus) {
            power++;
        } else {
            power--;
        }

    }

    public void mutateRotate() {
        /*if (this.rotate < Constant.ROTATION_MIN) {
            this.rotate = (int) Constant.ROTATION_MIN;
        }
        if (this.rotate > Constant.ROTATION_MAX) {
            this.rotate = (int) Constant.ROTATION_MAX;
        }*/
        int i = ThreadLocalRandom.current().nextInt(Constant.ANGLE_MIN, Constant.ANGLE_MAX);

        this.rotate = i;

    }
}
