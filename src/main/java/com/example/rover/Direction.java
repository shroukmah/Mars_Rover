package com.example.rover;

public enum Direction {
    EAST(0),
    NORTH(1),
    WEST(2),
    SOUTH(3);

    private int numVal;

    Direction(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
