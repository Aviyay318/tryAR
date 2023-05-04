import javax.swing.*;
import java.awt.*;

public class PanelOne extends JPanel {
    private PanelTwo panelTwo;
    private Keyboard keyboard;
    public PanelOne(int x,int y,int width,int height){
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setBackground(Color.BLUE);

        this.panelTwo = new PanelTwo(300,600, 150,100);
        this.add(panelTwo);
        this.panelTwo.setVisible(true);

        this.keyboard = new Keyboard();
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(this.keyboard);

        this.setVisible(true);

        checkKeyboardState();
    }

    public void checkKeyboardState(){
        boolean stop=false;
        new Thread(()->{
            while(!stop){
                if (keyboard.isHide()){
                    panelTwo.setVisible(false);
                }
            }
        }).start();

    }
}
