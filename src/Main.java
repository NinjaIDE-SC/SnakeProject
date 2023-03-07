import demo.java.cell.CellBoardFrame;
import demo.java.snake.SnakeCellBoard;
import demo.java.snake.SnakeCellBoardPanel;

public class Main {

    public static void main(String[] arg) {

        SnakeCellBoard cellboard = new SnakeCellBoard(10,10);
        SnakeCellBoardPanel cellboardpanel = new SnakeCellBoardPanel(40, cellboard);
        CellBoardFrame<SnakeCellBoardPanel> cellboardframe = new CellBoardFrame<>("Snake Game", cellboardpanel);

    }

}