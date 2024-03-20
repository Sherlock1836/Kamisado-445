public class MoveValidator {
    private static boolean isFirstTurn = true;
    private static String lastMovedOpponentColor = null;
    private static String currentPlayerColor;

    private MoveValidator() {}

    public static boolean checkValidityOf(Square[] move) {
        // Extract move coordinates
        int startX = move[0].getX();
        int startY = move[0].getY();
        int endX = move[1].getX();
        int endY = move[1].getY();

        // Extract current player color
        currentPlayerColor = move[0].getDragonTower().getColor();

        // Check if the move is valid according to Kamisado rules
        boolean isValidMove = isValidMove(startX, startY, endX, endY, currentPlayerColor);

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

    private static boolean isValidMove(int startX, int startY, int endX, int endY, String currentPlayerColor) {
        // Implement move validation logic here according to Kamisado rules
        // You can use the rules provided earlier in the conversation to guide the
        // implementation

        // For example:
        // boolean isValid = RuleM1(startX, startY, endX, endY) &&
        // RuleM2(startX, startY, endX, endY) &&
        // RuleM3(endX, endY) &&
        // RuleM5(startX, startY, endX, endY) &&
        // RuleM6(currentPlayerColor);
        // return isValid;
        return false;
    }

    private static void updateLastMovedOpponentColor(String color) {
        lastMovedOpponentColor = color;
    }

    private static void endFirstTurn() {
        isFirstTurn = false;
    }

    private boolean isStraight(int startX, int startY, int endX, int endY) {
        if (currentPlayerColor == "black") {
            if ((startX == endX && endY - startY > 0) || ((endY - startY) / (endX - startX) == 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((startX == endX && endY - startY < 0) || ((endY - startY) / (endX - startX) == -1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isClear(int startX, int startY, int endX, int endY) {
        return false;

    }

}
