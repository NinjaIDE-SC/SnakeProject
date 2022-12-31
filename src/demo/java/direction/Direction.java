package demo.java.direction;

import java.util.Random;

public enum Direction {
    NORTH,
    EAST,
    WEST,
    SOUTH,
    none;

    @Override
    public String toString() {
        return super.toString();
    }

    public char toChar() {
        return super.toString().charAt(0);
    }

    public int toInt() {
        return super.ordinal();
    }

    public static Direction fromString(String s) {
        Direction sd = null;
        for(Direction d : Direction.values()) {
            if(s.equals(sd.toString())) {
                sd = d;
            }
        }
        return sd;
    }

    public static Direction fromChar(char c) {
        Direction cd = null;
        for(Direction d : Direction.values()) {
            if(c == d.toChar()) {
                cd = d;
            }
        }
        return cd;
    }

    public static Direction fromInt(int i) {
        Direction id = null;
        for(Direction d : Direction.values()) {
            if(i == d.toInt()) {
                id = d;
            }
        }
        return id;
    }

    public static int size() {
        return Direction.values().length;
    }

    public static Direction randomRandom() {
        Random random = new Random();
        return fromInt(random.nextInt(size()));
    }

    public Direction otherRandomDirection() {
        Direction other = randomRandom();
        if(this != other) {
            return other;
        } else {
            return otherRandomDirection();
        }
    }

}