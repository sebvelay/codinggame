package com.cg.game.item;

public interface Item {
    int getI();

    int getJ();

    static String[] split(String name){
        String[] split = name.split("-");
        return split;
    }
}
