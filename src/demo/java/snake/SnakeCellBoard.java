package demo.java.snake;

import demo.java.cell.Cell;
import demo.java.cell.CellBoard;
import demo.java.cell.CellType;
import demo.java.direction.Direction;

public class SnakeCellBoard extends CellBoard {

    private final Snake snake;
    private Direction direction;
    private Cell food;

    public SnakeCellBoard(int xb, int yb, Cell snakecell, Cell foodcell, Direction direction) {
        super(xb, yb);
        this.snake = new Snake(snakecell);
        this.food = foodcell;
        this.direction = direction;
    }

    public SnakeCellBoard(int xb, int yb, int xs, int ys, int xf, int yf, Direction direction) {
        super(xb, yb);
        this.snake = new Snake(getCell(xs, ys));
        this.food = getCell(xf, yf);
        this.direction = direction;
    }

    public SnakeCellBoard(int xb, int yb) {
        super(xb, yb);
        this.snake = new Snake(setCenterBody());
        this.food = setRandomFood();
        this.direction = (Direction.none).otherRandomDirection();
    }

    private Cell setCenterBody() {
        return getCell((int) getXB()/2, (int) getYB()/2);
    }

    private Cell setRandomBody() {
        return setRandomCellType(CellType.BODY);
    }

    private Cell setRandomFood() {
        return setRandomCellType(CellType.FOOD);
    }

    public void setNewRandomFood() throws SnakeException {
        Cell nextfood;
        if ((getSize() - 1) <= getSnakeSize()) { 
            // snake is bigger than the board
            // we cannot find a new foodcell
            nextfood = null;
        } else {
            // snake is smaller than the board
            // we can find a new foodcell
            nextfood = setRandomFood();
        }
        // set the new foodcell
        food.setCellType(CellType.VOID);
        food = nextfood;
    }

    public int snakeGame(Direction direction) throws SnakeException {
        this.direction = direction;

        // two choices: to move or to stop, that's the dilemma!
        if(direction == Direction.none) { // snake stops

            // snake takes a pause
            throw new SnakeStopException();

        } else { // snake moves

            // get the nextcell...
            // two choices: board with bounds or board without bounds
            Cell nextcell = nextCellWithoutBounds(snake.getHead(), direction);
            // Cell nextcell = nextCellWithBounds(snake.getHead(), direction);
            
            // get the CellType of the nextcell...
            CellType nextcellCellType;
            if(nextcell == null) {
                nextcellCellType = CellType.WALL; // nextcell is a out-of-bounds
            } else {
                nextcellCellType = nextcell.getCellType();
            }

            // return the points...
            if(nextcellCellType == CellType.VOID) {
                // snake moves up to a cell
                snake.bodyMoves(nextcell);  // snake can move, so bodymoves
                return 0; // only moving

            } else if(nextcellCellType == CellType.FOOD) {
                // snake moves up to a foodcell
                setNewRandomFood();         // set next foodcell randomly
                snake.bodyGrows();          // snake can eat, so bodygrows
                snake.bodyMoves(nextcell);  // snake can move, so bodymoves
                return 1; // eating and moving 

            } else {
                // snake crushes agaist wall or itself
                throw new SnakeCrushException(nextcellCellType);

            }

        }
    }

    private Cell nextCellWithBounds(Cell cell, Direction direction) {
        // cellboard max and min are like bounds (WALLS)
        int x = cell.getX();
        int y = cell.getY();
        switch(direction) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            case SOUTH:
                y++;
                break;
            default:
                break;
        }
        return getCell(x, y);
    }

    private Cell nextCellWithoutBounds(Cell cell, Direction direction) {
        // cellboard max and min are not like teleportation points
        int x = cell.getX();
        int y = cell.getY();
        int xBound = getXB() - 1;
        int yBound = getYB() - 1;
        switch(direction) {
            case NORTH:
                y--;
                if(y < 0) y = yBound;
                break;
            case EAST:
                x++;
                if(x > xBound) x = 0;
                break;
            case WEST:
                x--;
                if(x < 0) x = xBound;
                break;
            case SOUTH:
                y++;
                if(y > yBound) y = 0;
                break;
            default:
                break;
        }
        return getCell(x, y);
    }


    public Snake getSnake() {
        return snake;
    }

    public int getSnakeSize() {
        return snake.getBodySize();
    }

    public Cell getSnakePart(int s) {
        return snake.getBodyPart(s);
    }

    public Direction getDirection() {
        return direction;
    }

    public Cell getFood() {
        return food;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setFood(Cell food) {
        this.food = food;
    }

    public boolean isFoodNull() {
        return food == null;
    }

    public boolean isDirectionNull() {
        return direction == null;
    }

}