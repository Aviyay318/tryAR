import javax.swing.*;
import java.awt.*;

public class PanelTwo extends JPanel {
    private JLabel blast;
    private JLabel smallBlast;
    private JLabel ball;
    private JLabel smallBall;

    public PanelTwo(int width,int height,int x, int y){

        this.setSize(width,height);
        this.setLocation(x,y);
        this.setLayout(null);
        this.setBackground(Color.white);

        addBlastLabel();
        addBallLabel();

        this.setOpaque(true);
        this.setVisible(true);
    }

    public void addBlastLabel(){
        this.blast = new JLabel("Blast",JLabel.CENTER);
        this.blast.setBounds(10,10,280,100);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawRect(30,100,100,100);
//        graphics2D.drawRect(60,130,100,100);
//        graphics2D.drawLine(60,130,100,100);


    }
}
