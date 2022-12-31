package demo.java.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import demo.java.cell.CellBoardPanel;
import demo.java.direction.Direction;

public class SnakeCellBoardPanel extends CellBoardPanel<SnakeCellBoard> implements ActionListener {

    private boolean snakemove; // true ≈> snake moves, then snakestop is false
    private boolean snakestop; // true ≈> snake stops (pause), then snakemoves is false
    // snakemove & snakestop both false ≈> snake is dead ≈> game over
    private Direction snakedirection; // snake direction while moving
    private Direction snakeresumedirection; // snake direction before stopping stopping

    private int snakepoints; // food eaten 
    private Boolean snakeresult; // have you won (true) or have you lost (false)

    // if snake stops, then snake direction is none ≈> snakedirection = none
    // so... snakeresumedirection = previous snakedirection

    // game not ovet till one of snakemove or snakestop are true

    private int delay = 300; // standard 200
    private Timer timer;

    public SnakeCellBoardPanel(int unit, SnakeCellBoard snakecellboard) {
        super(unit, snakecellboard);
        addKeyListener(snakeKeyAdapter());
        start();
    }

    private void start() { // a snake is born ... the game of snakelife starts!
        snakedirection = getCellBoard().getDirection(); 
        snakeresumedirection = Direction.none;

        snakemove = true;   
        snakestop = false; 

        snakeresult = null;
        snakepoints = 0;

        timer = new Timer(delay, this);
        timer.start();
    }

    private void play(Direction direction) {
        int points = 0;
        try { 
            // play the game 
            points = getCellBoard().snakeGame(direction);
        } catch (SnakeStopException e) { 
            // game pause: you taking a pause
            snakemove = false;
            snakestop = true;
        } catch (SnakeException e) { 
            // game over: you lost or you won
            snakemove = false;
            snakestop = false;
            timer.stop();
            result();
        } finally {
            snakepoints += points;
        }
    }

    public void resume(Direction direction) {
        // game in pause till snakedirection matches snakeresumedirection
        if (snakedirection == snakeresumedirection) {
            snakestop = false;
            snakemove = true;
            snakeresumedirection = Direction.none;
        }
    }

    public void result() {
        // game result: 
        if(!snakemove && !snakestop) {
            if (getCellBoard().getSize() == getCellBoard().getSnakeSize()) {
                // the snake is as big as the board ≈> you win
                snakeresult = true;
            } else {
                // the snake smaller than the board ≈> you lost
                snakeresult = false;
            }
        } else {
            // something strange happened
            snakeresult = null;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(snakemove || snakestop) {
            super.draw(g);

            if(!getCellBoard().isFoodNull()) {
                drawOval(getCellBoard().getFood(), Color.magenta, g);
            }
            
            for(int s = 0; s < getCellBoard().getSnakeSize(); s++) {
                if(s == 0) {
                    drawRoundRect(getCellBoard().getSnakePart(s), Color.cyan, g);
                } else {
                    drawRoundRect(getCellBoard().getSnakePart(s), Color.blue, g);
                }     
            }

            if(snakestop) {
                g.setColor(Color.magenta);
                g.setFont(new Font("Ink Free", Font.BOLD, 60));
                FontMetrics fm = g.getFontMetrics();
                int xs = (getWidth() - fm.stringWidth("PAUSE"))/2;
                int ys = getHeight()/2 + fm.getAscent()/3;
                g.drawString("PAUSE", xs, ys);
            }

        } else {
            if(snakeresult) {
                drawGameOver("YOU WON", Color.magenta, g);
            } else {
                drawGameOver("YOU LOST", Color.magenta, g);
            }
        }

    }

    public void drawGameOver(String string, Color color, Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Ink Free", Font.BOLD, 60));
        FontMetrics fm = g.getFontMetrics();

        int ys = getHeight()/2 + fm.getAscent()/3;
        // game over
        int xs1 = (getWidth() - fm.stringWidth("GAME OVER"))/2;
        int ys1 = ys/2;
        g.drawString("GAME OVER", xs1, ys1);
        
        // you lost or you won
        int xs2 = (getWidth() - fm.stringWidth(string))/2;
        int ys2 = ys;
        g.drawString(string, xs2, ys2);

        // points scored
        int xs3 = (getWidth() - fm.stringWidth("Score: " + snakepoints))/2;
        int ys3 = ys1 + ys2;
        g.drawString("Score: " + snakepoints, xs3, ys3);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(snakemove) {
            play(snakedirection);
        }
        if(snakestop) {
            resume(snakedirection);
        }
        repaint();
    }

    public KeyAdapter snakeKeyAdapter() {
        KeyAdapter keyadapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                Direction direction = getCellBoard().getDirection();
                switch(ke.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if(direction != Direction.NORTH && direction != Direction.SOUTH) {
                            snakedirection = Direction.NORTH;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(direction != Direction.WEST && direction != Direction.EAST) {
                            snakedirection = Direction.WEST;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(direction != Direction.EAST && direction != Direction.WEST) {
                            snakedirection = Direction.EAST;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(direction != Direction.SOUTH && direction != Direction.NORTH) {
                            snakedirection = Direction.SOUTH;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(direction != Direction.none) {
                            snakedirection = Direction.none;
                            snakeresumedirection = direction;
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        return keyadapter;
    }

    // Alternative KeyAdapter...
    public KeyAdapter snakeAlternativeKeyAdapter() {
        KeyAdapter keyadapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                Direction direction = getCellBoard().getDirection();
                switch(ke.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if(direction != Direction.NORTH) {
                            snakedirection = Direction.NORTH;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(direction != Direction.WEST) {
                            snakedirection = Direction.WEST;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(direction != Direction.EAST) {
                            snakedirection = Direction.EAST;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(direction != Direction.SOUTH) {
                            snakedirection = Direction.SOUTH;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(direction != Direction.none) {
                            snakedirection = Direction.none;
                            snakeresumedirection = direction;
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        return keyadapter;
    }

}