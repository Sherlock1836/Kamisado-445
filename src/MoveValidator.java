public class MoveValidator {
    private static boolean isFirstTurn = true;
    private static String temp;
    public static String lastMovedOpponentColor = null;
    private static String currentPlayerColor;
    private static String currentTeamColor;
    private static boolean turnChange = false;

    private MoveValidator() {
    }

    public static String getLMOColor() {
        return lastMovedOpponentColor;
    }

    public static void setisFirstTurn(boolean i) {
        isFirstTurn = i;
    }

    public static void resetValidator() {
        isFirstTurn = true;
        lastMovedOpponentColor = null;
        turnChange = false;
        currentPlayerColor = null;
        currentTeamColor = null;
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
        if (isFirstTurn && isValidMove) {
            endFirstTurn();
        }

        return isValidMove;
    }

    private static boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // call our various rule methods-- if path is forward, if piece did and can
        // move, if its path was clear
        // return true if all conditions are met, false otherwise
        setTurnChange(false);
        if (!isFirstTurn && currentPlayerColor != lastMovedOpponentColor) {
            AllMoveAndWinSounds.playIncorrectMoveSound();
            System.out.println("Incorrect Piece Color");
            return false;
        }
        // Check if the movement is sideways
        if (startY == endY) {
            AllMoveAndWinSounds.playIncorrectMoveSound();
            System.out.println("Sideways movement is not allowed");
            canMove(startX, startY, endX, endY, board);
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
        // Check boundary conditions
        boolean onLeftEdge = startX == 0;
        boolean onRightEdge = startX == 7;
        boolean onTopEdge = startY == 0;
        boolean onBottomEdge = startY == 7;

        // Check if no possible moves and avoid out of bounds
        if (currentTeamColor.equals("black")) {
            // Assuming black moves down the board
            if (!onBottomEdge && board.getBoardArray()[startY + 1][startX].getDragonTower() != null) {
                boolean blockedRight = onRightEdge
                        || board.getBoardArray()[startY + 1][startX + 1].getDragonTower() != null;
                boolean blockedLeft = onLeftEdge
                        || board.getBoardArray()[startY + 1][startX - 1].getDragonTower() != null;

                if (blockedRight && blockedLeft) {
                    AllMoveAndWinSounds.playIncorrectMoveSound();
                    System.out.println("Turn Change");
                    setTurnChange(true);
                    lastMovedOpponentColor = board.getBoardArray()[startY][startX].getColor(); // Update color of last moved opponent's piece
                    return false;
                }
            }
        } else if (currentTeamColor.equals("white")) {
            // Assuming white moves up the board
            if (!onTopEdge && board.getBoardArray()[startY - 1][startX].getDragonTower() != null) {
                boolean blockedRight = onRightEdge
                        || board.getBoardArray()[startY - 1][startX + 1].getDragonTower() != null;
                boolean blockedLeft = onLeftEdge
                        || board.getBoardArray()[startY - 1][startX - 1].getDragonTower() != null;

                if (blockedRight && blockedLeft) {
                    AllMoveAndWinSounds.playIncorrectMoveSound();
                    System.out.println("Turn Change");
                    setTurnChange(true);
                    lastMovedOpponentColor = board.getBoardArray()[startY][startX].getColor(); // Update color of last moved opponent's piece
                    return false;
                }
            }
        } else {
            return false;
        }

        return true; // Default return value if no blocks are found
    }

    private static boolean isStraight(int startX, int startY, int endX, int endY) {
        // Calculate the change in X and Y coordinates
        int deltaX = endX - startX;
        int deltaY = endY - startY;

        if (currentTeamColor == "white") {
            if (deltaY > 0) {
                AllMoveAndWinSounds.playIncorrectMoveSound();
                System.out.println("White moved backwards");
                return false;
            } else if (deltaY < 0 && deltaX == 0) {
                System.out.println("White: Vertical Forward");
                return true;
            } else if (Math.abs(deltaY / deltaX) == 1) {
                System.out.println("White: Diagonal Forward");
                return true;
            } else {
                AllMoveAndWinSounds.playIncorrectMoveSound();
                System.out.println("Invalid Movement 1");
                return false;
            }
        } else if (currentTeamColor == "black") {
            if (deltaY < 0) {
                AllMoveAndWinSounds.playIncorrectMoveSound();
                System.out.println("Black moved backwards");
                return false;
            } else if (deltaY > 0 && deltaX == 0) {
                System.out.println("Black: Vertical Forward");
                return true;
            } else if (Math.abs(deltaY / deltaX) == 1) {
                System.out.println("Black: Diagonal Forward");
                return true;
            } else {
                AllMoveAndWinSounds.playIncorrectMoveSound();
                System.out.println("Invalid Movement 2");
                return false;
            }
        } else {
            AllMoveAndWinSounds.playIncorrectMoveSound();
            System.out.println("Invalid Movement 3");
            return false;
        }
    }

    private static boolean isClear(int startX, int startY, int endX, int endY, Board board) {
        int deltaY = endY - startY;
        int deltaX = endX - startX;

        if (board.getBoardArray()[endY][endX].getDragonTower() != null) {
            AllMoveAndWinSounds.playIncorrectMoveSound();
            System.out.println("Piece already at location");
            return false;
        }

        // Check direction of movement-- only forward vertical or forward diagonal
        if (deltaX == 0) {
            // Vertical

            int yIncrement = Integer.compare(deltaY, 0);
            for (int y = startY + yIncrement; y != endY; y += yIncrement) {
                if (board.getBoardArray()[y][startX].getDragonTower() != null) {
                    AllMoveAndWinSounds.playIncorrectMoveSound();
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
                    AllMoveAndWinSounds.playIncorrectMoveSound();
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
