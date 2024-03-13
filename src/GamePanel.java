import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GridLayout;

public class GamePanel  extends JPanel{
    private JPanel boardPanel;  //a panel on which to display the board graphically
    private JPanel squares[][]; //these individual panels will go in the board panel,
                                // and each one will have its own mouse listener to detect clicks

    public GamePanel() {
        boardPanel = new JPanel(new GridLayout (8, 8));
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
