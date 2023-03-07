package demo.java.cell;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Container;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;

public class CellBoardFrame<CBP extends CellBoardPanel<?>> extends JFrame {

    private static final int margin = 10;

    private int width;
    private int height;

    private Border b;

    private Container c;

    private JPanel panelc;

    private CBP cellboardpanel;

    public CellBoardFrame(String title, CBP cellboardpanel) {

        setTitle(title);

        this.cellboardpanel = cellboardpanel; 
        width = cellboardpanel.getUnit() * cellboardpanel.getCellBoard().getXB() + margin*2;
        height = cellboardpanel.getUnit() * cellboardpanel.getCellBoard().getYB() + margin*2;

        panelc = new JPanel();
        panelc.setPreferredSize(new Dimension(width, height));

        setLocationRelativeTo(null);

        c = getContentPane();

        b = new LineBorder(Color.gray, margin, true);

        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        panelc.setLayout(new BoxLayout(panelc, BoxLayout.X_AXIS));

        //c.add(Box.createRigidArea(new Dimension(0, margin)));
        c.add(panelc);
        //c.add(Box.createRigidArea(new Dimension(0, margin)));

        panelc.setBorder(b);

        cellboardpanel.setAlignmentX(CENTER_ALIGNMENT);
        panelc.add(cellboardpanel);

        c.setBackground(Color.white);
        panelc.setBackground(Color.white);

        pack();

        // this.setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}