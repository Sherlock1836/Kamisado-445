package music;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.SwingUtilities;

// found this class at https://stackoverflow.com/questions/16867976/how-do-you-add-music-to-a-jframe

public class BackgroundMusic {
    private static Clip clip;

    public static void playBackgroundMusic()
    {
        try{
            File audioFile = new File("src/music/Nujabes-Mystline-_Full-Version_.wav");
            clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
            clip.open(ais);

            // Adjust volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-22.5f);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // A GUI element to prevent the Clip's daemon Thread 
                    // from terminating at the end of the main()
                    //JOptionPane.showMessageDialog(null, "Close to exit!");
                }
            });
        } catch(Exception e){}
    }

    public static void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
