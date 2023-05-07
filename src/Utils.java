public class Utils {

    private Keyboard keyboard;
    private boolean isHide;

    public Utils(){
        this.keyboard = new Keyboard();
        this.isHide = false;
    }

    public boolean checkKeyboardState(){
        new Thread(()->{
            while(true){
                if (keyboard.isHide()){
                    this.isHide = true;
                    break;
                }
            }
        }).start();
        return isHide;
    }
}
