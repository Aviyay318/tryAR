import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelOne extends JPanel {
    private PanelTwo panelTwo;
    private Keyboard keyboard;
    private boolean keepGoing;
    private Sound sound;
    private JProgressBar jProgressBar;
    private int progressBarCounter;
    public PanelOne(int x,int y,int width,int height){
        this.keepGoing = true;

        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setBackground(Color.BLUE);

        this.panelTwo = new PanelTwo(PanelTwo.PANEL_TWO_WIDTH,PanelTwo.PANEL_TWO_HEIGHT, 150,100);
        this.add(panelTwo);
        this.panelTwo.setVisible(true);

        this.keyboard = new Keyboard();
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(this.keyboard);

        this.progressBarCounter = 0;

        checkKeyboardState();

        this.sound = new Sound();
        playMusic();


        this.setVisible(true);

    }


    public void addProgressBar(){
        this.jProgressBar = new JProgressBar();
        this.jProgressBar.setBounds(900/3-50,1100/3+100,400,200);
        this.jProgressBar.setStringPainted(true);
        this.jProgressBar.setFont(new Font("arial",Font.BOLD,20));
        this.jProgressBar.setBackground(new Color(238,177,255));
        this.jProgressBar.setForeground(Color.white);
        this.add(this.jProgressBar);
    }
    public void fillProgressBar(){
        new Thread(() -> {
            while(this.progressBarCounter <= 100){
                this.jProgressBar.setValue(this.progressBarCounter);
                try{
                    Thread.sleep(15);
                }catch (InterruptedException e){

                }
                this.progressBarCounter+=1;
            }
        }).start();
    }
    public void playMusic(){
        new Thread(()->{
            while(true){
                this.sound.loop();
            }
        }).start();
    }
    public void checkKeyboardState(){
        new Thread(()->{
            while(this.keepGoing){
                if (keyboard.isHide()){
                    try {
                        addProgressBar();
                        fillProgressBar();
                        Thread.sleep(2000);
                        panelTwo.setVisible(false);
                        this.jProgressBar.setVisible(false);
                        this.keepGoing = false;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        try {
            graphics.drawImage(ImageIO.read(new File("images/backGround2.png")),0,0,900,1000,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
