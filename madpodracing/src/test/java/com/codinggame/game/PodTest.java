package com.codinggame.game;

import com.codinggame.simulation.Map;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PodTest {

    @Test
    void should_set_other_pod() {
        Map.addCheckpoints(new Checkpoint(0, 0, 0));
        Pod pod1 = Pod.fromDebug("0 4463.0 4243.0 -340.0 377.0 102.0 target:0");
        Pod pod2 = Pod.fromDebug("1 4463.0 4243.0 -340.0 377.0 102.0 target:0");
        Pod pod3 = Pod.fromDebug("2 4463.0 4243.0 -340.0 377.0 102.0 target:0");
        Pod pod4 = Pod.fromDebug("3 4463.0 4243.0 -340.0 377.0 102.0 target:0");

        List<Pod> allPod = Arrays.asList(pod1, pod2, pod3, pod4);
        pod1.setOtherPods(allPod);

        assertEquals(3, pod1.getOtherPods().size());
    }


}