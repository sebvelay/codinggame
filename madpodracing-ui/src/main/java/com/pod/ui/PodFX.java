package com.pod.ui;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import com.codinggame.simulation.Constant;
import com.codinggame.simulation.Move;
import com.codinggame.simulation.Population;
import com.codinggame.simulation.Simulation;
import com.codinggame.simulation.Turn;
import com.pod.game.Player;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PodFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 1600, 900, Color.GHOSTWHITE));
        stage.show();
    }

    private Parent createContent() {

        Pane root = new Pane();
        Player player = new Player();
        List<Checkpoint> checkpoint = player.getCheckpoint();

        //for each checkpoint
        List<Circle> circles = new ArrayList<>();
        for (Checkpoint c : checkpoint) {
            //create a circle
            Circle circle = new Circle(c.x / 10, c.y / 10, 60, Color.BLUE);
            circles.add(circle);

            root.getChildren().add(circle);
        }


        Pod pod = player.getPod();
        displayNewPopulation(root, pod.clone());
        getBestForTurn(root, pod.clone());



        return root;
    }

    private void getBestForTurn(Pane root, Pod pod) {
        Turn turn = new Turn();
        Turn.startChrono();
        Turn.start += 60000;
        Move[] moves = turn.bestMove(pod, null);
        int baseX = (int) pod.x / 10;
        int baseY = (int) pod.y / 10;
        //for each move
        int i = 0;
        for (Move m : moves) {
            pod.apply(m);

            int endX = (int) pod.x / 10;
            int endY = (int) pod.y / 10;
            Line line = new Line(baseX, baseY, endX, endY);
            line.setStroke(Color.GREEN);
            root.getChildren().add(line);
            baseX = endX;
            baseY = endY;
        }
    }

    private void displayNewPopulation(Pane root, Pod pod) {
        Simulation simulation = new Simulation();
        Population[] populations = simulation.generatePopulation(pod, null);
        //for each population



        //for each move in the population
        for (int j = 0; j < populations[0].moves1.length; j++) {
            int baseX = (int) pod.x / 10;
            int baseY = (int) pod.y / 10;
            System.err.println(pod.x+" "+populations[0].pod1.x);
            pod.apply(populations[0].moves1[j]);

            int endX = (int) pod.x / 10;
            int endY = (int) pod.y / 10;
            Line line = new Line(baseX, baseY, endX, endY);
            line.setStroke(Color.RED);
            root.getChildren().add(line);

        }
    }
}