import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PanelTwo extends JPanel {

    public static final int PANEL_TWO_WIDTH = 600;
    public static final int PANEL_TWO_HEIGHT = 800;

    private JLabel blast;
    private JLabel smallBlast;
    private JLabel ball;
    private JLabel smallBall;
    private JLabel highScore;
    private JLabel moveInstructions;
    private JLabel shootInstructions;

    private BufferedImage crown;
    private BufferedImage [] left;
    private BufferedImage [] right;
    private BufferedImage [] space;

    private Score score;
    private int index;

    public PanelTwo(int width,int height,int x, int y){
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setLayout(null);
        this.setBackground(Color.white);

        addBlastLabel();
        addBallLabel();
        addCrown();
        addMoveInstructions();
        addShootInstructions();
        loadImages();
        this.index = 0;

        this.score = new Score();
        this.score.writeScore();
        addHighScore(this.score.addScore());

        this.setOpaque(false);
        this.setVisible(true);



    }


    public void addBlastLabel(){
        this.blast = new JLabel("Blast",JLabel.CENTER);
        this.blast.setBounds(PANEL_TWO_WIDTH/4,40,280,100);
        this.blast.setFont(new Font("arial",Font.BOLD,100));
        this.add(this.blast, BorderLayout.CENTER);
        this.blast.setVisible(true);
        this.blast.setForeground(new Color(238,177,255));


        this.smallBlast = new JLabel("Blast",JLabel.CENTER);
        this.smallBlast.setBounds(this.blast.getX()+3,this.blast.getY()+3,280,100);
        this.smallBlast.setFont(new Font("arial",Font.BOLD,100));
        this.add(this.smallBlast, BorderLayout.CENTER);
        this.smallBlast.setVisible(true);
        this.smallBlast.setForeground(new Color(166,183,204));
    }

    public void addBallLabel(){
        this.ball = new JLabel("Ball",JLabel.CENTER);
        this.ball.setBounds(this.blast.getX()+118,this.blast.getY()-50,120,100);
        this.ball.setFont(new Font("arial",Font.BOLD,58));
        this.add(this.ball, BorderLayout.CENTER);
        this.ball.setForeground(new Color(238,177,255));
        this.ball.setVisible(true);

        this.smallBall = new JLabel("Ball", JLabel.CENTER);
        this.smallBall.setBounds(this.ball.getX()+2,this.ball.getY()+2, 120,100);
        this.smallBall.setFont(new Font("arial",Font.BOLD,58));
        this.add(this.smallBall, BorderLayout.CENTER);
        this.smallBall.setForeground(new Color(166,183,204));
        this.smallBall.setVisible(true);
    }

    public void addCrown(){
        try {
            this.crown = ImageIO.read(new File("images/crown.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addHighScore(String score){
        this.highScore = new JLabel("",JLabel.LEFT);
        this.highScore.setText(score);
        this.highScore.setBounds(this.blast.getX()+170,this.blast.getY()+120,100,20);
        this.highScore.setFont(new Font("arial",Font.BOLD,20));
        this.add(this.highScore, BorderLayout.CENTER);
        this.highScore.setVisible(true);
        this.highScore.setForeground(new Color(238,177,255));
    }

    public void addMoveInstructions(){
        this.moveInstructions = new JLabel("To move PRESS:",JLabel.LEFT);
        this.moveInstructions.setBounds(this.blast.getX()+25,this.blast.getY()+160,300,100);
        this.moveInstructions.setFont(new Font("arial",Font.BOLD,30));
        this.add(this.moveInstructions, BorderLayout.CENTER);
        this.moveInstructions.setVisible(true);
        this.moveInstructions.setForeground(new Color(238,177,255));
    }

    public void addShootInstructions(){
        this.shootInstructions = new JLabel("To shoot PRESS:",JLabel.LEFT);
        this.shootInstructions.setBounds(this.moveInstructions.getX(),this.moveInstructions.getY()+100,300,100);
        this.shootInstructions.setFont(new Font("arial",Font.BOLD,30));
        this.add(this.shootInstructions, BorderLayout.CENTER);
        this.shootInstructions.setVisible(true);
        this.shootInstructions.setForeground(new Color(238,177,255));
    }

    private void loadImages(){
        final int SIZE = 2;
        try {
            this.right = new BufferedImage[]{ImageIO.read(new File("images/instructions/rightUnPressed.png")), ImageIO.read(new File("images/instructions/rightPressed.png"))};
            this.left = new BufferedImage[]{ImageIO.read(new File("images/instructions/leftUnPressed.png")), ImageIO.read(new File("images/instructions/leftPressed.png"))};
            this.space = new BufferedImage[]{ImageIO.read(new File("images/instructions/spaceUnPressed.png")), ImageIO.read(new File("images/instructions/spacePressed.png"))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void keysAnimation(Graphics2D graphics2D, BufferedImage[] images, int x, int y,int width,int height){
        graphics2D.drawImage(images[this.index],x,y,width,height,null);
        if(this.index==0){
            this.index=1;
        }else {
            this.index=0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(this.crown,this.highScore.getX()-38,this.highScore.getY(),30,20,null);
        keysAnimation(graphics2D,this.left,this.moveInstructions.getX()+70,this.moveInstructions.getY()+70,50,50);
        keysAnimation(graphics2D,this.right,this.moveInstructions.getX()+130,this.moveInstructions.getY()+70,50,50);
        keysAnimation(graphics2D,this.space,this.shootInstructions.getX()+25,this.shootInstructions.getY()+70,200,40);


    }
}
