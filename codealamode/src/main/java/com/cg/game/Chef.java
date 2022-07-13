package com.cg.game;

public class Chef {
    Desert inprogress;
    Desert inMyHand;

    public void cook(Desert inprogress) {
        this.inprogress = inprogress;
        inMyHand.debug("I wana cook : ");
    }

    public void inMyHand(Desert inMyHand) {
        this.inMyHand = inMyHand;
        inMyHand.debug("I have : ");
    }

    public String nextItem() {
        if (inMyHand.itemList.length == 0) {
            System.err.println("rien dans la main je veux " + inprogress.itemList[0]);
            return inprogress.itemList[0];
        }
        for (String item : inprogress.itemList) {
            boolean foundItem = false;
            for (String itemInHand : inMyHand.itemList) {
                if (item.equals(itemInHand)) {
                    foundItem = true;
                    break;
                }
            }
            if (!foundItem) {
                return item;
            }
        }
        return "W";
    }

}
