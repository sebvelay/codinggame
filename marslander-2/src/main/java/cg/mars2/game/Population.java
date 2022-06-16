package cg.mars2.game;

import java.util.concurrent.ThreadLocalRandom;

public class Population {
    public Move[] moves = new Move[Constant.DEEP_MOVE];
    public double score;
    public Capsule capsule;

    public Population(Capsule capsule) {
        this.capsule = capsule;
        this.score = -999999999;
    }

    public Population mutate() {

        Population population = new Population(capsule);
        population.moves = new Move[moves.length];
        //copy all moves
        for (int i = 0; i < moves.length; i++) {
            population.moves[i] = new Move(moves[i].rotate, moves[i].power);
        }

        int i = ThreadLocalRandom.current().nextInt(0, moves.length);

        population.moves[i].mutate();
        //apply same parameters for all off nexts moves
        for(int j=(i+1);j<moves.length;j++){
            population.moves[j].power=population.moves[i].power;
            population.moves[j].rotate=population.moves[i].rotate;
        }



        population.applyAndScore();
        return population;
    }

    public void applyAndScore() {
        this.capsule.saveState();
        for (int i = 0; i < moves.length; i++) {
            capsule.apply(moves[i]);
        }

        this.score = capsule.getScore();
        this.capsule.restoreState();
    }
}
