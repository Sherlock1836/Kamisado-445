import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePanel extends JPanel {
    private BackgroundPanel boardPanelHolder;
    private JPanel boardPanel;
    private JPanel buttonPanel;
    private JButton returnToMenu;
    private JButton start;
    private JButton reset;
    private JLabel turnLabel;

    private Square selectedSquare;      // Store the selected square for dragging
    private Square destinationSquare;   //Store destination square

    public Square[] getMove() {
        Square[] move = null;
        if(selectedSquare != null && destinationSquare != null){
            move = new Square[] {selectedSquare, destinationSquare}; //pack move in an array to be processed
            selectedSquare.setSelected(false);
            selectedSquare = null;
            destinationSquare = null;
        }
        return move;
    }

    public GamePanel(Board board) {
        boardPanelHolder = new BackgroundPanel(new Color(255, 255, 255, 1));
        boardPanelHolder.setPreferredSize(new Dimension(550, 550));
        boardPanel = new JPanel(new GridLayout(8, 8));

        // Iterate over each square in the board array
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Get the square at the current position
                Square square = board.getBoardArray()[row][col];

                // Add mouse listener to each square
                square.addMouseListener(new SquareMouseListener());
                //square.addMouseMotionListener(new SquareMouseMotionListener());

                // Add the square to the board panel
                boardPanel.add(square);
            }
        }

        boardPanelHolder.add(boardPanel, BorderLayout.CENTER);
        add(boardPanelHolder);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(100, 200));

        turnLabel = new JLabel(" Click Start "); //"            "
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        turnLabel.setOpaque(true);
        turnLabel.setForeground(Color.BLACK);
        turnLabel.setBackground(new Color(255, 255, 255, 95));
        buttonPanel.add(turnLabel);

        start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Kamisado.setGameOver(false);
            }
        });
        buttonPanel.add(start);

        reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Kamisado.setGameOver(true);
                setTurnLabel(" Click Start ");
                Kamisado.clearBoard();
                repaint();
            }
        });
        buttonPanel.add(reset);

        returnToMenu = new JButton("Back to Menu");
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kamisado.setGameOver(true);
                setTurnLabel(" Click Start ");
                Kamisado.clearBoard();
                Kamisado.showPanel("MenuPanel");
            }
        });
        buttonPanel.add(returnToMenu);

        add(buttonPanel);
    }

    // Inner class for mouse listener implementation
    private class SquareMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (selectedSquare == null) {
                selectedSquare = (Square) e.getSource();
                if ((selectedSquare.getDragonTower() != null)
                        && ((selectedSquare.getDragonTower().getTeam() == "black") == Kamisado.isBlacksTurn)) {
                    selectedSquare.setSelected(true); // Highlight selected square
                    selectedSquare.repaint(); // probably will need this
                } else {
                    selectedSquare = null;
                }
            } else {
                Square clickedSquare = (Square) e.getSource();
                if (clickedSquare == selectedSquare) {
                    // If the destination square is the same as the selected square, reset selectedSquare
                    selectedSquare.setSelected(false);
                    selectedSquare.repaint();
                    selectedSquare = null;
                } else {
                    destinationSquare = clickedSquare;
                }
            }
        }
    }

    public void setTurnLabel(String t) {
        turnLabel.setText(t);
        turnLabel.setForeground(Square.setColor(MoveValidator.getLMOColor()));
    }
    //IF WE WANT DRAGGING AT SOME POINT WE CAN USE THISSS
    // Inner class for mouse motion listener implementation
    //All this set to change, feel free to edit
    /*private class SquareMouseMotionListener extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedSquare != null) {
                // Get the current square being dragged
                Square draggedSquare = (Square) e.getSource();
                // Update the location of the dragged square based on mouse position
                draggedSquare.setLocation(draggedSquare.getX() - draggedSquare.getWidth()/2 + e.getX(), draggedSquare.getY() - draggedSquare.getHeight()/2 + e.getY());
            }
        }
    }*/
}
