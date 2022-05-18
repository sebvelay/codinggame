package codinggame.amazon;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameSimulation {

    @Test
    void shouldSimulateBuild(){
        Grille grille = new Grille(3,"000000000");
        Unit me = new Unit(0,0,grille,0,false);

        Action action = new Action("MOVE&BUILD",0,"E","W",me);

        Grille newGrille = grille.simulateAction(action);
        Case aCase = newGrille.getCase(0, 0);

        Assertions.assertEquals(1,aCase.hauteur);
    }

    @Test
    void shouldMoveUnit(){
        Grille grille = new Grille(3,"000000000");
        Unit me = new Unit(0,0,grille,0,false);
        grille.addUnit(me);

        Action action = new Action("MOVE&BUILD",0,"E","W",me);

        Grille newGrille = grille.simulateAction(action);
        Case aCase = newGrille.getCase(0, 0);

        Assertions.assertEquals(1,newGrille.units.get(0).point.x);
        Assertions.assertEquals(0,newGrille.units.get(0).point.y);
    }

    @Test
    void shouldSimulatePushAndBuild(){
        Grille grille = new Grille(3,
                "000" +
                        "030" +
                        "000");
        Unit me1 = new Unit(1, 0, grille, 0, false);
        Unit enemy1 = new Unit(1, 1, grille, 0, true);

        grille.addUnit(me1);
        grille.addUnit(enemy1);

        Action action = new Action("PUSH&BUILD", 0, "S", "S", me1);

        Grille newGrille = grille.simulateAction(action);

        Case aCase = newGrille.getCase(1, 1);

        Assertions.assertEquals(4, aCase.hauteur);
    }

    @Test
    void shouldGetAvailableMoveAfterSimulation() {
        Grille grille = new Grille(3,
                "3.0" +
                        "040" +
                        "..0");
        Unit me = new Unit(0, 0, grille, 0, false);

        Action action = new Action("MOVE&BUILD", 0, "S", "N", me);

        Grille newGrille = grille.simulateAction(action);

        List<Move> moves = newGrille.availableMoveArroundPoint(new Point(0, 1));

        Assertions.assertEquals(0,moves.size());
    }


}
