import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        GridLayout gridLayout = new GridLayout(2,1);
        this.setSize(900,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PanelOne(0,0,600,1000));

        this.setVisible(true);
    }




}
