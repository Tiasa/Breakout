import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
/**
 *
 * @author Tiasa
 */
public class Breakpaddle implements Breakinterface{
    private Breakglobals global;
    private int xpos,ypos;
    private Image paddle_image;
    
    public Breakpaddle (Breakglobals glb) {
        global=glb;
        ImageIcon paddleii = new ImageIcon(getClass().getResource("paddle.png"));
        paddle_image = paddleii.getImage();
        setInitialState();
    }
    private void setInitialState() {
        xpos = INIT_PADDLE_X;
        ypos = INIT_PADDLE_Y;
    }
    public Image getImage() {
        return paddle_image;
    }
    public int getXpos() {
        return xpos;
    }
    public int getYpos() {
        return ypos;
    }
    Rectangle getContainerBox() {
        return new Rectangle(xpos, ypos,global.PADDLE_WIDTH, global.PADDLE_HEIGHT);
    }
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (xpos-(global.SPEED*20) > global.PADDLE_MAX_LEFT) {
                xpos -= global.SPEED*20;
            } else {
                xpos=global.PADDLE_MAX_LEFT;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (xpos+(global.SPEED*20) < global.PADDLE_MAX_RIGHT) {
                xpos += global.SPEED*20;
            } else {
                xpos = global.PADDLE_MAX_RIGHT;
            }
        }
    }
    
}
