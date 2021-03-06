package com.searchrace;

import com.searchrace.game.Checkpoint;
import com.searchrace.game.Pod;
import com.searchrace.simulation.Map;
import com.searchrace.simulation.Move;
import com.searchrace.simulation.Turn;

import java.util.Scanner;

public class Player {
    public static int turnNumber = 0;


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        boolean timerStarted = false;

        Turn turn = new Turn();
        int checkpoints = in.nextInt(); // Count of checkpoints to read

        Turn.startChrono();
        timerStarted = true;

        System.err.println("checkpoints : " + checkpoints);
        for (int i = 0; i < checkpoints; i++) {
            int checkpointX = in.nextInt(); // Position X
            int checkpointY = in.nextInt(); // Position Y
            Map.addCheckpoints(new Checkpoint(0, checkpointX, checkpointY));
        }

        // game loop
        while (true) {
            int checkpointIndex = in.nextInt(); // Index of the checkpoint to lookup in the checkpoints input, initially 0
            int x = in.nextInt(); // Position X
            if (timerStarted == false) {
                Turn.startChrono();
            }
            int y = in.nextInt(); // Position Y
            int vx = in.nextInt(); // horizontal speed. Positive is right
            int vy = in.nextInt(); // vertical speed. Positive is downwards
            int angle = in.nextInt(); // facing angle of this car
            System.err.println("checkpointIndex : " + checkpointIndex);
            Pod pod = new Pod(0, x, y, vx, vy, angle, Map.getCheckpoints().get(checkpointIndex));
            System.err.println("pod : 0 " + pod.x + " " + pod.y + " " + pod.vx + " " + pod.vy + " " + pod.angle + " target:" + checkpointIndex);

            Move[] moves = turn.bestMove(pod);

            String actionString = pod.getActionString(moves[0]);

            timerStarted = false;
            turnNumber++;
            System.out.println(actionString);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

        }
    }
}