package com.cg;

import com.cg.game.Chef;
import com.cg.game.Desert;
import com.cg.game.Kitchen;
import com.cg.game.item.Item;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numAllCustomers = in.nextInt();
        for (int i = 0; i < numAllCustomers; i++) {
            String customerItem = in.next(); // the food the customer is waiting for
            int customerAward = in.nextInt(); // the number of points awarded for delivering the food
        }
        in.nextLine();
        String[] lines = new String[7];
        for (int i = 0; i < 7; i++) {
            String kitchenLine = in.nextLine();
            lines[i] = kitchenLine;
        }
        Kitchen kitchen = new Kitchen(lines);


        // game loop
        while (true) {
            kitchen.debug();
            int turnsRemaining = in.nextInt();
            int playerX = in.nextInt();
            int playerY = in.nextInt();
            String playerItem = in.next();

            Chef chef = new Chef();
            String[] itemsInMyHand = Item.split(playerItem);
            System.err.println("playerItem: " + playerItem);
            Desert inMyHand = new Desert(itemsInMyHand);
            chef.inMyHand(inMyHand);


            int partnerX = in.nextInt();
            int partnerY = in.nextInt();
            String partnerItem = in.next();
            System.err.println("partnerItem: " + partnerItem);

            int numTablesWithItems = in.nextInt(); // the number of tables in the kitchen that currently hold an item
            for (int i = 0; i < numTablesWithItems; i++) {
                int tableX = in.nextInt();
                int tableY = in.nextInt();
                String item = in.next();
                System.err.println(tableX + " " + tableY + " " + item);

            }
            String ovenContents = in.next(); // ignore until wood 1 league
            int ovenTimer = in.nextInt();
            int numCustomers = in.nextInt(); // the number of customers currently waiting for food

            for (int i = 0; i < numCustomers; i++) {
                String customerItem = in.next();
                int customerAward = in.nextInt();
                String[] items = Item.split(customerItem);
                Desert desert = new Desert(items);
                if (i == 0) {
                    System.err.println("customerItem: " + customerItem);
                    chef.cook(desert);
                }
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            String c = chef.nextItem();
            Item item = kitchen.getItem(c);
            if(item!=null){
                System.out.println("USE "+item.getI() + " " + item.getJ());
            }else{
                System.out.println("WAIT");
            }


            // MOVE x y
            // USE x y
            // WAIT
        }
    }
}