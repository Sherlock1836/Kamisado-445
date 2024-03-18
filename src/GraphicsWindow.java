import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GraphicsWindow extends JFrame{
    private final int FRAME_WIDTH = 720;
    private final int FRAME_HEIGHT = 600;
    
    private GamePanel gp;
    private MenuPanel mp;
    private CardLayout cl = new CardLayout();
    private BackgroundPanel cards;  //set background image with the constructor
    

    public GraphicsWindow(Board board) {
        gp = new GamePanel(board);
        mp = new MenuPanel();

        Image img = null;
        try{
            img = ImageIO.read(new File("src/images/background.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        //replace getForeground() with the background image we want
        cards = new BackgroundPanel(img);
        cards.setLayout(cl);
        cards.add(gp, "GamePanel");
        cards.add(mp, "MenuPanel");
        
        cl.show(cards, "MenuPanel");
    

        add(cards);
        setTitle("Kamisado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showPanel(String panel) {
        cl.show(cards, panel);
    }
}
