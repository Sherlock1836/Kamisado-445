import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private BackgroundPanel boardPanelHolder;
    private JPanel boardPanel;
    private JPanel buttonPanel;
    private JButton returnToMenu;
    private JButton start;

    private Square selectedSquare; // Store the selected square for dragging
    private Square destinationSquare; // Store destination square

    public Square[] getMove() {
        Square[] move = null;
        if (selectedSquare != null && destinationSquare != null) {
            move = new Square[] { selectedSquare, destinationSquare }; // pack move in an array to be processed
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
                // square.addMouseMotionListener(new SquareMouseMotionListener());

                // Add the square to the board panel
                boardPanel.add(square);
            }
        }

        boardPanelHolder.add(boardPanel, BorderLayout.CENTER);
        add(boardPanelHolder);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(100, 200));
        start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Kamisado.setGameOver(false);
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

    // Inner class for mouse listener implementation
    private class SquareMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (selectedSquare == null) {
                selectedSquare = (Square) e.getSource();
                selectedSquare.setSelected(true);// Highlight selected square
                selectedSquare.repaint(); // probably will need this
            } else {
                destinationSquare = (Square) e.getSource();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedSquare.setSelected(false); // Remove highlight when mouse released
            selectedSquare.repaint(); // probably will need need this
            selectedSquare = null; // Reset selected square
        }
    }
}

// Inner class for mouse listener implementation
// Modified for move validation purposes
/*
 * private class SquareMouseListener extends MouseAdapter {
 * 
 * @Override
 * public void mousePressed(MouseEvent e) {
 * Square clickedSquare = (Square) e.getSource();
 * 
 * 
 * // Check if it's the playing team's turn
 * if (isPlayingTeamPiece(clickedSquare)) {
 * // Check if no square is selected yet or if the clicked square is already
 * // selected
 * if (selectedSquare == null || selectedSquare == clickedSquare) {
 * // Highlight the selected square
 * selectedSquare = clickedSquare;
 * selectedSquare.setSelected(true);
 * } else {
 * // Check validity of the move
 * if (isValidMove(selectedSquare, clickedSquare)) {
 * // Set clicked square as the destination square
 * destinationSquare = clickedSquare;
 * } else {
 * // Display an error message or perform other actions for an invalid move
 * }
 * }
 * } else {
 * // Display an error message or perform other actions for attempting to select
 * an
 * // opponent's piece
 * }
 * }
 * 
 * private boolean isPlayingTeamPiece(Square square) {
 * // Implement logic to check if the piece belongs to the playing team
 * // You might need to access some global variables or methods to determine the
 * // playing team
 * // For example:
 * // return square.getDragonTower().getColor().equals("playing_team_color");
 * return false;
 * }
 * 
 * private boolean isValidMove(Square startSquare, Square endSquare) {
 * // Implement logic to check the validity of the move
 * // You can use the MoveValidator class or define the rules directly here
 * return false;
 * }
 * 
 * }
 * }
 */

// Inner class for mouse motion listener implementation
// All this set to change, feel free to edit
/*
 * private class SquareMouseMotionListener extends MouseAdapter {
 * 
 * @Override
 * public void mouseDragged(MouseEvent e) {
 * if (selectedSquare != null) {
 * // Get the current square being dragged
 * Square draggedSquare = (Square) e.getSource();
 * // Update the location of the dragged square based on mouse position
 * draggedSquare.setLocation(draggedSquare.getX() - draggedSquare.getWidth()/2 +
 * e.getX(), draggedSquare.getY() - draggedSquare.getHeight()/2 + e.getY());
 * }
 * }
 * }
 */
