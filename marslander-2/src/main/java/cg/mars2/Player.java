package cg.mars2;

import cg.mars2.game.Capsule;
import cg.mars2.game.Land;
import cg.mars2.game.Point;

import java.util.Scanner;

/**
 * Save the Planet.
 * Use less Fossil Fuel.
 **/
public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of points used to draw the surface of Mars.
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            points[i] = new Point(landX, landY);
            System.err.println("Point " + i + ": " + points[i].x + " " + points[i].y);
        }

        Land land = new Land(points);
        System.err.println("target : " + land.target.x + " " + land.target.y);

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int HS = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int VS = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int F = in.nextInt(); // the quantity of remaining fuel in liters.
            int R = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int P = in.nextInt(); // the thrust power (0 to 4).

            Capsule capsule = new Capsule(X, Y, HS, VS, F, R, P);
            System.err.println("capsule : " + capsule.x + " " + capsule.y + " " + capsule.HS + " " + capsule.VS + " " + capsule.fuel + " " + capsule.rotation + " " + capsule.power);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // R P. R is the desired rotation angle. P is the desired thrust power.
            System.out.println("-20 3");
        }
    }
}