package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StuckTest {

    @Test
    void shouldNotStuckMyself(){
        Grille grille = new Grille(3,
                "330"+
                "..0"+
                "000");

        Unit me = new Unit(0,2,grille,0,false);

        List<Node> nodes = me.listActions();
        Node best = Simulation.getBest(nodes);

        Assertions.assertNotEquals("MOVE&BUILD 0 E W",best.action.toString());
    }


}
