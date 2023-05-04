import javax.swing.*;
import java.awt.*;

public class PanelOne extends JPanel {
    private PanelTwo panelTwo;
    private Keyboard keyboard;
    private boolean keepGoing;

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

        this.setVisible(true);

        checkKeyboardState();
    }

    public void checkKeyboardState(){
        new Thread(()->{
            while(this.keepGoing){
                if (keyboard.isHide()){
                    panelTwo.setVisible(false);
                    this.keepGoing = false;
                }
            }
        }).start();

    }
}
