package com.codinggame.game;

import com.codinggame.Player;
import com.codinggame.math.LightNumber;
import com.codinggame.math.Vector;
import com.codinggame.simulation.Constant;
import com.codinggame.simulation.Map;
import com.codinggame.simulation.Move;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;


public class Pod extends Unit {
    //to manage last lap ...
    public static int LAP_POD_0 = 1;
    public static int LAP_POD_1 = 1;
    public static int LAST_CHECKPOINT_ID_POD_0 = 1;
    public static int LAST_CHECKPOINT_ID_POD_1 = 1;
    public static boolean isLastCheckpointForPod0 = false;
    public static boolean isLastCheckpointForPod1 = false;
    //
    public boolean collisionWithPod = false;
    public int checked;
    public double angle;
    private boolean shield;
    private int timeout;
    private Checkpoint nextCheckpoint;
    private double saveX;
    private double saveY;
    private double saveAngle;
    private double saveVx;
    private double saveVy;
    private int saveChecked;
    private boolean savecollisionWithPod;
    private Checkpoint saveNextCheckpoint;
    private List<Pod> otherPods;

    public Pod(int id, double x, double y, double vx, double vy, int angle, Checkpoint nextCheckpoint) {
        super(id, x, y, vx, vy);
        this.shield = false;
        this.timeout = 100;
        this.checked = 0;
        this.angle = angle;
        this.setCheckpoint(nextCheckpoint);
        this.collisionWithPod = false;


        /**
         * dirty code, but may be efficient ...
         */
        if (id == 0) {
            if (nextCheckpoint.id == 1 && LAST_CHECKPOINT_ID_POD_0 != 1) {
                LAP_POD_0++;
            }
            LAST_CHECKPOINT_ID_POD_0 = nextCheckpoint.id;

        } else {
            if (nextCheckpoint.id == 1 && LAST_CHECKPOINT_ID_POD_1 != 1) {
                LAP_POD_1++;
            }
            LAST_CHECKPOINT_ID_POD_1 = nextCheckpoint.id;
        }

        if (LAP_POD_0 == 3 && LAST_CHECKPOINT_ID_POD_0 == 0) {
            if (Constant.DEBUG) {
                System.err.println("LAST CHECKPOINT FOR POD 0");
            }
            isLastCheckpointForPod0 = true;
        }

        if (LAP_POD_1 == 3 && LAST_CHECKPOINT_ID_POD_1 == 0) {
            if (Constant.DEBUG) {
                System.err.println("LAST CHECKPOINT FOR POD 1");
            }
            isLastCheckpointForPod1 = true;
        }

    }


    private Pod(int id, double x, double y, boolean shield, double vx, double vy, int timeout, double angle, Checkpoint nextCheckpoint, int checked) {
        super(id, x, y, vx, vy);
        this.shield = shield;
        this.timeout = timeout;
        this.checked = checked;
        this.angle = angle;
        this.collisionWithPod = false;
        this.setCheckpoint(nextCheckpoint);
    }

    public static Pod fromDebug(String str) {
        //0 8759.0 6090.0 399.0 265.0 19.0 target:3
        String[] splited = str.split(" ");
        int id = Integer.valueOf(splited[6].replace("target:", ""));
        Integer idPod = Integer.valueOf(splited[0]);
        Double x = Double.valueOf(splited[1]);
        Double y = Double.valueOf(splited[2]);
        Double vx = Double.valueOf(splited[3]);
        Double vy = Double.valueOf(splited[4]);
        Integer angle = Double.valueOf(splited[5]).intValue();
        return new Pod(idPod, x, y, vx, vy, angle, Map.getCheckpoints().get(id));
    }

    public List<Pod> getOtherPods() {
        return this.otherPods;
    }

    public void setOtherPods(List<Pod> allPods) {
        this.otherPods = new ArrayList<>();
        for (int i = 0; i < allPods.size(); i++) {
            if (allPods.get(i).id != this.id) {
                this.otherPods.add(allPods.get(i));
            }
        }
    }

