package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GrilleTest {

    Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille(6, "23..44044444.3343..3..4.000000000000");

    }

    @Test
    void shouldCreateGrille() {

        Assertions.assertTrue(grille.cases.get(0).point.x == 0);
        Assertions.assertTrue(grille.cases.get(0).point.y == 0);
        Assertions.assertTrue(grille.cases.get(0).hauteur == 2);

        Assertions.assertTrue(grille.cases.get(3).hauteur == -1);

        Assertions.assertTrue(grille.cases.get(5).point.x == 5);
        Assertions.assertTrue(grille.cases.get(5).point.y == 0);
        Assertions.assertTrue(grille.cases.get(5).hauteur == 4);

        Assertions.assertTrue(grille.cases.get(6).point.x == 0);
        Assertions.assertTrue(grille.cases.get(6).point.y == 1);

        Assertions.assertTrue(grille.cases.get(35).point.x == 5);
        Assertions.assertTrue(grille.cases.get(35).point.y == 5);
        Assertions.assertTrue(grille.cases.get(35).hauteur == 0);
    }


    @Test
    void shouldReturnCaseArround() {
        List<Case> casesArround = grille.getCasesArround(3, 3);

        Assertions.assertEquals(8, casesArround.size());

        Assertions.assertFalse(casesArround.removeIf(c -> c.point.x == 3 && c.point.y == 3));
    }

    @Test
    void shouldReturnCase() {
        grille = new Grille(7, "...0.....000...00001.0001101.00000...000.....0...");
        Case aCase = grille.getCase(5, 2);

        Assertions.assertEquals(1,aCase.hauteur);

    }

    @Test
    void shouldReturnCaseAfterDirection(){
        Grille grille = new Grille(7,"...0.....000...00410.1230000.10000...000.....0...");
        Case depart = grille.getCase(2,3);

        Case ne = grille.getCaseAfterMoveDir(depart,"NE");

        Assertions.assertTrue(ne.point.x==3 && ne.point.y==2);
    }

    @Test
    void shouldCountAvailableMove(){
        Grille grille = new Grille(3,
                "3.0" +
                        "040" +
                        "..0");
        Unit me = new Unit(0, 0, grille, 0, false);
        Unit ennemy = new Unit(3,0,grille,0,true);
        grille.addUnit(me);
        grille.addUnit(ennemy);

        Action action = new Action("MOVE&BUILD", 0, "S", "N", me);

        Grille newGrille = grille.simulateAction(action);

        Assertions.assertEquals(0,newGrille.countAvailableMoveForUnit(false));
        Assertions.assertEquals(2,newGrille.countAvailableMoveForUnit(true));
    }

}