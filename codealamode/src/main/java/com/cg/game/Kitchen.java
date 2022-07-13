package com.cg.game;

import com.cg.game.chemin.Node;
import com.cg.game.item.Blueberries;
import com.cg.game.item.Dish;
import com.cg.game.item.Icecream;
import com.cg.game.item.Item;
import com.cg.game.item.Sonette;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {

    char[][] kitchen;
    Item dish;
    Item blueberries;
    Item icecream;
    Item sonette;

    public Kitchen(String[] lines) {
        kitchen = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                char c = lines[i].charAt(j);
                if (c == '1' || c == '0') {
                    c = '.';
                }
                if (c == 'D') {
                    dish = new Dish(j, i);
                } else if (c == 'B') {
                    blueberries = new Blueberries(j, i);
                } else if (c == 'I') {
                    icecream = new Icecream(j, i);
                } else if (c == 'W') {
                    sonette = new Sonette(j, i);
                }
                kitchen[i][j] = c;
            }
        }

    }

    public Item getItem(String c) {
        if (c.equals("DISH")) {
            return dish;
        } else if (c.equals("BLUEBERRIES")) {
            return blueberries;
        } else if (c.equals("ICE_CREAM")) {
            return icecream;
        } else if (c.equals("W")) {
            return sonette;
        }
        return null;

    }

    public void debug() {
        for (int i = 0; i < kitchen.length; i++) {
            for (int j = 0; j < kitchen[i].length; j++) {
                System.err.print(kitchen[i][j]);
            }
            System.err.println();
        }
    }

    public char getCase(int i, int i1) {
        return kitchen[i][i1];
    }

    public List<Node> voisin(Node depart) {
        List<Node> voisin = new ArrayList<>();
        if (getCase(depart.i + 1, depart.j) == '.') {
            Node node = new Node(depart.i + 1, depart.j);
            node.addCost();
            voisin.add(node);
        }
        if (getCase(depart.i - 1, depart.j) == '.') {
            Node node = new Node(depart.i - 1, depart.j);
            node.addCost();
            voisin.add(node);
        }
        if (getCase(depart.i, depart.j + 1) == '.') {
            Node node = new Node(depart.i, depart.j + 1);
            node.addCost();
            voisin.add(node);
        }
        if (getCase(depart.i, depart.j - 1) == '.') {
            Node node = new Node(depart.i, depart.j - 1);
            node.addCost();
            voisin.add(node);
        }
        return voisin;
    }

    public int distance(int i, int j, int x, int y) {

        Node depart = new Node(i, j);
        Node arrive = new Node(x, y);

        List<Node> voisin = voisin(depart);



        return 0;
    }

}
