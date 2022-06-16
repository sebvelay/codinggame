package cg.mars2.game;

import cg.mars2.Player;
import cg.mars2.math.LightNumber;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Capsule extends Point {
    public double horizontalSpeed;
    public double verticalSpeed;
    public int fuel;
    public double rotation;
    public double initialRotation;
    public int power;

    public Point target;

    public double accelerationHorizontal;
    public double accelerationVertical;

    double save_x;
    double save_y;
    double save_HS;
    double save_VS;
    int save_fuel;
    double save_rotation;
    int save_power;

    boolean dead = false;
    boolean save_dead = false;

    boolean crossLand = false;
    boolean save_crossLand = false;


    public Capsule(double x, double y, int horizontalSpeed, int verticalSpeed, int fuel, int rotation, int power) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
        this.fuel = fuel;
        this.rotation = rotation;
        this.power = power;
        this.initialRotation = rotation;
        this.target = Player.map.getTarget();
    }

    @Override
    public void debug(String name) {
        System.err.println(name + " : x: " + this.x + " y : " + this.y + " HS : " + this.horizontalSpeed + " VS: " + this.verticalSpeed
                + " fuel : " + this.fuel + " rotation : " + this.rotation + " power :" + this.power);
        System.err.println("angle with target : " + this.diffAngleWith(this.target));
    }

    public void apply(Move move) {

        Point departure = new Point(this.x, this.y);

        /**
         * const radians = R * (Math.PI / 180)
         *     const xAcc = Math.sin(radians) * P
         *     const yAcc = Math.cos(radians) * P - gravity
         *     HS -= xAcc
         *     VS += yAcc
         *     const newX = X + HS - xAcc * 0.5
         *     const newY = Y + VS + yAcc * 0.5 + gravity
         */

        this.rotation = move.rotate;
        this.fuel -= move.power;
        this.power = move.power;

        double ra = this.rotation * PI / 180.0;
        this.accelerationHorizontal = sin(ra) * move.power;
        this.accelerationVertical = cos(ra) * move.power + Constant.GRAVITE_MARS;
        this.horizontalSpeed -= this.accelerationHorizontal;
        this.verticalSpeed += this.accelerationVertical;
        this.x += this.horizontalSpeed /*- this.accelerationHorizontal * 0.5*/;
        //TODO voir si on ajoute la gravité dans l'accélération verticale ou ici
        this.y += (this.verticalSpeed + 0.5);


        this.x = LightNumber.truncate(this.x);
        this.y = LightNumber.truncate(this.y);

        Point destination = new Point(this.x, this.y);


        this.horizontalSpeed = LightNumber.truncate(this.horizontalSpeed);
        this.verticalSpeed = LightNumber.truncate(this.verticalSpeed);

        if (Player.map.crossLand(departure, destination)) {
            crossLand = true;
        }

        if (!Player.map.safeZone(departure, destination) && !crossLand) {
            dead = true;
        }


    }


    public double getRotation(Point point) {

        if (this.x > this.target.x) {
            return this.initialRotation - Constant.ROTATION_MAX;

        } else {
            return this.initialRotation - Constant.ROTATION_MAX;
        }
    }

    public double diffAngleWith(Point point) {
        double a = this.getAngle(point);

        return a - 180;

       /* // To know whether we should turn clockwise or not we look at the two ways and keep the smallest
        // The ternary operators replace the use of a modulo operator which would be slower
        double right = this.rotation <= a ? a - this.rotation : 360.0 - this.rotation + a;
        double left = this.rotation >= a ? this.rotation - a : this.rotation + 360.0 - a;

        if (right < left) {
            return right;
        } else {
            // We return a negative angle if we must rotate to left
            return -left;
        }*/
    }

    public void saveState() {
        this.save_x = this.x;
        this.save_y = this.y;
        this.save_HS = this.horizontalSpeed;
        this.save_VS = this.verticalSpeed;
        this.save_fuel = this.fuel;
        this.save_rotation = this.rotation;
        this.save_power = this.power;
        this.save_dead = this.dead;
        this.save_crossLand = this.crossLand;
    }

    public void restoreState() {
        this.x = this.save_x;
        this.y = this.save_y;
        this.horizontalSpeed = this.save_HS;
        this.verticalSpeed = this.save_VS;
        this.fuel = this.save_fuel;
        this.rotation = this.save_rotation;
        this.power = this.save_power;
        this.dead = this.save_dead;
        this.crossLand = this.save_crossLand;
    }

    public double getScore() {


        if (crossLand) {
            double scoreWon = Constant.WON;

            if (Math.abs(verticalSpeed) > 40) {
                scoreWon -= Math.abs(verticalSpeed);
            }
            if (Math.abs(horizontalSpeed) > 20) {
                scoreWon -= Math.abs(horizontalSpeed);
            }
            scoreWon -= Math.abs(rotation);

            return scoreWon;
        }

        if (dead) {
            return Constant.LOOSE;
        }

        if (fuel <= 0) {
            return Constant.LOOSE;
        }

        //double score = -distance(this.target);


        double score =
                -distance(new Point(target.x, this.y));

        if (Math.abs(verticalSpeed) > 40) {
            score -= Math.abs(verticalSpeed) * 10000;
        }
        if (Math.abs(horizontalSpeed) > 20) {
            score -= Math.abs(horizontalSpeed) * 10000;
        }

        return score;
    }
}
