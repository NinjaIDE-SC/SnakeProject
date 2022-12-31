package demo.java.snake;

import java.util.LinkedList;

import demo.java.cell.Cell;
import demo.java.cell.CellType;

public class Snake {

    private final LinkedList<Cell> body;

    public Snake(Cell cell) {
        body = new LinkedList<Cell>();
        body.addFirst(cell.modifyCell(CellType.BODY));
    }

    public Snake(Cell cell, int size) {
        body = new LinkedList<Cell>();
        body.addFirst(cell.modifyCell(CellType.BODY));
        bodySize(size);
    }

    private void bodySize(int size) {
        for(int s = 0; s < size; s++) {
            bodyGrows();
        }
    }
 
    public void bodyGrows() { 
        body.addLast(body.getLast()); 
    }
 
    public void bodyMoves(Cell nextcell) {
        body.removeLast().setCellType(CellType.VOID);
        body.addFirst(nextcell.modifyCell(CellType.BODY));
        body.getLast().setCellType(CellType.BODY);
    }

    @Override
    public String toString() {
        return body.toString();
    }
 
    public LinkedList<Cell> getBody() {
        return body;
    }

    public int getBodySize() { 
        return body.size(); 
    }

    public Cell getBodyPart(int s) { 
        return body.get(s); 
    }

    public Cell getHead() { 
        return body.getFirst(); 
    }

    public Cell getTail() { 
        return body.getLast(); 
    }

}