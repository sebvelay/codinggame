package com.searchrace.simulation;

import com.searchrace.game.Pod;

public class Simulation {

    public static Population[] previousForPod1 = null;
    public static Population[] previousForPod2 = null;


    public Population[] generatePopulation(Pod pod) {

        Population[] populations = new Population[Constant.POPULATION_SIZE];

        //double[] angles = generateRangeOfAngle(Constant.ANGLE_MIN, Constant.ANGLE_MAX);

        //racourics douteux : le premier et le second ne seront plus null en même temps
        if (previousForPod1 == null) {
            for (int i = 0; i < populations.length; i++) {
                populations[i] = Population.generate(pod);

                populations[i].score();
            }
        } else {
            //on génére depuis l'ancien
            for (int i = 0; i < populations.length; i++) {
                if (pod.id == 0) {
                    populations[i] = Population.generateFromPrevious(previousForPod1[i], pod);
                } else {
                    populations[i] = Population.generateFromPrevious(previousForPod2[i], pod);
                }
            }


        }


        /*//remove boost if already used
        for (int i = 0; i < populations.length; i++) {
            for (int j = 0; j < populations[i].moves.length; j++) {
                if (populations[i].moves[j].thrust > 100) {
                    populations[i].moves[j].thrust = 100;
                }
            }
        }*/

        return populations;
    }


    public double[] generateRangeOfAngle(double from, double to) {


        double[] doubles = new double[Constant.POPULATION_SIZE - 1];

        //generate range of angle from -18 to 18
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = from + (to - from) * i / (doubles.length - 1);
        }

        return doubles;
    }
}