    public double diffAngleWithNextCheckpoint() {
        double a = this.getAngleToNextCheckpoint();

        // To know whether we should turn clockwise or not we look at the two ways and keep the smallest
        // The ternary operators replace the use of a modulo operator which would be slower
        double right = this.angle <= a ? a - this.angle : 360.0 - this.angle + a;
        double left = this.angle >= a ? this.angle - a : this.angle + 360.0 - a;

        if (right < left) {
            return right;
        } else {
            // We return a negative angle if we must rotate to left
            return -left;
        }
    }


    public double getRotationToNextCheckpoint() {
        double a = this.diffAngleWithNextCheckpoint();

        // Can't turn by more than 18° in one turn
        if (a > 18.0) {
            a = 18.0;
        } else if (a < -18.0) {
            a = -18.0;
        }
        return a;
    }

    void rotateToNextCheckpoint() {
        double a = this.diffAngleWithNextCheckpoint();

        // Can't turn by more than 18° in one turn
        if (a > 18.0) {
            a = 18.0;
        } else if (a < -18.0) {
            a = -18.0;
        }

        this.angle += a;

        // The % operator is slow. If we can avoid it, it's better.
        if (this.angle >= 360.0) {
            this.angle = this.angle - 360.0;
        } else if (this.angle < 0.0) {
            this.angle += 360.0;
        }
    }

    public void boost(int thrust) {
        // Don't forget that a pod which has activated its shield cannot accelerate for 3 turns
        if (this.shield) {
            return;
        }

        // Conversion of the angle to radiants
        double ra = this.angle * PI / 180.0;

        // Trigonometry
        this.vx += cos(ra) * thrust;
        this.vy += sin(ra) * thrust;
    }

    public void move(float t) {
        this.x += this.vx * t;
        this.y += this.vy * t;
    }

    public void end() {
        this.x = LightNumber.truncate(this.x);
        this.y = LightNumber.truncate(this.y);

        this.vx = LightNumber.truncate(this.vx * 0.85);
        this.vy = LightNumber.truncate(this.vy * 0.85);

        // Don't forget that the timeout goes down by 1 each turn. It is reset to 100 when you pass a checkpoint
        this.timeout -= 1;
    }

    public void apply(Move move, int number) {
        this.angle = this.angle + move.angle;
        this.boost(move.thrust);
        this.move(1.0f);
        this.end();
        if (nextCheckpoint != null && this.collision(nextCheckpoint) != null) {
            this.bounce(nextCheckpoint);

            this.setCheckpoint(Map.nextCheckpoint(nextCheckpoint));
        }
        //check collision with other pods
        /*if (otherPods != null && number == 0) {
            for (int i = 0; i < otherPods.size(); i++) {
                Collision collision = this.collision(otherPods.get(i));
                if (collision != null) {
                    this.bounce(otherPods.get(i));
                }
            }
        }*/


        //on vérifie la collision avec tout les autres pods.


    }

    void bounce(Unit u) {
        if (u instanceof Checkpoint) {
            this.checked += 1;
            this.timeout = 100;
        }
        if (u instanceof Pod) {
            this.collisionWithPod = true;
            double mcoeff = 1;
            double nx = this.x - u.x;
            double ny = this.y - u.y;

            // Square of the distance between the 2 pods. This value could be hardcoded because it is always 800²
            double nxnysquare = nx * nx + ny * ny;

            double dvx = this.vx - u.vx;
            double dvy = this.vy - u.vy;

            // fx and fy are the components of the impact vector. product is just there for optimisation purposes
            double product = nx * dvx + ny * dvy;
            double fx = (nx * product) / (nxnysquare * mcoeff);
            double fy = (ny * product) / (nxnysquare * mcoeff);

            this.vx -= fx;
            this.vy -= fy;
            u.vx += fx;
            u.vy += fy;

            double impulse = Math.sqrt(fx * fx + fy * fy);
            if (impulse < 120.0) {
                fx = fx * 120.0 / impulse;
                fy = fy * 120.0 / impulse;
            }

            this.vx -= fx;
            this.vy -= fy;
            u.vx += fx;
            u.vy += fy;


        }
    }

