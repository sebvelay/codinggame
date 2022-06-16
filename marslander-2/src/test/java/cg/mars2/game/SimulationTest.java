package cg.mars2.game;

import cg.mars2.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulationTest {

    @BeforeEach
    void init() {
        Point p1 = new Point(0, 100);
        Point p2 = new Point(1000, 500);
        Point p3 = new Point(1500, 1500);
        Point p4 = new Point(3000, 1000);
        Point p5 = new Point(4000, 150);
        Point p6 = new Point(5500, 150);
        Point p7 = new Point(6999, 800);

        Player.map = new Map(new Point[]{p1, p2, p3, p4, p5, p6, p7});

    }

    @Test
    public void testReUsePopulation() {
        Simulation simulation = new Simulation();
        Capsule capsule = new Capsule(2700, 1000, 0, 0, 550, 0, 0);
        Point target = new Point(4750, 0);
        capsule.target = target;


        simulation.generateSimulation(capsule);

        simulation.getBest();
        simulation.generateSimulation(capsule);
    }

}