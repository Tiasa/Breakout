import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author Tiasa
 */

public class Breakwindow extends JPanel implements Breakinterface {
    private Breakglobals global;
    private Breakpaddle paddle;
    private Breakball ball;
    private Breakblock blocks[];
    private boolean cheat=false;
    private int period,speed;
    private Timer timer,timer2;
    private boolean doOnce=true;
    public Breakwindow(int period,int speed,boolean cheat) {
        addKeyListener(new PaddleController());
        addMouseListener(new SpeedController());
        addComponentListener(new ResizeController());
        setFocusable(true);
        setDoubleBuffered(true);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        this.period=period;
        this.speed=speed;
        this.cheat=cheat;
        // For frame rate
        timer = new Timer();
        timer.scheduleAtFixedRate(new DrawFrame(), DELAY, this.period);
        timer2 = new Timer();
        timer2.scheduleAtFixedRate(new MoveBall(), DELAY, this.speed);
    }
    private void restart() {
        this.removeAll();
        revalidate();
        global = new Breakglobals();
        paddle = new Breakpaddle(global);
        ball = new Breakball(global,paddle);
        blocks = new Breakblock[N_OF_BLOCKS];
        initBricks();
        global.LOST=false;
        global.WON=false;
        global.SCORE = 0;
        global.BLOCK_DESTROYED = 0;
        doOnce=true;
    }
    private void initialize() {
           
        global = new Breakglobals();
        paddle = new Breakpaddle(global);
        ball = new Breakball(global,paddle);
        blocks = new Breakblock[N_OF_BLOCKS];
        initBricks();
    }
    private void initBricks() {
        if (global.LEVEL==1) {
            int k = 0;
            for (int i = 0; i < N_OF_ROWS; i++) {
                for (int j = 0; j < N_OF_BLOCK_PER_ROW; j++) {
                    blocks[k] = new Breakblock(j * global.BLOCK_WIDTH + 30, i * global.BLOCK_HEIGHT + 50,global,ball,j%2);
                    k++;
                }
            }
        } else {
            int k = 0;
            for (int i = 0; i < N_OF_ROWS; i++) {
                for (int j = 0; j < N_OF_BLOCK_PER_ROW; j++) {
                    blocks[k] = new Breakblock(j * global.BLOCK_WIDTH + 30, i * global.BLOCK_HEIGHT + 50,global,ball,(i==0 || j==0 || (i+1==N_OF_ROWS) ||(j+1==N_OF_BLOCK_PER_ROW))?2:1 );
                    k++;
                }
            }
        }   
    }
    @Override
    public void addNotify() {
        super.addNotify();
        initialize();
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (global.LOST) {
            drawEndGameLost(g2d);
        } else if ((global.BLOCK_DESTROYED==N_OF_BLOCKS) || (cheat && global.BLOCK_DESTROYED>=3)) {
            global.WON = true;
            drawEndGameWon(g2d);
        }
        else {
            drawComponent(g2d);
        }
    }
    private void drawComponent(Graphics2D g2d) {
        g2d.drawImage(paddle.getImage(),paddle.getXpos(),paddle.getYpos(),global.PADDLE_WIDTH,global.PADDLE_HEIGHT,this);
        g2d.drawImage(ball.getImage(),ball.getXpos(),ball.getYpos(),global.BALL_WIDTH,global.BALL_HEIGHT,this); 
        for (int i = 0; i < N_OF_BLOCKS; i++) {
            if (!blocks[i].isDestroyed()) {
                g2d.drawImage(blocks[i].getImage(), blocks[i].getXpos(),
                        blocks[i].getYpos(), global.BLOCK_WIDTH,
                        global.BLOCK_HEIGHT, this);
            }
        }
        g2d.setFont(new Font("TimesRoman",Font.BOLD,20));
        g2d.drawString("Score: "+global.SCORE, 20, 675);
    }
    private void drawEndGameLost(Graphics2D g2d) {
        if (doOnce) {
        global.START_GAME = false;
        this.setLayout(null);
        JButton replay = new JButton("Replay");
        replay.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
                restart();
            }
        });
        replay.setBounds(400, 500, 100, 50);
        this.add(replay);
        this.revalidate();
        doOnce=false;
        }
        g2d.drawImage(new ImageIcon(getClass().getResource("lost.png")).getImage(),0,0,global.GAME_WIDTH,global.GAME_HEIGHT,this);
        g2d.setFont(new Font("Fixedsys",Font.BOLD,30));
        g2d.setColor(Color.pink);
        g2d.drawString("Your score is: "+global.SCORE, 350, 300);
    }
    private void drawEndGameWon(Graphics2D g2d) {
        if (doOnce) {
        global.START_GAME = false;
        this.setLayout(null);
        JButton levelup = new JButton("Next Level");
        levelup.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                global.LEVEL++;
                restart();
            }
        });
        levelup.setBounds(200, 500, 100, 50);
        this.add(levelup);
        JButton replay = new JButton("Replay");
        replay.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        replay.setBounds(600, 500, 100, 50);
        this.add(replay);
        this.revalidate();
        doOnce=false;
        }
        g2d.drawImage(new ImageIcon(getClass().getResource("won.png")).getImage(),0,0,global.GAME_WIDTH,global.GAME_HEIGHT,this);
    }
    private class DrawFrame extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
    private class MoveBall extends TimerTask {
        @Override
        public void run() {
            ball.move();
        }
    }
    private class PaddleController extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!global.WON && !global.LOST) {
            if (!global.START_GAME) global.START_GAME = true;
            paddle.keyPressed(e);
            }
        }
    }
    private class ResizeController extends ComponentAdapter {
    
        public void componentResized(ComponentEvent e) {
            global.refresh(e.getComponent().getWidth(),e.getComponent().getHeight());
        }
    }
    private class SpeedController extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1)
	    {
	      if (global.SPEED > 1 ) {
                  global.SPEED --;
              }
	    }	    
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
	     if (global.SPEED < 6 ) {
                  global.SPEED ++;
              }
	    }
        }
    }

}
