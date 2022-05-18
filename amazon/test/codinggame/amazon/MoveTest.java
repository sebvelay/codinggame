package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MoveTest {

    @Test
    void cantMoveIfHauteurhighterThan1(){
        Grille grille = new Grille(3,"030"+
                "000"+
                "000");
        Unit unit = new Unit(0,0,grille,0,false);

        List<Node> nodes = unit.listActions();

        Assertions.assertFalse(nodes.removeIf(node -> node.caseToMove.equals(grille.getCase(1,0))));
    }


}
