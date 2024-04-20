public class Player {
    private int score;
    private boolean isBot;
    private String teamColor;

    public Player(boolean isBot, String teamColor) {
        this.isBot = isBot;
        score = 0;
        this.teamColor = teamColor;
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
        // Find the piece that matches the last moved opponent color, assuming "white"
        // team plays the bot
        DragonTower toMove = Kamisado.pieces.get("white " + MoveValidator.lastMovedOpponentColor);

        if (toMove == null) {
            return null; // No valid piece to move based on the last color
        }

        Square currentSquare = toMove.getSquare();
        int startRow = currentSquare.getRow();
        int startCol = currentSquare.getColumn();

        // Try moving forward and diagonally, if possible, checking for boundaries and
        // blockages
        int[] potentialRows = { startRow - 1, startRow - 1, startRow - 1 }; // Possible rows for forward and diagonal
                                                                            // moves
        int[] potentialCols = { startCol, startCol - 1, startCol + 1 }; // Corresponding columns

        for (int i = 0; i < potentialRows.length; i++) {
            int newRow = potentialRows[i];
            int newCol = potentialCols[i];

            // Check boundaries
            if (newRow >= 0 && newCol >= 0 && newCol < Kamisado.gameBoard.getBoardArray()[0].length) {
                Square newSquare = Kamisado.gameBoard.getBoardArray()[newRow][newCol];
                if (newSquare.getDragonTower() == null) { // Check if the square is unoccupied
                    return new Square[] { currentSquare, newSquare }; // Valid move found
                }
            }
        }

        // If no valid move is found, just return the current position (no move)
        return new Square[] { currentSquare, currentSquare };
    }

}
