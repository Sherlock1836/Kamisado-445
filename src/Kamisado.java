/*
 * Matthew Hayes, Alexander Imm, Nathaniel Turner, Isaac Reynolds 
 * CSC 445 Final Project
 */

public class Kamisado {
    private static Board gameBoard;
    private static GraphicsWindow gWindow;
    private static boolean gameOver;
    private static Square[] move;
    public static boolean isBlacksTurn; // is public so square mouse listener can check team before allowing selection
    private static Player whitePlayer;
    private static Player blackPlayer;

    public static void main(String[] args) throws Exception {
        gameOver = true;
        isBlacksTurn = true;
        gameBoard = new Board();
        gameBoard.initializeBoard();
        gWindow = new GraphicsWindow(gameBoard);

        while (true) { // continually check to see if start button has been clicked
            if (!gameOver) { // once start has been clicked, reset board and enter the game loop
                gameBoard.resetBoard();
                gWindow.updateGamePanel();
                gameLoop(blackPlayer, whitePlayer);
            }
            try {
                Thread.sleep(250); // sleep so that while loop doesn't kill itself
            } catch (InterruptedException e) {
            }
        }
    }

    private static void gameLoop(Player bPlayer, Player wPlayer) {
        while (!gameOver) {
            while (move == null) {
                move = isBlacksTurn ? bPlayer.getMove() : wPlayer.getMove();
                try {
                    Thread.sleep(100); // sleep so that while loop doesn't kill itself
                } catch (InterruptedException e) {
                }
            }
            if (MoveValidator.checkValidityOf(move, gameBoard)) {
                executeMove(move);
                isBlacksTurn = !isBlacksTurn;
            }
            // check if a piece made it to a homerow and update piece and score(attached to
            // player object) accordingly
            // either display win screen or start new round
            gWindow.updateGamePanel(); // update gui
        }
        // do whatever needs to be done after game ends (remove all pieces, show end
        // screen, etc)
    }

    private static void executeMove(Square[] move) {
        move[1].setDragonTower(move[0].getDragonTower());
        move[0].setDragonTower(null);
    }

    // methods to interface with gui or somn
    public static void showPanel(String panel) {
        gWindow.showPanel(panel);
    }

    public static void endGame() {
        gWindow.dispose();
    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    public static Square[] getMoveFromGUI() {
        return gWindow.getMove();
    }

    public static void setPlayers(boolean isBotGame) {
        if (isBotGame) {
            whitePlayer = new Player(true); // makes white player a bot
            blackPlayer = new Player(false);
        } else {
            whitePlayer = new Player(false);
            blackPlayer = new Player(false);
        }
    }
}
