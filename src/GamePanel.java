import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JButton;

public class GamePanel extends JPanel {
    private BackgroundPanel boardPanelHolder;
    private JPanel boardPanel;
    private JPanel buttonPanel;
    private JButton returnToMenu;
    private JButton start;

    private Square selectedSquare; // Store the selected square for dragging

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
                square.addMouseMotionListener(new SquareMouseMotionListener());

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
                // probably change a boolean in Kamisado called gameOver or something
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
            selectedSquare = (Square) e.getSource();
            selectedSquare.setOpaque(true); // Highlight selected square
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedSquare.setOpaque(false); // Remove highlight when mouse released
            selectedSquare = null; // Reset selected square
        }
    }

    // Inner class for mouse motion listener implementation
    //All this set to change, feel free to edit
    private class SquareMouseMotionListener extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedSquare != null) {
                // Get the current square being dragged
                Square draggedSquare = (Square) e.getSource();
                // Update the location of the dragged square based on mouse position
                draggedSquare.setLocation(draggedSquare.getX() + e.getX(), draggedSquare.getY() + e.getY());
            }
        }
    }
}
