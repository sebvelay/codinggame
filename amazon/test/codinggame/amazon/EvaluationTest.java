package codinggame.amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class EvaluationTest {

    Grille grille;
    List<Unit> unitList;
    List<Node> nodes;

    @BeforeEach
    void setUp(){
        grille = new Grille(3,"000"+"000"+"000");
        Unit me = new Unit(0,0,grille,0,false);
        Unit enemy = new Unit(00,1,grille,0,true);
        unitList = Arrays.asList(me,enemy);
        grille.addUnit(me);
        grille.addUnit(enemy);

        nodes = me.listActions();

    }

    @Test
    void shouldFilter5BestNode(){
        List<Node> filter = Evaluation.filter(5, nodes);

        Assertions.assertEquals(5,filter.size());
    }

    @Test
    void shouldCalculateDeeper(){
        List<Node> filter = Evaluation.filter(5, nodes);

        for(Node n:filter){
            n.deeper(1);
        }


    }

}
