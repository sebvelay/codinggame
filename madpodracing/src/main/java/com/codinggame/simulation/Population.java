package com.codinggame.simulation;

import com.codinggame.game.Pod;

import java.util.concurrent.ThreadLocalRandom;

public class Population {
    //for pod 1
    public Move[] moves;
    public Pod pod;
    double score = 0;
    double amplitude = Constant.AMPLITUDE;


    private Population(Pod initialPod) {
        createPopulation(initialPod, Constant.AMPLITUDE);
    }


    private Population(Population previous, Pod pod) {
        this.pod = pod;
        moves = new Move[Constant.DEEP_MOVE];
        pod.saveSate();
        this.score = 0;
        for (int j = 0; j < Constant.DEEP_MOVE; j++) {
            if (j < Constant.DEEP_MOVE - 1) {
                this.moves[j] = previous.moves[j + 1];
                this.pod.apply(moves[j], j);
            } else {
                this.moves[j] = new Move(this.pod.getRotationToNextCheckpoint(), Constant.MAX_THRUST);
                this.pod.apply(moves[j], j);
            }
        }
        this.score();
        pod.restore();

    }

    private Population(Pod pod, double amplitude) {
        createPopulation(pod, amplitude);

    }

    private Population(Pod initialPod, boolean boosted) {
        this.pod = initialPod;
        this.pod.saveSate();

        this.moves = new Move[Constant.DEEP_MOVE];


        for (int i = 0; i < moves.length; i++) {

            double angle = 0;
            //je peux rotate autant que je veux
            if (pod.vx == 0 && pod.vy == 0) {
                angle = pod.getAngleToNextCheckpoint();
            } else {
                angle = pod.getRotationToNextCheckpoint();
            }

            int thrust = (int) (Constant.MAX_THRUST * amplitude);
            if (i == 0) {
                thrust = Constant.BOOST_VALUE;
            }

            moves[i] = new Move(angle, thrust);
            pod.apply(moves[i], i);
        }
        this.score();
        this.pod.restore();
    }

    public static Population generateWithCustomAmplitude(Pod pod, double amplitude) {
        return new Population(pod, amplitude);
    }

    public static Population generateBoosted(Pod pod) {
        return new Population(pod, true);
    }

    public static Population generate(Pod pod) {
        return new Population(pod);
    }

    public static Population generateFromPrevious(Population previous, Pod pod) {
        return new Population(previous, pod);
    }

    private void createPopulation(Pod initialPod, double amplitude) {
        this.pod = initialPod;
        this.pod.saveSate();

        this.moves = new Move[Constant.DEEP_MOVE];


        for (int i = 0; i < moves.length; i++) {

            double angle = initialPod.getRotationToNextCheckpoint();


            int thrust = (int) (Constant.MAX_THRUST * amplitude);

            moves[i] = new Move(angle, thrust);
            pod.apply(moves[i], i);
        }
        this.score();
        this.pod.restore();
    }

    public Population clone() {
        pod.saveSate();
        Population population = new Population(pod);
        //clone each move
        population.moves = new Move[moves.length];
        //  population.moves2 = new Move[moves2.length];
        population.score = score;
        population.amplitude = amplitude;

        for (int i = 0; i < moves.length; i++) {
            population.moves[i] = moves[i].clone();
            //  population.moves2[i] = moves2[i].clone();
        }
        pod.restore();
        return population;
    }

    public void mutate() {
        Turn.mutationCount++;
        //on ne mutate que 1 move
        int i = ThreadLocalRandom.current().nextInt(0, moves.length);
        moves[i].mutate(amplitude);
        amplitude = amplitude - Constant.AMPLITUDE_PAS;

    }

    public double score() {
        this.score = pod.getScore() + this.moves[0].thrust;
        return this.score;
    }

    public double applyAndScore() {
        this.pod.saveSate();

        for (int i = 0; i < moves.length; i++) {
            pod.apply(moves[i], i);
        }
        score();

        this.pod.restore();

        return score;
    }
}
