import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class GamePanel  extends JPanel{
    private Board board;        //add a reference to the game board
    private JPanel boardPanel;  //a panel on which to display the board graphically
                                 //these individual panels will go in the board panel,
                                //and each one will have its own mouse listener to detect clicks...
                                //need to make Squares extend JPanel and implement a paint method 

    public GamePanel(Board board) {
        setLayout(new BorderLayout());
        boardPanel = new JPanel(new GridLayout (8, 8));
        this.board = board;
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++){
                boardPanel.add(board.getBoardArray()[row][col]);
                //System.out.println(board.getBoardArray()[row][col].getColor());
            }
        }    
        setPreferredSize(new Dimension(8*50, 8*50));
        add(boardPanel, BorderLayout.CENTER);
    }

    /*@Override
    protected void paintComponent(Graphics g) {

    }*/
}
