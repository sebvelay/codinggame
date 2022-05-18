package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SimulationTest {

    @Test
    void bestMoveIsTakePoint() {
        Grille grille = new Grille(3, "230" +
                "200" +
                "000");
        Unit unit = new Unit(0, 0, grille,0,false);

        Node play = Simulation.play(Arrays.asList(unit));

        Assertions.assertTrue(play.action.toString().contains("MOVE&BUILD 0 E"));
    }

    @Test
    void bestMoveIsTakePointAndBuildOnHauteur2(){
        Grille grille = new Grille(3, "230" +
                "200" +
                "000");
        Unit unit = new Unit(0, 0, grille,0,false);

        Node play = Simulation.play(Arrays.asList(unit));

        Assertions.assertTrue(play.action.toString().contains("MOVE&BUILD 0 E W"));
    }

    @Test
    void bestMoveIsMoveOnHauteur1(){
        Grille grille = new Grille(3, "000" +
                "100" +
                "000");
        Unit unit = new Unit(0, 0, grille,0,false);

        Node play = Simulation.play(Arrays.asList(unit));

        Assertions.assertTrue(play.action.toString().contains("MOVE&BUILD 0 S"));
    }

    @Test
    void bestMoveIsBuildToClimb(){
        Grille grille = new Grille(3, "333" +
                "100" +
                "333");
        Unit unit = new Unit(0, 1, grille,0,false);

        Node play = Simulation.play(Arrays.asList(unit));

        Assertions.assertTrue(play.action.toString().contains("MOVE&BUILD 0 E E"));
    }

    @Test
    void cantMoveToAnOccupedCase() {
        Grille grille = new Grille(3, "123" +
                "000" +
                "000");
        grille.addUnit(new Unit(1, 0, grille,0,false));
        Unit unit = new Unit(0, 0, grille,0,false);

        List<Node> nodes = unit.listActions();
        Node best = Simulation.getBest(nodes);

        Assertions.assertFalse(best.caseToMove.point.x == 1 && best.caseToMove.point.y == 0);

    }

    @Test
    void cantBuildToAnOccupedCase() {
        Grille grille = new Grille(7, "...3.....401...00011.0011111.00000...000.....0...");
        grille.addUnit(new Unit(4, 1, grille,0,false));
        Unit unit = new Unit(3, 0, grille,0,false);

        List<Node> nodes = unit.listActions();
        Node best = Simulation.getBest(nodes);

        Assertions.assertFalse(best.caseToBuild.point.x == 4 && best.caseToBuild.point.y == 1);
    }

    @Test
    void shouldClimb(){
        Grille grille = new Grille(2,"210.");
        Unit me = new Unit(1,0,grille,0,false);

        List<Node> nodes = me.listActions();

        //on récupère la node qui
        int scoreMoveWbuildE=0;
        int scoreMoveWbuildS=0;
        for(Node n : nodes){
            if (n.action.toString().equals("MOVE&BUILD 0 W E")) {
                scoreMoveWbuildE = n.score;
            }
            if (n.action.toString().equals("MOVE&BUILD 0 W S")) {
                scoreMoveWbuildS = n.score;
            }
        }
        Assertions.assertTrue(scoreMoveWbuildE > scoreMoveWbuildS);

    }

    @Test
    void shouldStayAwayFromEnnemy() {
        Grille grille = new Grille(3,
                "000"
                        + "000"
                        + "000");

        Unit me = new Unit(0,0,grille,0,false);
        Unit ennemy = new Unit(2,0,grille,0,true);
        grille.addUnit(me);
        grille.addUnit(ennemy);

        List<Node> nodes = me.listActions();
        int scoreMoveEst=0;
        int scoreMoveSouth=0;
        for(Node n : nodes){
            if(n.action.toString().equals("MOVE&BUILD 0 SE N")){
                scoreMoveEst=n.score;
            }
            else if(n.action.toString().equals("MOVE&BUILD 0 S N")){
                scoreMoveSouth=n.score;
            }

        }

        Assertions.assertTrue(scoreMoveEst<scoreMoveSouth);
    }

    @Test
    void shouldPushOnFloor(){
        Grille grille = new Grille(3,
                "000"
                        + "021"
                        + "003");

        Unit me = new Unit(0,1,grille,0,false);
        Unit ennemy = new Unit(1,1,grille,0,true);
        grille.addUnit(me);
        grille.addUnit(ennemy);

        List<Node> nodes = me.listActions();
        Node best = Simulation.getBest(nodes);

        Assertions.assertEquals("PUSH&BUILD 0 E NE",best.action.toString());

    }

    @Test
    void shouldPreferBuildOn3ifIAmOn3(){
        Grille grille = new Grille(3,
                "000"
                        + "021"
                        + "003");

        Unit me = new Unit(1,1,grille,0,false);

        List<Node> nodes = me.listActions();
        Node best = Simulation.getBest(nodes);

        Assertions.assertEquals("MOVE&BUILD 0 SE NW",best.action.toString());

    }


}
