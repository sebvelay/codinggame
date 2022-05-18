package com.codinggame.game;

import com.codinggame.simulation.Map;
import com.codinggame.simulation.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PodOnCheckpointTest {

    @BeforeEach
    void setUp() {
        Map.clearCheckpoints();
    }

}
