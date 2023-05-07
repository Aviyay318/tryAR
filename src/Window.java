import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final float GAME_HERTZ = 120.0f;
    private final double TIME_BETWEEN_UPDATES = 100000000/GAME_HERTZ;
    private final float TARGET_FPS = 120.0f;
    private final double TIME_BETWEEN_RENDERS = 100000000/TARGET_FPS;
    private final int MAX_UPDATES_BEFORE_RENDER = 5;
    private double lastUpdateTime;
    private double lastRenderTime;

    private Thread gameLoop;
    private boolean isRunning;

    private PanelOne panelOne;

    public Window(){
        //GridLayout gridLayout = new GridLayout(2,1);
        this.setSize(900,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panelOne = new PanelOne();
        this.add(this.panelOne);

        setupGameLoop();

        this.setVisible(true);

        this.isRunning=true;
        this.gameLoop.start();

    }

    public void setupGameLoop() {
        this.gameLoop = new Thread(()->{
            while(isRunning){
                long now = System.nanoTime();
                int updateCounter = 0;

                while(now - this.lastUpdateTime > TIME_BETWEEN_UPDATES && updateCounter < MAX_UPDATES_BEFORE_RENDER){
                    this.panelOne.repaint();
                    this.lastUpdateTime+=TIME_BETWEEN_UPDATES;
                    updateCounter++;
                }

                if (now-this.lastUpdateTime > TIME_BETWEEN_UPDATES){
                    this.lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                float interpolation = Math.min(1.0f, (float) ((now-this.lastUpdateTime)/TIME_BETWEEN_UPDATES));
                panelOne.render(interpolation);
                this.lastRenderTime = now;

                while(now - this.lastRenderTime < TIME_BETWEEN_RENDERS && now - this.lastUpdateTime < TIME_BETWEEN_UPDATES){
                    Thread.yield();
                    now = System.nanoTime();
                }
            }
        });
    }

}
