package com.cg.game.item;

public class Icecream implements Item{
    int i;
    int j;

    public Icecream(int i, int j) {
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
