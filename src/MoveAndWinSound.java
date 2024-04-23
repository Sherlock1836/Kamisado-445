import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MoveAndWinSound 
{
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

     public static void playWinSound() {
          try {
               File winSound = new File("mixkit-male-voice-cheer-2010.wav");
               Clip clip = AudioSystem.getClip();
               AudioInputStream ais = AudioSystem.getAudioInputStream(winSound);

               // this format specification is used to fix the following error:
               // "line with format PCM_SIGNED 44100.0 Hz, 24 bit,
               // stereo, 6 bytes/frame, little-endian not supported"
               AudioFormat baseFormat = ais.getFormat();
               AudioFormat targetFormat = new AudioFormat(
                         AudioFormat.Encoding.PCM_SIGNED,
                         baseFormat.getSampleRate(),
                         16, // Set to 16 bits per sample
                         baseFormat.getChannels(),
                         baseFormat.getChannels() * 2, // Calculate frame size
                         baseFormat.getSampleRate(),
                         false); // Set to little endian

               AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(targetFormat, ais);
               clip.open(audioInputStream);
               clip.start();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
