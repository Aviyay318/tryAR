import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean hide;
    public Keyboard(){
        this.hide = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT: {System.out.println("left"); this.hide=true;}
            case KeyEvent.VK_RIGHT: {System.out.println("right"); this.hide=true;}
            case KeyEvent.VK_SPACE: {System.out.println("space"); this.hide=true;}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT: {}
        }
    }

    public boolean isHide() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.hide;
    }
}
