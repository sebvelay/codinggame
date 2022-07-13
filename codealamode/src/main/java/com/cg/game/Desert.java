package com.cg.game;

public class Desert {
    String[] itemList;

    public Desert(String[] itemList) {
        this.itemList = itemList;
    }

    public void debug(String message) {
        System.err.print(message+" ");
        for (int i = 0; i < itemList.length; i++) {
            System.err.print(itemList[i]+" ");
        }
        System.err.println();
    }
}
