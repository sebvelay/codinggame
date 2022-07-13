package com.cg.game.chemin;

import java.util.Objects;

public class Node {
    public int i;
    public int j;
    public int cost = 0;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void addCost() {
        cost++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return i == node.i && j == node.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
