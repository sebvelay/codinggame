package com.searchrace.game;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static int lap = 1;
    private static List<Checkpoint> checkpoints = new ArrayList<>();

    public static void addCheckpoints(Checkpoint checkpoint) {

        checkpoints.add(checkpoint);

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
                    return checkpoints.get(0);
                } else {
                    return checkpoints.get(i + 1);
                }
            }
        }
        return null;
    }
}
