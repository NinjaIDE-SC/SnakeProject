package demo.java.cell;

import java.util.Random;

public enum CellType {
    VOID,
    WALL,
    FOOD,
    BODY;

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

    public static CellType fromString(String s) {
        CellType sct = null;
        for(CellType ct : CellType.values()) {
            if(s.equals(ct.toString())) {
                sct = ct;
            }
        }
        return sct;
    }

    public static CellType fromChar(char c) {
        CellType cct = null;
        for(CellType ct : CellType.values()) {
            if(c == ct.toChar()) {
                cct = ct;
            }
        }
        return cct;
    }

    public static CellType fromInt(int i) {
        CellType ict = null;
        for(CellType ct : CellType.values()) {
            if(i == ct.toInt()) {
                ict = ct;
            }
        }
        return ict;
    }

    public static int size() {
        return CellType.values().length;
    }

    public static CellType randomCellType() {
        Random random = new Random();
        return fromInt(random.nextInt(size()));
    }

    public CellType otherRandomCellType() {
        CellType other = randomCellType();
        if(this != other) {
            return other;
        } else {
            return otherRandomCellType();
        }
    }

}