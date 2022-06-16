package cg.mars2.game;

import cg.mars2.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    public static int TOTAL_MUTATION = 0;
    public static int BEST_MUTATION = 0;

    public Population[] populations = new Population[Constant.POPULATION_SIZE];

    public void generateSimulation(Capsule capsule) {

        if (Player.oldPopulation == null) {
            //for each population
            for (int j = 0; j < Constant.POPULATION_SIZE; j++) {
                Population population = createPopulation(capsule);
                populations[j] = population;
            }
        } else {
            populations = Player.oldPopulation;
            for (int j = 0; j < Constant.POPULATION_SIZE; j++) {
                populations[j].capsule = capsule;
                for (int i = 0; i < Constant.DEEP_MOVE - 1; i++) {
                    populations[j].moves[i] = populations[j].moves[i + 1];
                }
                populations[j].moves[Constant.DEEP_MOVE - 1] = new Move(0, 4);


                for (int i = 0; i < Constant.DEEP_MOVE; i++) {
                    populations[j].applyAndScore();
                }

            }


        }

    }

    public void mutate() {
        System.err.println("Start mutation");
        TOTAL_MUTATION = 0;
        BEST_MUTATION = 0;
        boolean newGenerated = false;
        while (Chrono.elapsedTime() < Constant.MAX_TIME_FOR_MUTATION) {
            int i = ThreadLocalRandom.current().nextInt(0, populations.length);
            Population mutated = populations[i].mutate();

            keepBest(populations, mutated);


            TOTAL_MUTATION++;
        }
        System.err.println("Total mutation : " + TOTAL_MUTATION);
        System.err.println("Best mutation : " + BEST_MUTATION);
    }

    public void keepBest(Population[] populations, Population newPopulation) {
        for (int i = 0; i < Constant.POPULATION_SIZE; i++) {
            if (populations[i].score < newPopulation.score) {
                BEST_MUTATION++;
                populations[i] = newPopulation;
                break;
            }
        }

    }

    public Population getBest() {
        Population best = populations[0];
        for (int i = 1; i < Constant.POPULATION_SIZE; i++) {
            if (best.score < populations[i].score) {
                best = populations[i];
            }
        }
        System.err.println("Best score : " + best.score);
        Player.oldPopulation = populations;
        return best;
    }

    private Population createPopulation(Capsule capsule) {
        Population population = new Population(capsule);

        capsule.saveState();
        for (int i = 0; i < Constant.DEEP_MOVE; i++) {
            //on prend un point à hauteur de la capsule
            //capsule.debug("start");
            Point p1 = new Point(capsule.target.x, capsule.y);

            //System.err.println(capsule.getAngle(capsule.target) - 180);


            /*double rotation = capsule.getRotation(capsule.target);
            if (capsule.x < capsule.target.x) {
                rotation = -rotation;
            }
            int power = 1;
            double distance = capsule.distance(p1);
            if (distance < 1000) {
                rotation = 0;
                power = removePower(capsule);
            } else {
                if (Math.abs(capsule.verticalSpeed) < 20) {
                    power = addPower(capsule);
                }
            }

            if (rotation == 0 && Math.abs(capsule.verticalSpeed) > 20) {
                power = addPower(capsule);
            }*/


            Move move = new Move(0, 0);
            population.moves[i] = move;

        }
        population.applyAndScore();

        capsule.restoreState();
        return population;
    }

    public int addPower(Capsule capsule) {
        if (capsule.power == 4) {
            return capsule.power;
        }
        return capsule.power + 1;
    }

    public int removePower(Capsule capsule) {
        if (capsule.power == 1) {
            return capsule.power;
        }
        return capsule.power - 1;
    }

}
