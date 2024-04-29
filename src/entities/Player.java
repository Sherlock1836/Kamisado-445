package entities;

import board.Square;
import main.Kamisado;
import pieces_moveLogic.DragonTower;
import pieces_moveLogic.MoveValidator;

public class Player {
    private int score;
    private boolean isBot;
    private String teamColor;

    public Player(boolean isBot, String teamColor) {
        this.isBot = isBot;
        score = 0;
        this.teamColor = teamColor;
    }

    public boolean isBot() {
        return isBot;
    }

    public Square[] getMove() {
        if (isBot)
            return calculateMove();
        else
            return Kamisado.getMoveFromGUI();
    }

    public void addToScore(int n) {
        score += n;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    // bots algorithm
    private Square[] calculateMove() {
        // find the piece that matches the last moved opponent color, white default team
        // team plays the bot
        DragonTower toMove = Kamisado.pieces.get("white " + MoveValidator.lastMovedOpponentColor);

        if (toMove == null) {
            return null; // no valid piece to move based on the last color
        }

        Square currentSquare = toMove.getSquare();
        int startRow = currentSquare.getRow();
        int startCol = currentSquare.getColumn();

        // try moving forward and diagonally, if possible, checking for boundaries and
        // blocks
        int[] potentialRows = { startRow - 1, startRow - 1, startRow - 1 }; // possible rows for forward and diagonal
                                                                            // moves
        int[] potentialCols = { startCol, startCol - 1, startCol + 1 }; // corresponding columns

        for (int i = 0; i < potentialRows.length; i++) {
            int newRow = potentialRows[i];
            int newCol = potentialCols[i];

            // check boundaries
            if (newRow >= 0 && newCol >= 0 && newCol < Kamisado.gameBoard.getBoardArray()[0].length) {
                Square newSquare = Kamisado.gameBoard.getBoardArray()[newRow][newCol];
                if (newSquare.getDragonTower() == null) { // check if the square is unoccupied
                    return new Square[] { currentSquare, newSquare }; // valid move found
                }
            }
        }

        // if no valid move is found, just return the current position (no move)
        return new Square[] { currentSquare, currentSquare };
    }

}
