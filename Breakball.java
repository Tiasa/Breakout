import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
/**
 *
 * @author Tiasa
 */
public class Breakball implements Breakinterface {
    private Breakglobals global;
    private Breakpaddle paddle;
    private int xpos, ypos;
    private double moveRatex,moveRatey;
    private Image ball_image;
    public Breakball(Breakglobals glb,Breakpaddle pdle) {
        global = glb;
        paddle = pdle;
        ImageIcon ballii = new ImageIcon(getClass().getResource("ball.png"));
        ball_image = ballii.getImage();
        setInitialState();
    }
    private void setInitialState() {
        xpos = INIT_BALL_X;
        ypos = INIT_BALL_Y;
        moveRatex = global.SPEED;
        moveRatey = -global.SPEED;
    }
    public Image getImage() {
        return ball_image;
    }
    public int getXpos() {
        return xpos;
    }
    public int getYpos() {
        return ypos;
    }
    Rectangle getContainerBox() {
        return new Rectangle(xpos, ypos,global.BALL_WIDTH, global.BALL_HEIGHT);
    }
    public void move() {
        if (global.START_GAME) {
            if (this.getContainerBox().getMaxY() > global.GAME_HEIGHT-20) {
                global.LOST=true;
                return;
            }
        xpos += moveRatex;
        ypos += moveRatey;
        if (getContainerBox().intersects(paddle.getContainerBox())) {
            double ballCenterX = getContainerBox().getCenterX();
            double paddleCenterX = paddle.getContainerBox().getCenterX();
            double posX = (ballCenterX - paddleCenterX) / (global.PADDLE_WIDTH/2);
            double moveRateXY = Math.sqrt(moveRatex*moveRatex + moveRatey*moveRatey);
            moveRatex = moveRateXY * posX * INFLUENCE_PADDLE;
            moveRatey=Math.sqrt(moveRateXY*moveRateXY-moveRatex*moveRatex) * (moveRatey>0?-1:1);
            global.SCORE++;

        }
        if (xpos <= 0) {
            moveRatex = global.SPEED;
            
        }

        if (xpos >= global.GAME_WIDTH - global.BALL_WIDTH) {
            moveRatex = -global.SPEED;
            
        }

        if (ypos <= 0) {
            
            moveRatey=global.SPEED;
            
        }
        }
    }
    public void setMoveRateX(double speed) {
        moveRatex = speed;
    }
    public void setMoveRateY(double speed) {
        moveRatey = speed;
    }
}
