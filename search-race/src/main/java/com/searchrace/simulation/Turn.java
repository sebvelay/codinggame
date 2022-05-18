package com.searchrace.simulation;

import com.searchrace.game.Pod;

import java.util.concurrent.ThreadLocalRandom;

public class Turn {
    public static int mutationCount = 0;
    public static long start = 0;
    public static int betterSolution = 0;
    public static int betterFromNewGeneration = 0;
    public double amplitude = Constant.AMPLITUDE;

    public static void startChrono() {
        start = System.currentTimeMillis();
    }

    public static long elapsedTime() {
        return System.currentTimeMillis() - start;
    }

    public Move[] bestMove(Pod pod1) {
        betterSolution = 0;
        mutationCount = 0;
        betterFromNewGeneration = 0;
        Simulation simulation = new Simulation();
        pod1.saveSate();
        Population[] populationsForPod1 = simulation.generatePopulation(pod1);
        pod1.restore();

        boolean newGenerated = false;

        while (elapsedTime() < Constant.MAX_TIME_FOR_MUTATION) {
            mutationCount++;

            int i = ThreadLocalRandom.current().nextInt(0, populationsForPod1.length);

            mutateOneSolution(populationsForPod1, i);

            if (!newGenerated) {
                createNewSolution(pod1, populationsForPod1);
                newGenerated = true;
            }

            if (Constant.boostAvailable) {
                createBoostedSolution(pod1, populationsForPod1);
            }


        }
        System.err.println("mutation : " + mutationCount);
        System.err.println("betterSolution : " + betterSolution);
        System.err.println("betterFromNewGeneration : " + betterFromNewGeneration);

        Population best1 = getBestSolution(populationsForPod1);

        Simulation.previousForPod1 = populationsForPod1;

        System.err.println("best score 1 : " + best1.score);
        System.err.println("complete simulation : ");
        //for each move on best1
        for (int i = 0; i < best1.moves.length; i++) {
            System.err.println("move : " + i + " angle : " + best1.moves[i].angle + " thrust : " + best1.moves[i].thrust);
        }


        Move[] bestMoves = new Move[2];
        bestMoves[0] = best1.moves[0];

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

    private void mutateOneSolution(Population[] populations, int i) {
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
