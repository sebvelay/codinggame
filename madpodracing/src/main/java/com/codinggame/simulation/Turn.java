package com.codinggame.simulation;

import com.codinggame.Player;
import com.codinggame.game.Pod;

import java.util.concurrent.ThreadLocalRandom;

public class Turn {
    public static int mutationCount = 0;
    public static long start = 0;
    public static int betterSolution = 0;
    public static int betterFromNewGeneration = 0;
    public double amplitude = Constant.AMPLITUDE;

    public static void startChrono() {
        start = System.currentTimeMillis();
        if (Constant.DEBUG) {
            System.err.println("start " + start);
            System.err.println("MAX_TIME_FOR_MUTATION " + Constant.MAX_TIME_FOR_MUTATION);
        }
    }

    public static long elapsedTime() {
        return System.currentTimeMillis() - start;
    }

    public Move[] bestMove(Pod pod1, Pod pod2) {
        if (Constant.DEBUG) {
            System.err.println("1 " + elapsedTime());
        }
        betterSolution = 0;
        mutationCount = 0;
        betterFromNewGeneration = 0;
        Simulation simulation = new Simulation();
        pod1.saveSate();
        Population[] populationsForPod1 = simulation.generatePopulation(pod1);
        pod1.restore();
        pod2.saveSate();
        Population[] populationsForPod2 = simulation.generatePopulation(pod2);
        pod2.restore();

        boolean newGenerated = false;
        amplitude = Constant.AMPLITUDE;

        while ((Player.turnNumber == 1 && elapsedTime() < Constant.MAX_TIME_FOR_MUTATION_TURN_1) || (elapsedTime() < Constant.MAX_TIME_FOR_MUTATION)) {
            mutationCount++;

            int i = ThreadLocalRandom.current().nextInt(0, populationsForPod1.length);

            mutateOneSolution(populationsForPod1, i);
            mutateOneSolution(populationsForPod2, i);

            if (amplitude > 0.1) {
                createNewSolution(pod1, populationsForPod1);
                createNewSolution(pod2, populationsForPod2);
            }


            if (Constant.boostAvailable) {
                createBoostedSolution(pod1, populationsForPod1);
            }


        }
        if (Constant.DEBUG) {
            System.err.println(elapsedTime());
            System.err.println("mutation : " + mutationCount);
            System.err.println("betterSolution : " + betterSolution);
            System.err.println("betterFromNewGeneration : " + betterFromNewGeneration);
        }

        Population best1 = getBestSolution(populationsForPod1);
        Population best2 = getBestSolution(populationsForPod2);

        Simulation.previousForPod1 = populationsForPod1;
        Simulation.previousForPod2 = populationsForPod2;

        if (Constant.DEBUG) {
            System.err.println("best score 1 : " + best1.score);
            System.err.println("best score 2 : " + best2.score);
        }

        Move[] bestMoves = new Move[2];
        bestMoves[0] = best1.moves[0];
        bestMoves[1] = best2.moves[0];

        return bestMoves;
    }

    private Population getBestSolution(Population[] populations) {
        double minScore = -10000000;
        Population best = null;
        //select the best solution
        for (int i = 0; i < populations.length; i++) {
            if (populations[i].score > minScore) {
                minScore = populations[i].score;
                best = populations[i];
            }
        }
        return best;
    }

    private void createBoostedSolution(Pod pod, Population[] populations) {
        Population populationCreated = Population.generateBoosted(pod);
        double minScore = -10000000;
        for (int j = 0; j < populations.length; j++) {
            if (populations[j].score > minScore) {
                //populations[j] = popToMutate;
                minScore = populations[j].score;
                //isBetter = true;
                //betterSolution++;
            }
        }
        if (populationCreated.score > minScore) {
            betterSolution++;
            Constant.boostAvailable = false;
            //remove population with min score
            for (int j = 0; j < populations.length; j++) {
                if (populations[j].score == minScore) {
                    populations[j] = populationCreated;
                    break;
                }
            }
        }
    }

    private void createNewSolution(Pod pod1, Population[] populations) {
        Population populationCreated = Population.generateWithCustomAmplitude(pod1, amplitude);
        double minScore = -10000000;
        for (int j = 0; j < populations.length; j++) {
            if (populations[j].score > minScore) {
                //populations[j] = popToMutate;
                minScore = populations[j].score;
                //isBetter = true;
                //betterSolution++;
            }
        }
        if (populationCreated.score > minScore) {
            betterFromNewGeneration++;
            betterSolution++;
            //remove population with min score
            for (int j = 0; j < populations.length; j++) {
                if (populations[j].score == minScore) {
                    populations[j] = populationCreated;
                    break;
                }
            }
        }
        amplitude = amplitude - Constant.AMPLITUDE_PAS;

    }

    public void mutateOneSolution(Population[] populations, int i) {
        Population popToMutate = populations[i].clone();

        //mutate solution
        popToMutate.mutate();
        //score solution
        popToMutate.applyAndScore();
        //if score is better than minScore
        double minScore = -10000000;
        for (int j = 0; j < populations.length; j++) {
            if (populations[j].score > minScore) {
                minScore = populations[j].score;
            }
        }
        //on a le min score maintenant
        //on regarde si on a un meilleur score
        if (popToMutate.score > minScore) {
            betterSolution++;
            //remove population with min score
            for (int j = 0; j < populations.length; j++) {
                if (populations[j].score == minScore) {
                    populations[j] = popToMutate;
                    break;
                }
            }
        }
    }
}
