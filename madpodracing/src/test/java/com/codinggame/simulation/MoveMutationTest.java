package com.codinggame.simulation;

import com.codinggame.game.Pod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveMutationTest {


    @Test
    void testAmplitude(){
        Move move = new Move(0, 100);

        move.mutateThurst(1);

    }
}
