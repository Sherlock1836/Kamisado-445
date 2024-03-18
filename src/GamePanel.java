import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    private BackgroundPanel boardPanelHolder;
    private JPanel boardPanel;  //a panel on which to display the board graphically
                                 //these individual panels will go in the board panel,
                                //and each one will have its own mouse listener to detect clicks...
                                //need to make Squares extend JPanel and implement a paint method 

    public GamePanel(Board board) {
        boardPanelHolder = new BackgroundPanel(new Color(255, 255, 255, 1));
        boardPanelHolder.setPreferredSize(new Dimension(550, 550));
        boardPanel = new JPanel(new GridLayout (8, 8));
        for(int row = 0; row < 8; row++)
            for(int col = 0; col < 8; col++)
                boardPanel.add(board.getBoardArray()[row][col]);
        boardPanelHolder.add(boardPanel, BorderLayout.CENTER);
        add(boardPanelHolder);
    }
}
