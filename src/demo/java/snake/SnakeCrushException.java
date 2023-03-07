package demo.java.snake;

import demo.java.cell.CellType;

public class SnakeCrushException extends SnakeException {
    
    public SnakeCrushException(CellType celltype) {
        super("Snake Crushed Against" + celltype);
    }

}