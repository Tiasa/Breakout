import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
/**
 *
 * @author Tiasa
 */
public class Breakblock implements Breakinterface {
    private Breakball ball;
    private Breakglobals global;
    private int xpos,ypos;
    private boolean destroyed;
    private Image block_image;
    private int hit;
    public Breakblock(int x, int y,Breakglobals glb,Breakball bl,int imageNo) {
        ball = bl;
        global = glb;
        ImageIcon blockii;
        switch (imageNo) {
            case 1:
                blockii= new ImageIcon(getClass().getResource("block1.png"));
                break;
            case 2:
                blockii= new ImageIcon(getClass().getResource("block2.png"));
                break;
            default:
                blockii= new ImageIcon(getClass().getResource("block3.png"));
                break;
        }
        block_image = blockii.getImage();
        hit=0;
        xpos = x;
        ypos = y;
        destroyed = false;
    }
    public Image getImage() {
        return block_image;
    }
    public int getXpos() {
        return xpos*global.SCALEX;
    }
    public int getYpos() {
        return ypos*global.SCALEY;
    }
    Rectangle getContainerBox() {
        return new Rectangle(xpos*global.SCALEX, ypos*global.SCALEY,global.BLOCK_WIDTH, global.BLOCK_HEIGHT);
    }
    public boolean isDestroyed() {
        if (destroyed) {
            return true;
        } else {
            if (ball.getContainerBox().intersects(getContainerBox())) {
                if (ball.getYpos() <= getYpos()-(global.BLOCK_HEIGHT/2)) {
                    ball.setMoveRateY(-global.SPEED);
                }
                if (ball.getYpos()>=getYpos()+(global.BLOCK_HEIGHT/2)) {
                    ball.setMoveRateY(global.SPEED);
                }
                if (ball.getXpos() < getXpos()) {
                    ball.setMoveRateX(global.SPEED);
                }
                if (ball.getXpos() > getXpos()) {
                    ball.setMoveRateX(-global.SPEED);
                }
                
                global.SCORE++;
                hit++;
                if (hit==global.LEVEL) {
                    destroyed = true;
                    global.BLOCK_DESTROYED++;
                    return true;
                } else {
                    return false;
                }
                
            }
            return false;
        }
    }
    
}
