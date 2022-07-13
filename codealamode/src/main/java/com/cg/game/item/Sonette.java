package com.cg.game.item;

public class Sonette implements Item {
    int i;
    int j;

    public Sonette(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int getI() {
        return i;
    }

    @Override
    public int getJ() {
        return j;
    }

}
