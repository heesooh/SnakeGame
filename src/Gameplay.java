import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private ImageIcon titleImage;
    private ImageIcon snakeImage;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean pause = false;

    private Timer timer;
    private int delay = 100;

    private int snakelength = 3;
    private int trigger = 0;

    private int[] orangexpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
            475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] orangeypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
            475, 500, 525, 550, 575, 600, 625};

    private ImageIcon orange;
    private int scores;
    private Random random = new Random();
    //34 is the total position of x
    private int xpos = random.nextInt(34);
    //23 is the total position of y
    private int ypos = random.nextInt(23);


    public Gameplay() {
        addKeyListener(this);
        //a component that is not focusable can not gain the focus
        setFocusable(true);
        //decides whether or not focus traversal keys are allowed to be used when the component has focus
        //traversal keys: Tab key, shift + Tab, etc
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {

        if (trigger == 0) {
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;
        }
        //draw image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        //draw the title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //draw boarder
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        //draw background
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        //draw score board
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString("Scores: " + scores, 780, 30);

        //draw the length of the snake
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString("Length: " + snakelength, 780, 50);


        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

        for (int body = 0; body < snakelength; body++) {
            if (body == 0 && right) {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[body], snakeylength[body]);
            }
            if (body == 0 && left) {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[body], snakeylength[body]);
            }
            if (body == 0 && up) {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[body], snakeylength[body]);
            }
            if (body == 0 && down) {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[body], snakeylength[body]);
            }

            if (body != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakexlength[body], snakeylength[body]);
            }
        }

        orange = new ImageIcon("enemy.png");

        if ((orangexpos[xpos] == snakexlength[0]) && (orangeypos[ypos] == snakeylength[0])) {
            snakelength++;
            scores++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        orange.paintIcon(this, g, orangexpos[xpos], orangeypos[ypos]);

        for (int b = 1; b < snakelength; b++) {
            if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Press R to Restart", 350, 340);
            }
        }

        //causes the JFrame window to be destroyed and cleaned up by the operating system.
        g.dispose();
    }

    //Automatically called when the timer begins
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (right) {
            for (int r = snakelength - 1; r >= 0; r--) {
                snakeylength[r + 1] = snakeylength[r];
            }
            for (int r = snakelength - 1; r >= 0; r--) {

                if (r == 0) {
                    snakexlength[r] = snakexlength[r] + 25;
                } else {
                    snakexlength[r] = snakexlength[r - 1];
                }
                if (snakexlength[r] > 850) {
                    snakexlength[r] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int r = snakelength - 1; r >= 0; r--) {
                snakeylength[r + 1] = snakeylength[r];
            }
            for (int r = snakelength - 1; r >= 0; r--) {
                if (r == 0) {
                    snakexlength[r] = snakexlength[r] - 25;
                } else {
                    snakexlength[r] = snakexlength[r - 1];
                }
                if (snakexlength[r] < 25) {
                    snakexlength[r] = 850;
                }
            }

            repaint();

        }
        if (up) {
            for (int r = snakelength - 1; r >= 0; r--) {
                snakexlength[r + 1] = snakexlength[r];
            }
            for (int r = snakelength - 1; r >= 0; r--) {
                if (r == 0) {
                    snakeylength[r] = snakeylength[r] - 25;
                } else {
                    snakeylength[r] = snakeylength[r - 1];
                }
                if (snakeylength[r] < 75) {
                    snakeylength[r] = 625;
                }
            }

            repaint();

        }
        if (down) {
            for (int r = snakelength - 1; r >= 0; r--) {
                snakexlength[r + 1] = snakexlength[r];
            }
            for (int r = snakelength - 1; r >= 0; r--) {
                if (r == 0) {
                    snakeylength[r] = snakeylength[r] + 25;
                } else {
                    snakeylength[r] = snakeylength[r - 1];
                }
                if (snakeylength[r] > 625) {
                    snakeylength[r] = 75;
                }
            }

        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (pause) {
                timer.restart();
                pause = !pause;
            } else {
                timer.stop();
                pause = !pause;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            trigger = 0;
            snakelength = 3;
            scores = 0;
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            trigger++;
            right = true;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            trigger++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            trigger++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            trigger++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
