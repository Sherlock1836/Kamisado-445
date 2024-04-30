// documentation gather from https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioFormat.html 
// and https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioInputStream.html

package sfx;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AllSounds 
{
     public static void playMoveSound() {
          try {
               URL audioFile = AllSounds.class.getResource("move.wav");
               Clip clip = AudioSystem.getClip();
               AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
               clip.open(ais);
               clip.start();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public static void playIncorrectMoveSound()
     {
          try {
               URL audioFile = AllSounds.class.getResource("Incorrect_move.wav");
               Clip clip = AudioSystem.getClip();
               AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
               clip.open(ais);

               // Adjust volume
               FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
               gainControl.setValue(5.5f);

               clip.start();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public static void playWinSound() {
          try {
               URL winSound = AllSounds.class.getResource("mixkit-male-voice-cheer-2010.wav");
               Clip clip = AudioSystem.getClip();
               AudioInputStream ais = AudioSystem.getAudioInputStream(winSound);

               // this format specification is used specifically to fix the following error:
               // "line with format PCM_SIGNED 44100.0 Hz, 24 bit, stereo, 6 bytes/frame, little-endian not supported"
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
