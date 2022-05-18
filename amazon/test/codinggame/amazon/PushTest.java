package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PushTest {


    @Test
    void cantPushIfCaseNotAvailable(){
        Grille grille = new Grille(7,"...0.....000...00410.1230000.10000...000.....0...");
        Unit me1 = new Unit(1,4,grille,0,false);
        Unit me2 = new Unit(0,3,grille,1,false);
        Unit enemy1 = new Unit(2,3,grille,0,true);
        Unit enemy2 = new Unit(1,2,grille,1,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);
        grille.addUnit(enemy2);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertNotEquals(best.action.toString(),"PUSH&BUILD 0 NE NE");
    }

    @Test
    void cantPushIfCaseIsOccuped(){
        Grille grille = new Grille(3,"000300000");
        Unit me1 = new Unit(0,0,grille,0,false);
        Unit me2 = new Unit(0,2,grille,1,false);
        Unit enemy1 = new Unit(0,1,grille,0,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertNotEquals(best.action.toString(),"PUSH&BUILD 1 S S");

    }

    @Test
    void cantPushIfCaseIsHauteur4(){
        Grille grille = new Grille(3,"000300400");
        Unit me1 = new Unit(0,0,grille,0,false);
        Unit me2 = new Unit(0,2,grille,1,false);
        Unit enemy1 = new Unit(0,1,grille,0,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertNotEquals(best.action.toString(),"PUSH&BUILD 1 S S");

    }

    @Test
    void cantPushIfCaseIsOccuped2(){
        Grille grille = new Grille(3,"000300400");
        Unit me1 = new Unit(0,0,grille,0,false);
        Unit me2 = new Unit(0,2,grille,1,false);
        Unit enemy1 = new Unit(0,1,grille,0,true);

        grille.addUnit(me1);
        grille.addUnit(me2);
        grille.addUnit(enemy1);

        Node best = Simulation.play(Arrays.asList(me1, me2));

        Assertions.assertNotEquals(best.action.toString(),"PUSH&BUILD 1 N N");

    }

    @Test
    void shouldPushIfCaseIsNotOccuped() {
        Grille grille = new Grille(3, "000300000");
        Unit me1 = new Unit(0, 0, grille, 0, false);
        Unit enemy1 = new Unit(0, 1, grille, 0, true);

        grille.addUnit(me1);
        grille.addUnit(enemy1);

        Node best = Simulation.play(Arrays.asList(me1));

        Assertions.assertEquals(best.action.toString(), "PUSH&BUILD 0 S S");
    }

    @Test
    void canPushOn3Direction() {
        Grille grille = new Grille(3,
                   "000" +
                        "030" +
                        "000");
        Unit me1 = new Unit(1, 0, grille, 0, false);
        Unit enemy1 = new Unit(1, 1, grille, 0, true);

        grille.addUnit(me1);
        grille.addUnit(enemy1);

        List<Node> allNodesForPlayer = Simulation.getAllNodesForPlayer(Arrays.asList(me1, enemy1));
        List<Node> pushNode = allNodesForPlayer.stream().filter(n -> n.action.type.equals("PUSH&BUILD")).collect(Collectors.toList());

        Assertions.assertEquals(3,pushNode.size());
    }

}
