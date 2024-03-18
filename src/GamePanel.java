import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JButton;

public class GamePanel extends JPanel{
    private BackgroundPanel boardPanelHolder;
    private JPanel boardPanel;
    private JPanel buttonPanel;
    private JButton returnToMenu;
    private JButton start;

    public GamePanel(Board board) {
        boardPanelHolder = new BackgroundPanel(new Color(255, 255, 255, 1));
        boardPanelHolder.setPreferredSize(new Dimension(550, 550));
        boardPanel = new JPanel(new GridLayout (8, 8));
        for(int row = 0; row < 8; row++)
            for(int col = 0; col < 8; col++)
                boardPanel.add(board.getBoardArray()[row][col]);
        boardPanelHolder.add(boardPanel, BorderLayout.CENTER);
        add(boardPanelHolder);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(100, 200));
        start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //probably change a boolean in Kamisado called gameOver or something
            }
        });
        buttonPanel.add(start);

        returnToMenu = new JButton("Back to Menu");
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kamisado.showPanel("MenuPanel");
            }
        });
        buttonPanel.add(returnToMenu);

        add(buttonPanel);
    }
}
