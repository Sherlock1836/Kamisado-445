public class MoveValidator {
    private static boolean isFirstTurn = true;
    private static String lastMovedOpponentColor = null;
    private static String currentPlayerColor;

    private MoveValidator() {
    }

    public static boolean checkValidityOf(Square[] move, Board board) {
        // Extract move coordinates
        int startX = move[0].getColumn();
        int startY = move[0].getRow();
        int endX = move[1].getColumn();
        int endY = move[1].getRow();

        // Extract current player color
        currentPlayerColor = move[0].getDragonTower().getColor();

        // Check if the move is valid according to Kamisado rules
        boolean isValidMove = isValidMove(startX, startY, endX, endY, board);

        // If it's the first turn, end it after the first move
        if (isFirstTurn) {
            endFirstTurn();
        }

        // If the move is valid, update the last moved opponent color
        if (isValidMove) {
            updateLastMovedOpponentColor(move[1].getDragonTower().getColor());
        }

        return isValidMove;
    }

    private static boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // call our various rule methods-- if path is forward, if piece did and can
        // move, if its path was clear
        // return true if all conditions are met, false otherwise
        return canMove(startX, startY, endX, endY, board) && isClear(startX, startY, endX, endY, board)
                && isStraight(startX, startY, endX, endY);
    }

    private static void updateLastMovedOpponentColor(String color) {
        lastMovedOpponentColor = color;
    }

    private static void endFirstTurn() {
        isFirstTurn = false;
    }

    private static boolean canMove(int startX, int startY, int endX, int endY, Board board) {
        // first check if piece moved.
        if (startY != endY) {
            return true;
        } else {
            // if no possible moves, return true
            if (board.getBoardArray()[startY + 1][startX].getDragonTower() != null &&
                    board.getBoardArray()[startY + 1][startX + 1].getDragonTower() != null &&
                    board.getBoardArray()[startY + 1][startX - 1].getDragonTower() != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean isStraight(int startX, int startY, int endX, int endY) {
        // check for straight line moves
        // black starts from index 0, so slope will be positive
        if (currentPlayerColor == "black") {
            if ((startX == endX && endY - startY > 0) || ((endY - startY) / (endX - startX) == 1)) {
                return true;
            } else {
                return false;
            }
            // white will start from index 7, so slope will be negative.
        } else {
            if ((startX == endX && endY - startY < 0) || ((endY - startY) / (endX - startX) == -1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean isClear(int startX, int startY, int endX, int endY, Board board) {
        int deltaY = endY - startY;
        int deltaX = endX - startX;

        // Check direction of movement-- only forward vertical or forward diagonal
        if (deltaX == 0) {
            // Vertical
            int yIncrement = Integer.compare(deltaY, 0);
            for (int y = startY + yIncrement; y != endY; y += yIncrement) {
                if (board.getBoardArray()[y][startX].getDragonTower() != null) {
                    return false; // there's a piece in the way
                }
            }
        } else if (Math.abs(deltaY) == Math.abs(deltaX)) {
            // Diagonal
            int xIncrement = Integer.compare(deltaX, 0);
            int yIncrement = Integer.compare(deltaY, 0);
            for (int x = startX + xIncrement, y = startY + yIncrement; x != endX
                    && y != endY; x += xIncrement, y += yIncrement) {
                if (board.getBoardArray()[y][x].getDragonTower() != null) {
                    return false; // there's a piece in the way
                }
            }
        } else {
            // invalid movement (not vertical or diagonal)
            return false;
        }

        // no pieces in the way, move is clear
        return true;
    }

}
