package cg.mars2;

import cg.mars2.game.Capsule;
import cg.mars2.game.Chrono;
import cg.mars2.game.Land;
import cg.mars2.game.Map;
import cg.mars2.game.Move;
import cg.mars2.game.Point;
import cg.mars2.game.Population;
import cg.mars2.game.Simulation;

import java.util.Scanner;

/**
 * Save the Planet.
 * Use less Fossil Fuel.
 **/
public class Player {

    public static Population[] oldPopulation = null;

    public static Map map = null;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of points used to draw the surface of Mars.
        Chrono.startChrono();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            points[i] = new Point(landX, landY);
            System.err.println("Point " + i + ": " + points[i].x + " " + points[i].y);
        }

        map = new Map(points);
        System.err.println("target : " + map.getTarget().x + " " + map.getTarget().y);

        Simulation simulation = new Simulation();

        // game loop
        while (true) {
            int X = in.nextInt();
            Chrono.startChrono();
            int Y = in.nextInt();
            int HS = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int VS = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int F = in.nextInt(); // the quantity of remaining fuel in liters.
            int R = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int P = in.nextInt(); // the thrust power (0 to 4).

            Capsule capsule = new Capsule(X, Y, HS, VS, F, R, P);
            capsule.debug("init capsule");

            simulation.generateSimulation(capsule);
            simulation.mutate();

            Move move = simulation.getBest().moves[0];

            //todo remove after
            capsule.apply(move);
            capsule.debug("next");

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // R P. R is the desired rotation angle. P is the desired thrust power.
            Chrono.stopChrono();


            System.err.println("x "+capsule.distanceX(map.getTarget()));
            System.err.println("Y "+capsule.distanceY(map.getTarget()));
            System.out.println(move.rotate + " " + move.power);
        }
    }
}