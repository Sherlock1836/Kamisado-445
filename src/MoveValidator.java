public class MoveValidator {
    private static boolean isFirstTurn = true;
    private static String temp;
    private static String lastMovedOpponentColor = null;
    private static String currentPlayerColor;
    private static String currentTeamColor;
    private static boolean turnChange = false;

    private MoveValidator() {
    }

    public static void resetValidator() {
        isFirstTurn = true;
        lastMovedOpponentColor = null;
    }

    public static boolean checkValidityOf(Square[] move, Board board) {
        // Extract move coordinates
        int startX = move[0].getColumn();
        int startY = move[0].getRow();
        int endX = move[1].getColumn();
        int endY = move[1].getRow();

        temp = move[1].getColor();

        currentTeamColor = board.getBoardArray()[startY][startX].getDragonTower().getTeam().toLowerCase();
        currentPlayerColor = board.getBoardArray()[startY][startX].getDragonTower().getColor();

        // Check if the move is valid according to Kamisado rules
        boolean isValidMove = isValidMove(startX, startY, endX, endY, board);

        // If it's the first turn, end it after the first move
        if (isFirstTurn) {
            endFirstTurn();
        }

        return isValidMove;
    }

    private static boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // call our various rule methods-- if path is forward, if piece did and can
        // move, if its path was clear
        // return true if all conditions are met, false otherwise
        if (!isFirstTurn && currentPlayerColor != lastMovedOpponentColor) {
            System.out.println("Incorrect Piece Color");
            return false;
        }
        boolean isValid = canMove(startX, startY, endX, endY, board) && isClear(startX, startY, endX, endY, board)
                && isStraight(startX, startY, endX, endY);

        if (isValid) {
            updateLastMovedOpponentColor(temp);
        }
        return isValid;
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
            if (currentTeamColor == "black") {
                if (board.getBoardArray()[startY + 1][startX].getDragonTower() != null &&
                        board.getBoardArray()[startY + 1][startX + 1].getDragonTower() != null &&
                        board.getBoardArray()[startY + 1][startX - 1].getDragonTower() != null) {
                    return true;
                } else {
                    setTurnChange(true);
                    return false;
                }
            } else if (currentTeamColor == "white") {
                if (board.getBoardArray()[startY - 1][startX].getDragonTower() != null &&
                        board.getBoardArray()[startY - 1][startX + 1].getDragonTower() != null &&
                        board.getBoardArray()[startY - 1][startX - 1].getDragonTower() != null) {
                    return true;
                } else {
                    setTurnChange(true);
                    return false;
                }
            } else {
                setTurnChange(true);
                return false;
            }
        }
    }

    private static boolean isStraight(int startX, int startY, int endX, int endY) {
        // Calculate the change in X and Y coordinates
        int deltaX = endX - startX;
        int deltaY = endY - startY;

        if (currentTeamColor == "white") {
            if (deltaY > 0) {
                System.out.println("White moved backwards");
                return false;
            } else if (deltaY < 0 && deltaX == 0) {
                System.out.println("White: Vertical Forward");
                return true;
            } else if (Math.abs(deltaY / deltaX) == 1) {
                System.out.println("White: Diagonal Forward");
                return true;
            } else {
                System.out.println("Invalid Movement 1");
                return false;
            }
        } else if (currentTeamColor == "black") {
            if (deltaY < 0) {
                System.out.println("Black moved backwards");
                return false;
            } else if (deltaY > 0 && deltaX == 0) {
                System.out.println("Black: Vertical Forward");
                return true;
            } else if (Math.abs(deltaY / deltaX) == 1) {
                System.out.println("Black: Diagonal Forward");
                return true;
            } else {
                System.out.println("Invalid Movement 2");
                return false;
            }
        } else {
            System.out.println("Invalid Movement 3");
            return false;
        }

    }

    private static boolean isClear(int startX, int startY, int endX, int endY, Board board) {
        int deltaY = endY - startY;
        int deltaX = endX - startX;

        if (board.getBoardArray()[endY][endX].getDragonTower() != null) {
            System.out.println("Piece already at location");
            return false;
        }

        // Check direction of movement-- only forward vertical or forward diagonal
        if (deltaX == 0) {
            // Vertical

            int yIncrement = Integer.compare(deltaY, 0);
            for (int y = startY + yIncrement; y != endY; y += yIncrement) {
                if (board.getBoardArray()[y][startX].getDragonTower() != null) {
                    System.out.println("There's a piece in the way");
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
                    System.out.println("Way is not clear");
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

    public static void setTurnChange(boolean n) {
        turnChange = n;

    }

    public static boolean getTurnChange() {
        return turnChange;
    }
}
