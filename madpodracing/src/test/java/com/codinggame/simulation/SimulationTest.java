package com.codinggame.simulation;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimulationTest {


    @Test
    void generateRangeOfAngle() {
        Simulation simulation = new Simulation();

        double[] angle = simulation.generateRangeOfAngle(-18, 18);

        assertEquals(Constant.POPULATION_SIZE - 1, angle.length);

        assertEquals(-18, angle[0]);
        assertEquals(18, angle[angle.length - 1]);
    }

    @Test
    void generateNewPopulation() {
        Simulation simulation = new Simulation();
        Checkpoint nextCheckpoint = new Checkpoint(0, 5900, 3400);
        Pod pod1 = new Pod(0, 0, 0, 0, 0, 0, nextCheckpoint);
        Population[] populations = simulation.generatePopulation(pod1);
        assertEquals(Constant.POPULATION_SIZE, populations.length);
    }

    @Test
    void generateNewPopulationWithPrevious() {

        Simulation simulation = new Simulation();
        Checkpoint nextCheckpoint = new Checkpoint(0, 5900, 3400);
        Pod pod1 = new Pod(0, 0, 0, 0, 0, 0, nextCheckpoint);
        Population[] populations = simulation.generatePopulation(pod1);
        Simulation.previousForPod1 = populations;
        Population[] populations2 = simulation.generatePopulation(pod1);

        assertEquals(populations[0].moves[1].angle, populations2[0].moves[0].angle);
        assertEquals(populations[0].moves[1].thrust, populations2[0].moves[0].thrust);

        //for population size
        for (int i = 0; i < Constant.POPULATION_SIZE; i++) {
            for (int j = 0; j < Constant.DEEP_MOVE; j++) {
                if (j < Constant.DEEP_MOVE - 1) {
                    System.err.println(i+" / "+j);
                    assertEquals(populations[i].moves[j + 1].angle, populations2[i].moves[j].angle);
                    assertEquals(populations[i].moves[j + 1].thrust, populations2[i].moves[j].thrust);
                } else {
                    assertNotNull(populations2[i].moves[j]);
                }
            }
        }
    }

    @Test
    public void generatePopulationOnPod(){
        Simulation simulation = new Simulation();
        Checkpoint nextCheckpoint = new Checkpoint(0, 5900, 3400);
        Pod pod1 = new Pod(0, 0, 0, 0, 0, 0, nextCheckpoint);
        Population[] populations = simulation.generatePopulation(pod1);

        Assertions.assertEquals(0, populations[0].pod.x);
    }

}
