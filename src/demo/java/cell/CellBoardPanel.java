package demo.java.cell;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class CellBoardPanel<CB extends CellBoard> extends JPanel {

    private int unit;
    private CB cellboard;

    public CellBoardPanel(int unit, CB cellboard) {

        this.unit = unit;
        this.cellboard = cellboard;

        setFocusable(true);

        setPreferredSize(new Dimension(getWidth(), getHeight()));
        
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setMaximumSize(new Dimension(getWidth(), getHeight()));

        setBackground(Color.black);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        drawGrid(Color.gray, g);
    }

    public void drawGrid(Color color, Graphics g) {
        g.setColor(color); 
        for(int i = 0; i < cellboard.getYB() + 1; i++) {
            g.drawLine(0, i * unit, unit * cellboard.getXB(), i * unit);
        }
        for(int j = 0; j < cellboard.getXB() + 1; j++) {
            g.drawLine(j * unit, 0, j * unit, unit * cellboard.getYB());
        }
    }

    public void drawOval(Cell cell, Color color, Graphics g) {
        g.setColor(color);
        g.fillOval(cell.getX() * unit, cell.getY() * unit, unit, unit);
    }

    public void drawRect(Cell cell, Color color, Graphics g) {
        g.setColor(color);
        g.fillRect(cell.getX() * unit, cell.getY() * unit, unit, unit);
    }

    public void drawRoundRect(Cell cell, Color color, Graphics g) {
        g.setColor(color);
        g.fillRoundRect(cell.getX() * unit, cell.getY() * unit, unit, unit, (int) unit / 3, (int) unit / 3);
    }

    public int getUnit() {
        return unit;
    }

    public CB getCellBoard() {
        return cellboard;
    }

    public int getWidth() {
        return unit * cellboard.getXB();
    }

    public int getHeight() {
        return unit * cellboard.getYB();
    }

}