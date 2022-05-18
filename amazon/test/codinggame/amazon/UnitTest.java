package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class UnitTest {

    @Test
    void shouldReturnDirectionAvailable(){

        Grille grille = new Grille(6,"0000000.00.0000000000000000000000000");
        Unit me = new Unit(2,3,grille,0,false);

        List<String> strings = grille.availableMoveArroundPoint(me.point).stream().map(move -> move.direction).collect(Collectors.toList());

        List<String> expected = Arrays.asList("N","E","S","W","SW","SE","NE","NW");

        Assertions.assertTrue(strings.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(strings));
    }

    @Test
    void shouldReturnDirectionAvailableWhenTopLeft(){

        Grille grille = new Grille(6,"000000000000000000000000000000000000");
        Unit me = new Unit(0,0,grille,0,false);

        List<String> strings = grille.availableMoveArroundPoint(me.point).stream().map(move -> move.direction).collect(Collectors.toList());

        List<String> expected = Arrays.asList("E","S","SE");

        Assertions.assertTrue(strings.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(strings));
    }

    @Test
    void shouldReturnDirectionAvailableWhenNotPossible(){

        Grille grille = new Grille(6,"0.0000000000000000000000000000000000");
        Unit me = new Unit(0,0,grille,0,false);

        List<String> strings = grille.availableMoveArroundPoint(me.point).stream().map(move -> move.direction).collect(Collectors.toList());

        List<String> expected = Arrays.asList("S","SE");

        Assertions.assertTrue(strings.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(strings));
    }

    @Test
    void shouldReturnDirectionAvailableWhenBuildIsOver(){

        Grille grille = new Grille(6,"040000000000000000000000000000000000");
        Unit me = new Unit(0,0,grille,0,false);

        List<String> strings = grille.availableMoveArroundPoint(me.point).stream().map(move -> move.direction).collect(Collectors.toList());

        List<String> expected = Arrays.asList("S","SE");

        Assertions.assertTrue(strings.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(strings));
    }

    @Test
    void shouldMove(){
        Grille grille = new Grille(6,"040000000000000000000000000000000000");
        Unit me = new Unit(0,0,grille,0,false);
        Point afterMove = me.move("S");

        Assertions.assertEquals(1,afterMove.y);
        Assertions.assertEquals(0,afterMove.x);


    }

    @Test
    void shouldMove2(){
        Grille grille = new Grille(6,"040000000000000000000000000000000000");
        Unit me = new Unit(0,0,grille,0,false);
        Point afterMove = me.move("SE");

        Assertions.assertEquals(1,afterMove.y);
        Assertions.assertEquals(1,afterMove.x);
    }

    @Test
    void shouldReturnAction(){
        Grille grille = new Grille(7,"...0.....000...00000.0000000.00000...000.....0...");
        Unit me = new Unit(4,3,grille,0,false);

        List<String> actions = me.listActions().stream().map(n->n.action).map(Action::toString).collect(Collectors.toList());

        List<String> expected = Arrays.asList(
        "MOVE&BUILD 0 E E",
        "MOVE&BUILD 0 E N",
        "MOVE&BUILD 0 E NW",
        "MOVE&BUILD 0 E S",
        "MOVE&BUILD 0 E SW",
        "MOVE&BUILD 0 E W",
        "MOVE&BUILD 0 N E",
        "MOVE&BUILD 0 N N",
        "MOVE&BUILD 0 N NW",
        "MOVE&BUILD 0 N S",
        "MOVE&BUILD 0 N SE",
        "MOVE&BUILD 0 N SW",
        "MOVE&BUILD 0 N W",
        "MOVE&BUILD 0 NE NW",
        "MOVE&BUILD 0 NE S",
        "MOVE&BUILD 0 NE SE",
        "MOVE&BUILD 0 NE SW",
        "MOVE&BUILD 0 NE W",
        "MOVE&BUILD 0 NW E",
        "MOVE&BUILD 0 NW N",
        "MOVE&BUILD 0 NW NE",
        "MOVE&BUILD 0 NW NW",
        "MOVE&BUILD 0 NW S",
        "MOVE&BUILD 0 NW SE",
        "MOVE&BUILD 0 NW SW",
        "MOVE&BUILD 0 NW W",
        "MOVE&BUILD 0 S E",
        "MOVE&BUILD 0 S N",
        "MOVE&BUILD 0 S NE",
        "MOVE&BUILD 0 S NW",
        "MOVE&BUILD 0 S S",
        "MOVE&BUILD 0 S SW",
        "MOVE&BUILD 0 S W",
        "MOVE&BUILD 0 SE N",
        "MOVE&BUILD 0 SE NE",
        "MOVE&BUILD 0 SE NW",
        "MOVE&BUILD 0 SE SW",
        "MOVE&BUILD 0 SE W",
        "MOVE&BUILD 0 SW E",
        "MOVE&BUILD 0 SW N",
        "MOVE&BUILD 0 SW NE",
        "MOVE&BUILD 0 SW NW",
        "MOVE&BUILD 0 SW S",
        "MOVE&BUILD 0 SW SE",
        "MOVE&BUILD 0 SW SW",
        "MOVE&BUILD 0 SW W",
        "MOVE&BUILD 0 W E",
        "MOVE&BUILD 0 W N",
        "MOVE&BUILD 0 W NE",
        "MOVE&BUILD 0 W NW",
        "MOVE&BUILD 0 W S",
        "MOVE&BUILD 0 W SE",
        "MOVE&BUILD 0 W SW",
        "MOVE&BUILD 0 W W");


        Assertions.assertTrue(actions.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(actions));

    }

    @Test
    void shouldCountAvailableAction(){

            Grille grille = new Grille(3,"000000000");
            Unit me = new Unit(0,0,grille,0,false);



            //Assertions.assertEquals(1,aCase.hauteur);

    }

}