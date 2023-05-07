import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanelOne extends JPanel {

    public static final int PANEL_ONE_WIDTH = 900;
    public static final int PANEL_ONE_HEIGHT = 1000;

    private Sound music;

    private PanelTwo panelTwo;
    private Keyboard keyboard;
//    private boolean keepGoing;
//
//    private JProgressBar jProgressBar;
//    private int progressBarCounter;

    private ArrayList<Ball> balls;
    private float interpolation;

    public PanelOne(){
        //this.keepGoing = true;
        this.setBounds(0,0,PANEL_ONE_WIDTH, PANEL_ONE_HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setIgnoreRepaint(true);

        this.panelTwo = new PanelTwo(PanelTwo.PANEL_TWO_WIDTH,PanelTwo.PANEL_TWO_HEIGHT, 150,100);
        this.add(panelTwo);
        this.panelTwo.setVisible(true);

        this.music = new Sound();
        //playMusic();

        this.keyboard = new Keyboard();
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(this.keyboard);
        checkKeyboardState();

        this.balls = new ArrayList<>();

        this.setVisible(true);
    }
    public void playMusic(){
        new Thread(()->{
            while(!music.isTransitionDone()){
                this.music.loop();
            }
            //this.sound.loop();
        }).start();
    }

    public void checkKeyboardState(){
        new Thread(()->{
            while(true){
                if (keyboard.isHide()){
                    try {
                        System.out.println("shit");
                        Thread.sleep(2000);
                        System.out.println("shit2");
                        this.panelTwo.setVisible(false);
                        createBalls();
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void createBalls(){
        for(int i=0; i<3; i++){
            balls.add(new Ball());
        }
    }
    public void drawBalls(Graphics2D graphics2D){
        for (Ball ball: balls) {
            ball.render(graphics2D,this.interpolation);
            ball.move();
            if (keyboard.isHit()){
                ball.destroy();
            }
        }
    }
    public void render(float interpolation){
        this.interpolation = interpolation;
        this.repaint();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        try {
            graphics2D.drawImage(ImageIO.read(new File("images/backGround2.png")),0,0,900,1000,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        drawBalls(graphics2D);
    }

}
