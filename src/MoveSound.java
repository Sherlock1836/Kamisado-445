import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MoveSound {
     public static void playMoveSound() {
          try {
               File audioFile = new File("move.wav");
               Clip clip = AudioSystem.getClip();
               AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
               clip.open(ais);
               clip.start();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
