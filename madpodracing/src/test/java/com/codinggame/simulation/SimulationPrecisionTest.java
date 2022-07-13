package com.codinggame.simulation;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationPrecisionTest {

    @Test
    void test1() {


        Map.addCheckpoints(new Checkpoint(0, 12462, 1324));
        Map.addCheckpoints(new Checkpoint(1, 10553, 5957));
        Map.addCheckpoints(new Checkpoint(2, 3597, 5172));
        Map.addCheckpoints(new Checkpoint(3, 13551, 7609));

        Pod pod = Pod.fromDebug("0 8759.0 6090.0 399.0 265.0 19.0 target:3");
        Pod pod1 = Pod.fromDebug("1 7584.0 5182.0 424.0 118.0 22.0 target:3");
        Move move = new Move(-1.5982685089111328, 100);
        Move move1 = new Move(3.701705953884442,100);

        pod.apply(move,0);
        pod1.apply(move1,0);

        assertEquals(9253.0,pod.x);
        assertEquals(6384.0,pod.y);

        assertEquals(8098.0,pod1.x);
        assertEquals(5343.0,pod1.y);
    }

}
