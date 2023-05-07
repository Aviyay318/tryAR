import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ball extends JLabel {
    static Random random = new Random();
    public final int GRASS_HEIGHT = 720;
    private final int LEFT_POSITION = 0;
    private final int RIGHT_POSITION = 800;
    public final int WIDTH_HEIGHT_DEFAULT = random.nextInt(50,70);

    private int defaultXPosition;
    private int defaultYPosition;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private int speed;
    private int health;
    private BufferedImage ball;

    public Ball(){
        createXPosition();
        this.defaultYPosition = random.nextInt(0,100);
        this.xPosition = 0;
        this.yPosition = 0;

        this.width = WIDTH_HEIGHT_DEFAULT;
        this.height = WIDTH_HEIGHT_DEFAULT;

        this.speed = 2;

        this.setText("4");
        this.setBounds(this.defaultXPosition,this.defaultYPosition,this.width,this.height);
        this.setVisible(true);
        this.health = random.nextInt(1,30);
        try {
            this.ball = ImageIO.read(new File("images/balls/red.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createXPosition(){
        int state = random.nextInt(1,3);
        switch (state){
            case 1 -> this.defaultXPosition = LEFT_POSITION;
            case 2 -> this.defaultXPosition = RIGHT_POSITION;
        }
    }

    private synchronized void moveUp(){
        while(this.yPosition>0){
            try {
                Thread.sleep(25);
                this.yPosition -=5;
                LeftRightMovement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void moveDown(){
        while(this.yPosition <GRASS_HEIGHT){//this.y = 0-100, height = 1000
            try {
                Thread.sleep(25);
                this.yPosition +=5;
                LeftRightMovement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void LeftRightMovement() {
        switch(this.defaultXPosition){
            case LEFT_POSITION -> {
                this.xPosition += this.speed; //x = 2
                if (this.xPosition < 0 || this.xPosition >800){
                    this.speed *= -1;
                }
            }
            case RIGHT_POSITION -> {
                this.xPosition -= this.speed;
                //System.out.println(defaultXPosition + this.xPosition);// x = -2 -4 -6 -8
                if (defaultXPosition+this.xPosition <= 0 || defaultXPosition+this.xPosition >= 800){//this.x = -2 > 0 && x = -2+800=798 < 800?
                    this.speed *= -1;

                }
            }
        }
    }

    public void move(){
        new Thread(() -> {
            moveUp();
            moveDown();
        }).start();
    }

    public void destroy(){
        if (health == 0) {
            this.width = 0;
            this.height = 0;
        }
    }

    public void render(Graphics2D graphics2D, float interpolation){
        graphics2D.drawImage(this.ball, ((int)((this.defaultXPosition + this.xPosition) + interpolation)), ((int) (this.defaultYPosition + this.yPosition+interpolation)),this.width,this.height,null);
    }

}
