package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BugTest {

    @Test
    void shouldNotNPE(){
        Grille grille = new Grille(7,"...4.....344...44410.3444000.34440...224.....2...");

        Unit me1 = new Unit(3,6,grille,0,false);
        Unit me2 = new Unit(0,3,grille,1,false);
        Unit enemy1 = new Unit(2,1,grille,0,true);
        Unit enemy2 = new Unit(2,5,grille,1,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);
        grille.addUnit(enemy2);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertNotEquals(best.action.toString(),"PUSH&BUILD 0 NE NE");

    }

    @Test
    void shouldNotNPE2(){
        Grille grille = new Grille(7,"...3.....144...44434.4444440.44244...343.....1...");

        Unit me1 = new Unit(3,4,grille,0,false);
        Unit me2 = new Unit(4,2,grille,1,false);
        Unit enemy1 = new Unit(3,6,grille,0,true);
        Unit enemy2 = new Unit(2,5,grille,1,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);
        grille.addUnit(enemy2);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertEquals(best.action.toString(),"MOVE&BUILD 0 SE NW"); //the only move on the position

    }

    @Test
    void shouldMoveOnHauteur3(){
        Grille grille = new Grille(7,"...3.....321...20000.0000230.00033...000.....0...");
        Unit me1 = new Unit(4,1,grille,0,false);
        Unit me2 = new Unit(3,0,grille,1,false);
        Unit enemy1 = new Unit(-1,-1,grille,0,true);
        Unit enemy2 = new Unit(5,2,grille,1,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);
        grille.addUnit(enemy2);

        Node best = Simulation.play(Arrays.asList(me1, me2,enemy1,enemy2));

        Assertions.assertTrue(best.action.toString().contains("MOVE&BUILD 1 SW"));

    }

    @Test
    void shouldReturnPush(){
        Grille grille = new Grille(6,"343200102200.0210.000000001000000000");
        //PUSH&BUILD 0 S S
        //PUSH&BUILD 0 S SE
        //PUSH&BUILD 0 S SW
        //PUSH&BUILD 1 SE S
        //PUSH&BUILD 1 SE SE
        Unit me1 = new Unit(1,1,grille,0,false);
        Unit me2 = new Unit(0,1,grille,1,false);
        Unit enemy = new Unit(1,2,grille,0,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy);

        Node best = Simulation.play(Arrays.asList(me1, me2,enemy));


    }


}
