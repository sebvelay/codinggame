package com.codinggame.ui;

import com.codinggame.Player;
import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import com.codinggame.simulation.Constant;
import com.codinggame.simulation.Map;
import com.codinggame.simulation.Move;
import com.codinggame.simulation.Population;
import com.codinggame.simulation.Simulation;
import com.codinggame.simulation.Turn;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PodFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 1600, 900, Color.GHOSTWHITE));
        stage.show();
    }

    private void initCheckpoint() {

       //for demo
       /* Map.addCheckpoints(new Checkpoint(0, 10655.0, 2302.0));
        Map.addCheckpoints(new Checkpoint(1, 8693.0, 7458.0));
        Map.addCheckpoints(new Checkpoint(2, 7179.0, 2166.0));
        Map.addCheckpoints(new Checkpoint(3, 3595.0, 5291.0));
        Map.addCheckpoints(new Checkpoint(4, 13815.0, 5091.0));*/


        /**
         * checkpoint : 0 11479.0 6062.0
         * checkpoint : 1 9085.0 1824.0
         * checkpoint : 2 5025.0 5259.0
         */
        Map.addCheckpoints(new Checkpoint(0, 11479.0, 6062.0));
        Map.addCheckpoints(new Checkpoint(1, 9085.0, 1824.0));
        Map.addCheckpoints(new Checkpoint(2, 5025.0, 5259.0));

    }

    public List<Checkpoint> getCheckpoint() {
        return Map.getCheckpoints();
    }

    public Pod getPod() {

        //for demo
       // return new Pod(0, 9068.0, 6164.0, -166.0, 485.0, 136, Map.getCheckpoints().get(1));

        //pod :0 11044.0 6308.0 0.0 0.0 358.01730811039016 target:1

        return new Pod(0, 11044.0, 6308.0, 0.0, 0.0, 358, Map.getCheckpoints().get(1));



    }

    private Parent createContent() {
        initCheckpoint();


        Pane root = new Pane();
        List<Checkpoint> checkpoint = this.getCheckpoint();

        //for each checkpoint
        List<Circle> circles = new ArrayList<>();
        for (Checkpoint c : checkpoint) {
            //create a circle
            Circle circle = new Circle(c.x / 10, c.y / 10, 60, Color.GREY);
            circles.add(circle);

            Text text = new Text(circle.getCenterX(), circle.getCenterY(), "" + c.id);
            Font font = new Font(20);
            text.setFont(font);


            root.getChildren().add(circle);
            root.getChildren().add(text);

        }

        Player.turnNumber++;
        Pod pod1 = this.getPod();
        Circle podCircle = new Circle(pod1.x / 10, pod1.y / 10, 5, Color.BLUE);
        root.getChildren().add(podCircle);

        Simulation simulation = new Simulation();
        pod1.saveSate();
        Population[] populationsForPod1 = simulation.generatePopulation(pod1);
        pod1.restore();

        for (Move move : populationsForPod1[0].moves) {
            System.err.println("move : " + move.angle + " " + move.thrust);

            pod1.apply(move, 0);
            Circle podMove = new Circle(pod1.x / 10, pod1.y / 10, 5, Color.RED);
            root.getChildren().add(podMove);

        }

        //mutation
        mutation(root, pod1, populationsForPod1,Color.GREEN);

        return root;
    }

    private void mutation(Pane root, Pod pod1, Population[] populationsForPod1,Color color) {
        Turn turn = new Turn();
        pod1.restore();
        Turn.startChrono();
        while ((Turn.elapsedTime() < Constant.MAX_TIME_FOR_MUTATION_TURN_1)) {

            int i = ThreadLocalRandom.current().nextInt(0, populationsForPod1.length);

            turn.mutateOneSolution(populationsForPod1, i);

        }
        for (Move move : populationsForPod1[0].moves) {
            pod1.apply(move, 0);
            System.err.println("move mutation : " + move.angle + " " + move.thrust);
            Circle podMove = new Circle(pod1.x / 10, pod1.y / 10, 5, color);
            root.getChildren().add(podMove);

        }
    }


}