/**
 *
 * @author Tiasa
 */
public class Breakglobals {
    public static int SCALEX = 1;
    public static int SCALEY = 1;
    public static int GAME_WIDTH = 900*SCALEX;
    public static int GAME_HEIGHT = 900*SCALEY;
    public static int PADDLE_WIDTH = 150*SCALEX;
    public static int PADDLE_HEIGHT = 25*SCALEY;
    public static int BLOCK_WIDTH = 80*SCALEX;
    public static int BLOCK_HEIGHT = 40*SCALEY;
    public static int BALL_WIDTH = 30*SCALEX;
    public static int BALL_HEIGHT = 30*SCALEY;
    public static int PADDLE_MAX_LEFT = 0;
    public static int PADDLE_MAX_RIGHT = GAME_WIDTH-PADDLE_WIDTH;
    public static int LEVEL=1;
    public static int SPEED = 2;
    public static int PERIOD = 10;
    public static int SCORE = 0;
    public static int BLOCK_DESTROYED = 0;
    public static boolean LOST = false;
    public static boolean WON = false;
    public static boolean START_GAME = false;
    public void refresh(int width,int height) {
            
            GAME_WIDTH=width;
           
            GAME_HEIGHT=height;
            
            SCALEX = (int) Math.ceil((double) GAME_WIDTH / 900);
            SCALEY = (int) Math.ceil((double) GAME_HEIGHT / 900);
            PADDLE_WIDTH = 150*SCALEX;
            PADDLE_HEIGHT = 25*SCALEX;
            BLOCK_WIDTH = 80*SCALEX;
            BLOCK_HEIGHT = 40*SCALEY;
            BALL_WIDTH = 30*SCALEX;
            BALL_HEIGHT = 30*SCALEX;
            PADDLE_MAX_LEFT = 0;
            PADDLE_MAX_RIGHT = GAME_WIDTH-PADDLE_WIDTH;  
    }
}
