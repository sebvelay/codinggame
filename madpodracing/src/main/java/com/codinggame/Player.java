package com.codinggame;

import com.codinggame.game.Checkpoint;
import com.codinggame.game.Pod;
import com.codinggame.simulation.Constant;
import com.codinggame.simulation.Map;
import com.codinggame.simulation.Move;
import com.codinggame.simulation.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    public static int turnNumber = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        boolean timerStarted = false;

        Turn turn = new Turn();

        int laps = in.nextInt();
        Turn.startChrono();
        timerStarted = true;

        Constant.NB_TURN = laps;
        int checkpointCount = in.nextInt();
        for (int i = 0; i < checkpointCount; i++) {
            int checkpointX = in.nextInt();
            int checkpointY = in.nextInt();
            Checkpoint checkpoint = new Checkpoint(i, checkpointX, checkpointY);
            Map.addCheckpoints(checkpoint);
        }
        Map.finalizeMap(Constant.NB_TURN);
        Map.debug();

        // game loop
        while (true) {
            turnNumber++;
            List<Pod> podList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                int x = in.nextInt(); // x position of your pod
                if (timerStarted == false) {
                    Turn.startChrono();
                    timerStarted = true;
                }


                int y = in.nextInt(); // y position of your pod
                int vx = in.nextInt(); // x speed of your pod
                int vy = in.nextInt(); // y speed of your pod
                int angle = in.nextInt(); // angle of your pod
                int nextCheckPointId = in.nextInt(); // next check point id of your pod
                Pod pod = new Pod(i, x, y, vx, vy, angle, Map.getCheckpoints().get(nextCheckPointId));
                podList.add(pod);
                pod.debug();
            }
            for (int i = 0; i < 2; i++) {
                int x2 = in.nextInt(); // x position of the opponent's pod
                int y2 = in.nextInt(); // y position of the opponent's pod
                int vx2 = in.nextInt(); // x speed of the opponent's pod
                int vy2 = in.nextInt(); // y speed of the opponent's pod
                int angle2 = in.nextInt(); // angle of the opponent's pod
                int nextCheckPointId2 = in.nextInt(); // next check point id of the opponent's pod
                Pod otherPod = new Pod(1 + i, x2, y2, vx2, vy2, angle2, Map.getCheckpoints().get(nextCheckPointId2));
                podList.add(otherPod);
            }


            Pod pod1 = podList.get(0);
            pod1.setOtherPods(podList);
            Pod pod2 = podList.get(1);
            pod2.setOtherPods(podList);

            timerStarted = false;

            Move[] move = turn.bestMove(pod1, pod2);

            System.out.println(pod1.getActionString(move[0]));
            System.out.println(pod2.getActionString(move[1]));


        }
    }
}