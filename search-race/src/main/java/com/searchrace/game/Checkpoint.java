package com.searchrace.game;

public class Checkpoint extends Unit {

    public final int id;

    public Checkpoint(int id, double x, double y) {
        super(0, x, y, 0, 0);
        this.id = id;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkpoint unit = (Checkpoint) o;
        return this.x == unit.x && this.y == unit.y;
    }


}
