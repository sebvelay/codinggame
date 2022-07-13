package com.cg.game;


import com.cg.game.chemin.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KitchenTest {

    @Test
    void create_kitchen() {
        String line1 = "############";
        String line2 = "#..#########";
        Kitchen kitchen = new Kitchen(new String[]{line1, line2});

        char c = kitchen.getCase(0, 0);
        assertThat(c).isEqualTo('#');

        char c1 = kitchen.getCase(1, 1);
        assertThat(c1).isEqualTo('.');
    }

    @Test
    void getDistance() {
        Kitchen kitchen = initKitchen();

        int distance = kitchen.distance(1, 4, 3, 0);

        assertThat(distance).isEqualTo(5);
    }



    @Test
    void getVoisin() {

        Kitchen kitchen = initKitchen();

        Node depart = new Node(1,4);

        List<Node> voisin = kitchen.voisin(depart);

        assertThat(voisin.size()).isEqualTo(2);
        assertThat(voisin).contains(new Node(1,3), new Node(1,5));

    }

    @Test
    void getVoisin2() {

        Kitchen kitchen = initKitchen();

        Node depart = new Node(1,1);

        List<Node> voisin = kitchen.voisin(depart);

        assertThat(voisin.size()).isEqualTo(2);
        assertThat(voisin).contains(new Node(1,2), new Node(2,1));

    }

    private Kitchen initKitchen() {
        Kitchen kitchen = new Kitchen(new String[]{
                "#####D#####",
                "#.........#",
                "#.####.##.I",
                "B.#..#..#.#",
                "#.##.####.#",
                "#.........#",
                "#####W#####"
        });
        return kitchen;
    }


}