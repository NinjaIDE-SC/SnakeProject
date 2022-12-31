package demo.java.cell;

public class Cell {
 
    private final int x; // coordinate x
    private final int y; // coordinate y 
    private CellType celltype; // type of cell

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        celltype = CellType.VOID;
    }
 
    public Cell(int x, int y, CellType celltype) {
        this.x = x;
        this.y = y;
        this.celltype = celltype;
    }

    public boolean equalCells(Cell other) {
        return (x == other.x && y == other.y && celltype == other.celltype);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || getClass() != other.getClass()) {
            return false;
        } else {
            return equalCells((Cell) other);
        }
    }

    public boolean equalX(Cell other) {
        return x == other.x;
    }

    public boolean equalY(Cell other) {
        return y == other.y;
    }

    public boolean equalXY(Cell other) {
        return (x == other.x && y == other.y);
    }

    @Override
    public String toString() {
        return "("+x+","+y+")" +  " celltype: " + celltype.toString();
    }
 
    public int getX() { 
        return x;
    }

    public int getY() { 
        return y;
    }

    public CellType getCellType() { 
        return celltype;
    }

    public void setCellType(CellType celltype) { 
        this.celltype = celltype;
    }

    public Cell modifyCell(CellType celltype) {
        this.celltype = celltype;
        return this;
    }
            
}