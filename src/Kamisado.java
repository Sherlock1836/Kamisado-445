/*
 * Matthew Hayes, Alexander Imm, Nathaniel Turner, Isaac Reynolds 
 * CSC 445 Final Project
 */

public class Kamisado {
    private static Board gameBoard;
    private static GraphicsWindow gWindow;
    private static MoveValidator moveValidator;
    private static boolean gameOver;
    private static Square[] move;

    public static void main(String[] args) throws Exception {
        gameOver = true;
        gameBoard = new Board();
        gameBoard.initializeBoard();
        gWindow = new GraphicsWindow(gameBoard);
        moveValidator = new MoveValidator();

        while (true) { // continually check to see if start button has been clicked
            if (!gameOver) { // once start has been clicked, reset board and enter the game loop
                gameBoard.resetBoard();
                gWindow.updateGamePanel();
                // gameLoop();
            }
            try {
                Thread.sleep(250); // sleep so that while loop doesn't kill itself
            } catch (InterruptedException e) {
            }
        }
    }

    public static void gameLoop() {
        while (!gameOver) {
            move = gWindow.getMove();
            while (move == null) {
                move = gWindow.getMove();
                try {
                    Thread.sleep(100); // sleep so that while loop doesn't kill itself
                } catch (InterruptedException e) {
                }
            }
            if (moveValidator.checkValidityOf(move))
                executeMove(move);
            // check if game has been won
            gWindow.updateGamePanel(); // update gui
        }
        // do whatever needs to be done after game ends (remove all pieces, show end
        // screen, etc)
    }

    private static void executeMove(Square[] move) {
        move[1].setDragonTower(move[0].getDragonTower());
        move[0].setDragonTower(null);
    }

    public static void showPanel(String panel) {
        gWindow.showPanel(panel);
    }

    public static void endGame() {
        gWindow.dispose();
    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }
}
