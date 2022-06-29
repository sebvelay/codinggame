package com.codinggame.simulation;

import com.codinggame.game.Checkpoint;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static int lap = 1;
    private static List<Checkpoint> checkpoints = new ArrayList<>();

    public static void addCheckpoints(Checkpoint checkpoint) {
        checkpoints.add(checkpoint);
    }

    public static void finalizeMap(int lap) {
        List<Checkpoint> fullCheckpoints = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < lap; i++) {

            for (int j = 0; j < checkpoints.size(); j++) {
                fullCheckpoints.add(new Checkpoint(id++, checkpoints.get(j).x, checkpoints.get(j).y));
            }
        }
        checkpoints = fullCheckpoints;

    }

    public static List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public static void clearCheckpoints() {
        checkpoints.clear();
    }


    public static Checkpoint nextCheckpoint(Checkpoint nextCheckpoint) {

        for (int i = 0; i < checkpoints.size(); i++) {
            if (checkpoints.get(i).equals(nextCheckpoint)) {
                if (i + 1 >= checkpoints.size()) {
                    return null;
                } else {
                    return checkpoints.get(i + 1);
                }
            }
        }
        return null;
    }

    public static void debug() {
        if (Constant.DEBUG) {
            for (Checkpoint checkpoint : checkpoints) {
                System.err.println("checkpoint : " + checkpoint.id + " " + checkpoint.x + " " + checkpoint.y);
            }
        }

    }
}
