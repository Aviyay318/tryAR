import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    //private String score;
    private JLabel highScore;

    private JLabel moveInstructions;
    private JLabel shootInstructions;

    private BufferedImage crown;

    private Score score;

    public PanelTwo(int width,int height,int x, int y){

        this.setSize(width,height);
        this.setLocation(x,y);
        this.setLayout(null);
        this.setBackground(Color.white);

        addBlastLabel();
        addBallLabel();
        addCrown();

        addHighScore("");
        this.score = new Score();
        this.score.writeScore();

        this.setOpaque(true);
        this.setVisible(true);
    }

    public void addMoveInstructions(){

    }

    public void addShootInstructions(){

    }

    public void addCrown(){
        try {
            this.crown = ImageIO.read(new File("images/crown.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBlastLabel(){
        this.blast = new JLabel("Blast",JLabel.CENTER);
        this.blast.setBounds(PANEL_TWO_WIDTH/4,40,280,100);
        this.blast.setFont(new Font("arial",Font.BOLD,80));
        this.add(this.blast, BorderLayout.CENTER);
        this.blast.setVisible(true);
        this.blast.setForeground(new Color(238,177,255));


        this.smallBlast = new JLabel("Blast",JLabel.CENTER);
        this.smallBlast.setBounds(this.blast.getX()+3,this.blast.getY()+3,280,100);
        this.smallBlast.setFont(new Font("arial",Font.BOLD,80));
        this.add(this.smallBlast, BorderLayout.CENTER);
        this.smallBlast.setVisible(true);
        this.smallBlast.setForeground(new Color(166,183,204));
    }

    public void addBallLabel(){
        this.ball = new JLabel("Ball",JLabel.CENTER);
        this.ball.setBounds(this.blast.getX()+118,this.blast.getY()-40,100,100);
        this.ball.setFont(new Font("arial",Font.BOLD,45));
        this.add(this.ball, BorderLayout.CENTER);
        this.ball.setForeground(new Color(238,177,255));
        this.ball.setVisible(true);

        this.smallBall = new JLabel("Ball", JLabel.CENTER);
        this.smallBall.setBounds(this.ball.getX()+2,this.ball.getY()+2, 100,100);
        this.smallBall.setFont(new Font("arial",Font.BOLD,45));
        this.add(this.smallBall, BorderLayout.CENTER);
        this.smallBall.setForeground(new Color(166,183,204));
        this.smallBall.setVisible(true);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(this.crown,this.highScore.getX()-38,this.highScore.getY(),30,20,null);

    }
}
