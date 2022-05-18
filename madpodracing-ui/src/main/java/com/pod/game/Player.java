package com.pod.game;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import com.codinggame.simulation.Map;

import java.util.List;

public class Player {


    public Player() {
        Map.addCheckpoints(new Checkpoint(0, 7781, 880));
        Map.addCheckpoints(new Checkpoint(1, 7642, 5941));
        Map.addCheckpoints(new Checkpoint(2, 3167, 7539));
        Map.addCheckpoints(new Checkpoint(3, 9534, 4385));
        Map.addCheckpoints(new Checkpoint(3, 14538, 7767));
        Map.addCheckpoints(new Checkpoint(3, 6312, 4271));

    }

    public List<Checkpoint> getCheckpoint() {
        return Map.getCheckpoints();
    }

    public Pod getPod() {
        return new Pod(0, 8281, 894, 0, 0, -1, Map.getCheckpoints().get(1));
    }
}
