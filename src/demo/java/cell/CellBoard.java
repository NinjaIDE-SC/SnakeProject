package demo.java.cell;

import java.util.Random;

public class CellBoard {

    private final int xb; // bound for x (x bound = xb) ≈> we can think as number rows
    private final int yb; // bound for y (y bound = yb) ≈> we can think as number columns 
    private final Cell[][] cells; // cellboard

    public CellBoard(int xb, int yb, CellType celltype) {
        this.xb = xb;
        this.yb = yb;
        cells = new Cell[xb][yb];
        fillCellBoard(celltype);
    }

    public CellBoard(int xb, int yb) {
        this(xb, yb, CellType.VOID);
    }

    private void fillCellBoard(CellType celltype) {
        for (int x = 0; x < xb; x++) {
            for (int y = 0; y < yb; y++) {
                cells[x][y] = new Cell(x, y, celltype);
            }
        }
    }

    public Cell setRandomCellType(CellType celltype) {
        Random random = new Random();
        Cell cell = null;
        int x;
        int y;
        boolean loop = true;
        while(loop) {
            x = random.nextInt(getXB());
            y = random.nextInt(getYB());
            cell = getCell(x,y);
            if(cell.getCellType() == CellType.VOID) {
                cell.setCellType(celltype);
                loop = false;
            }
        }
        return cell;
    }

    public Cell setRandomCellTypeRecursive(CellType celltype) {
        Random random = new Random();
        int newx = random.nextInt(getXB());
        int newy = random.nextInt(getYB());
        Cell cell = getCell(newx,newy);
        if(cell.getCellType() == CellType.VOID) {
            cell.setCellType(celltype);
            return cell;
        } else {
            return setRandomCellTypeRecursive(celltype);
        }
    }

    private boolean equalCellBoards(CellBoard other) {
        if(xb != other.xb || yb != other.yb) {
            return false;
        } else {
            for (int i = 0; i < xb; i++) {
                for (int j = 0; j < yb; j++) {
                    if(!cells[i][j].equals(other.cells[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || getClass() != other.getClass()) {
            return false;
        } else {
            return equalCellBoards((CellBoard) other);
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < xb; i++) {
            for (int j = 0; j < yb; j++) {
                string += cells[i][j].getCellType().toString() + ",";
                if(j < (yb - 1)) { 
                    string += " ";
                }
            }
            if(i < (xb-1)) { 
                string += System.lineSeparator();
            }
        }
        return string;
    }

    public boolean isCellInBounds(int x, int y) {
        return ((x >= 0 && x < xb) && (y >= 0 && y < yb));
    }

    public int getXB() {
        return xb;
    }

    public int getYB() {
        return yb;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getSize() {
        return xb * yb;
    }

    public Cell getCell(int x, int y) {
        if(isCellInBounds(x, y)) {
            return cells[x][y];
        } else {
            return null;
        }
    }

    public Cell modifyCell(int x, int y, CellType celltype) {
        if(isCellInBounds(x, y)) {
            return cells[x][y].modifyCell(celltype);
        } else {
            return null;
        }
    }

}