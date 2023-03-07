package demo.java.snake;

import java.util.LinkedList;

import demo.java.cell.Cell;
import demo.java.cell.CellType;

// maybe one day... (work in progress)

public class StrangeSnake {

    private final LinkedList<Cell> body;
    private Cell head; // head;
    private Cell tail; // tail;

    public StrangeSnake(Cell cell) {
        body = new LinkedList<Cell>();
        // add head to the body
        cell.setCellType(CellType.BODY);
        head = cell;
        tail = cell;
        body.addFirst(head);
    }

    public StrangeSnake(Cell head, Cell tail) {
        body = new LinkedList<Cell>();
        // add head to the body
        this.head = head.modifyCell(CellType.BODY);
        this.tail = tail.modifyCell(CellType.BODY);
        body.addFirst(head);
        body.addLast(tail);
    }

    public void bodyFistGrows(Cell cell) { 
        body.addFirst(cell); 
        head = body.getFirst();
    }

    public void bodyLastGrows(Cell cell) { 
        body.addLast(cell); 
        tail = body.getLast();
    }
 
    public void bodyMovesForward(Cell newcell) {
        // add newcell (head) to the front of the body
        head = newcell.modifyCell(CellType.BODY);
        body.addFirst(head);
        // remove oldcell (tail) to the back of the body
        body.removeLast().setCellType(CellType.VOID);
        tail = body.getLast().modifyCell(CellType.BODY);
    }

    public void bodyMovesBackward(Cell newcell) {
        // add newcell (tail) to the back of the body
        tail = newcell.modifyCell(CellType.BODY);
        body.addLast(tail);
        // remove oldcell (head) to the front of the body
        body.removeFirst().setCellType(CellType.VOID);
        head = body.getFirst().modifyCell(CellType.BODY);
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
        return head; 
    }

    public Cell getTail() { 
        return head; 
    }

    public void setHead(Cell newhead) { 
        head.setCellType(CellType.VOID);
        head = newhead.modifyCell(CellType.BODY);;
    }

    public void setTail(Cell newtail) { 
        tail.setCellType(CellType.VOID);
        tail = newtail.modifyCell(CellType.BODY);
    }

    public Cell modifyHead(Cell newhead) {
        head.setCellType(CellType.VOID);
        head = newhead.modifyCell(CellType.BODY);
        return head;
    }

    public Cell modifyTail(Cell newtail) {
        tail.setCellType(CellType.VOID);
        tail = newtail.modifyCell(CellType.BODY);
        return tail;
    }

}