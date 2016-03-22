import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Breakout extends JFrame implements Breakinterface {

    public Breakout(int fps, int speed) {
        
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
            try {
                Thread.sleep(15000);
            }
            catch(InterruptedException e) {
            }
        splash.close();
        boolean cheat=false;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to cheat and see what happens if you win?","Testing Features",dialogButton);
        if (dialogResult==1) {
            cheat = false;
        } else cheat =true;
        int period =1000/fps;
        int ballPeriod = 1000/speed;
        playSound();
        add(new Breakwindow(period,ballPeriod,cheat));
        setTitle("Breakout for CS 349");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT);
        setResizable(true);
        //setVisible(true);
        //toFront();
    }
    private void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
    }
}
public static void main (String args[]){
        
        if (args.length!= 2) {
            Breakout game = new Breakout(DEFAULT_FPS,DEFAULT_SPEED);
            game.setVisible(true);
        } else {
            int fps=0,speed=0;
            try {
            fps = Integer.parseInt(args[0]);
            speed = Integer.parseInt(args[1]);
            } catch (Exception e) {
                Breakout game = new Breakout(DEFAULT_FPS,DEFAULT_SPEED);
                game.setVisible(true);
            }
            if (fps < 35 || fps > 150) fps=DEFAULT_FPS;
            if (speed < 35 || speed > 200) speed=DEFAULT_SPEED;
            Breakout game = new Breakout(fps,speed);
            game.setVisible(true);
        }
         
    }

}