    public void setCheckpoint(Checkpoint checkpoint) {


        this.nextCheckpoint = checkpoint;
        if (Player.turnNumber == 1 && saveAngle == 0) {
            this.angle = this.getAngleToNextCheckpoint();

            if (Constant.DEBUG) {
                System.err.println("correct the angle to next checkpoint : " + this.angle);
            }
        }
    }

    public double getScore() {
        if (this.nextCheckpoint == null) {
            return (checked * 50000);
        }
        double distanceToNextCheckpoint = this.distance(this.nextCheckpoint);


        double vitesse = Vector.speed(vx, vy);

        double rotationToNext = getRotationToNextCheckpoint();
        // return (checked * 50000) - distanceToNextCheckpoint - (Math.abs(angleToNextCheckpoint) * 50) + vitesse * 10;
        return (checked * 50000)
                - distanceToNextCheckpoint
                - (Math.abs(rotationToNext) * 50)
                + (vitesse);
    }

    public Checkpoint getNextCheckpoint() {
        return this.nextCheckpoint;
    }

    /**
     * Gold rules  : the angle of the pod is the absolute rotation, we have to recalculate the angle with the next checkpoint
     *
     * @return
     */
    public double getAngleToNextCheckpoint() {
        Checkpoint p = this.getNextCheckpoint();
        if (p == null) {
            System.err.println("next checkpoint is null");
        }
        return this.getAngle(p);
    }

    public String getActionString(Move move) {
        if (Constant.DEBUG) {
            System.err.println(move.angle + " " + move.thrust);
        }
        double a = angle + move.angle;

        if (a >= 360.0) {
            a = a - 360.0;
        } else if (a < 0.0) {
            a += 360.0;
        }

        // Look for a point corresponding to the angle we want
        // Multiply by 10000.0 to limit rounding errors
        a = a * PI / 180.0;
        double px = this.x + cos(a) * 10000.0;
        double py = this.y + sin(a) * 10000.0;

        if (move.thrust == Constant.BOOST_VALUE) {
            Constant.boostAvailable = false;
            return round(px) + " " + round(py) + " BOOST";
        }

        if (move.thrust < 0) {
            System.err.println("********");
            System.err.println("thrust impossible " + move.thrust);
            move.thrust = 0;
        }


        return round(px) + " " + round(py) + " " + move.thrust;
    }

    public void saveSate() {

        this.saveX = this.x;
        this.saveY = this.y;
        this.saveVx = this.vx;
        this.saveVy = this.vy;
        this.saveAngle = this.angle;
        this.saveNextCheckpoint = this.nextCheckpoint;
        this.saveChecked = this.checked;
        this.savecollisionWithPod = this.collisionWithPod;


        // return new Pod(this.id, this.x, this.y, this.shield, this.vx, this.vy, this.timeout, this.angle, this.nextCheckpoint, this.checked);
    }

    public void restore() {
        this.x = this.saveX;
        this.y = this.saveY;
        this.vx = this.saveVx;
        this.vy = this.saveVy;
        this.angle = this.saveAngle;
        this.nextCheckpoint = this.saveNextCheckpoint;
        this.checked = this.saveChecked;
        this.collisionWithPod = this.savecollisionWithPod;
    }

    public void debug() {
        if (Constant.DEBUG) {
            System.err.println("pod :" + this.id + " " + this.x + " " + this.y + " " + this.vx + " " + this.vy + " " + this.angle + " target:" + this.nextCheckpoint.id);
        }
    }
}
