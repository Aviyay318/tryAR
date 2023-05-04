import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        this.setSize(600,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PanelOne(0,0,600,1000));

        this.setVisible(true);
    }




}
