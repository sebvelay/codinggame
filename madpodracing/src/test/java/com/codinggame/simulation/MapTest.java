package com.codinggame.simulation;

import com.codinggame.game.Checkpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapTest {

    @BeforeEach
    void setUp() {
        Map.clearCheckpoints();
    }

    @Test
    void addCheckPoint() {
        Checkpoint checkpoint = new Checkpoint(0,0, 0);
        Map.addCheckpoints(checkpoint);

        assertEquals(1, Map.getCheckpoints().size());

    }

    @Test
    void addAnotherCheckPoint() {
        Checkpoint checkpoint = new Checkpoint(0,0, 0);
        Map.addCheckpoints(checkpoint);

        Checkpoint checkpoint2 = new Checkpoint(1,2, 0);
        Map.addCheckpoints(checkpoint2);


        assertEquals(2, Map.getCheckpoints().size());

    }

    @Test
    void addExistingCheckPoint() {
        Checkpoint checkpoint = new Checkpoint(0,0, 0);
        Map.addCheckpoints(checkpoint);

        Checkpoint checkpoint2 = new Checkpoint(1,0, 0);

        Map.addCheckpoints(checkpoint2);


        assertEquals(1, Map.getCheckpoints().size());
    }

    @Test
    void equalsCheckpoint(){
        assertEquals(new Checkpoint(0,0, 0),new Checkpoint(1,0, 0));
    }

}