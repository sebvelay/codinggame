package com.codinggame.simulation;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    void bug1NullPointer() {

        Map.addCheckpoints(new Checkpoint(0, 9070, 1841));
        Map.addCheckpoints(new Checkpoint(1, 4995, 5245));
        Map.addCheckpoints(new Checkpoint(2, 11451, 6053));

        //0 7438.0 3834.0 -314 268 148.0
        Pod podBefore = new Pod(0, 7438, 3834, -314, 268, 148, Map.getCheckpoints().get(1));
        Pod pod = new Pod(0, 7037, 4152, -340, 270, 150, Map.getCheckpoints().get(1));
        Pod pod2 = new Pod(0, 7037, 4152, -340, 270, 150, Map.getCheckpoints().get(1));

        Turn turn = new Turn();
        Turn.startChrono();
        turn.bestMove(podBefore, pod);
        Turn.startChrono();
        turn.bestMove(pod, pod2);

    }

}
