import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip clip;
    private AudioInputStream intro;
    private AudioInputStream transition;
    private AudioInputStream during;
    private boolean isTransitionAvailable;
    private String state;
    private Keyboard keyboard;

    public Sound(){
        try {
            this.clip = AudioSystem.getClip();
            this.intro = AudioSystem.getAudioInputStream(new File("music/ballBlastIntro.wav"));
            this.transition = AudioSystem.getAudioInputStream(new File("music/ballBlastTransition.wav"));
            this.during = AudioSystem.getAudioInputStream(new File("music/ballBlastDuringGame.wav"));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }

        this.state = "intro";
        this.keyboard = new Keyboard();
        System.out.println(this.keyboard.isHide());
        this.isTransitionAvailable=false;
        checkTransitionAvailability();
        loadClip();
    }

    public void isHidingInvoked(){
        if (keyboard.isHide()){
            this.isTransitionAvailable = true;
        }
    }

    public void checkTransitionAvailability(){
        new Thread(()->{
            while (true){
                isHidingInvoked();
                if(this.isTransitionAvailable) {
                    setState();
                    break;
                }
            }
        }).start();
    }

    public void setState(){
        close();
        this.state = "transition";
        loadClip();
        long i = System.currentTimeMillis();
        try {
            Thread.sleep(2329);
            long j = System.currentTimeMillis();
            System.out.println(j-i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.state = "during";
        close();
        loadClip();
    }

    public void loadClip(){
        try{
            switch(this.state){
                case "intro" -> {
                    this.clip.open(this.intro);
                }
                case "transition" -> {
                    this.clip.open(this.transition);
                }
                case "during" -> {
                    this.clip.open(this.during);
                }
            }
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void start(){
        this.clip.setMicrosecondPosition(0);
        this.clip.start();
    }
    public void loop(){
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void close(){
        clip.close();
    }
}
