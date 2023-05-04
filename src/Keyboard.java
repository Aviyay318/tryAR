import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private static boolean hide=false;
    public Keyboard(){
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> {System.out.println("left"); hide=true;}
            case KeyEvent.VK_RIGHT -> {System.out.println("right"); hide=true;}
            case KeyEvent.VK_SPACE -> {System.out.println("space"); hide=true;}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean isHide() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return hide;
    }
}
