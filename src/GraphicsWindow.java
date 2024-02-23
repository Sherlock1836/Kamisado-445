import java.awt.CardLayout;

import javax.swing.JFrame;

public class GraphicsWindow extends JFrame{
    private final int FRAME_WIDTH = 720;
    private final int FRAME_HEIGHT = 640;
    
    private CardLayout cl = new CardLayout();
    private BackgroundPanel cards;  //set background image with the constructor
    

    public GraphicsWindow() {
        //replace getForeground() with the background image we want
        cards = new BackgroundPanel(getForeground());
        cards.setLayout(cl);

        add(cards);
        setTitle("Kamisado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
