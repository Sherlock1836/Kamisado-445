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
                whitePlayer.resetScore();
                blackPlayer.resetScore();
                isBlacksTurn = true;
                gameBoard.resetBoard();
                MoveValidator.resetValidator();
                // reset player scores
                gWindow.updateGamePanel();
                gameLoop(blackPlayer, whitePlayer);
            }
            try {
                Thread.sleep(250); // sleep so that while loop doesn't kill itself
                // System.out.println("wait");
            } catch (InterruptedException e) {
                System.out.println("catch");
            }
        }
    }

    private static void gameLoop(Player bPlayer, Player wPlayer) {
        while (!gameOver) {
            // System.out.println("gameloop");
            String labelTurn = isBlacksTurn ? "Black's Turn" : "White's Turn";
            gWindow.updateTurnLabel(labelTurn);
            move = isBlacksTurn ? bPlayer.getMove() : wPlayer.getMove();
            while (move == null && !gameOver) {
                move = isBlacksTurn ? bPlayer.getMove() : wPlayer.getMove();
                try {
                    // System.out.println("Getting move...");
                    Thread.sleep(100); // sleep so that while loop doesn't kill itself
                } catch (InterruptedException e) {
                }
            }
            if (move != null) {
                System.out.print("" + move[0].getRow() + " " + move[0].getColumn());
                System.out.println(" to " + move[1].getRow() + " " + move[1].getColumn());

                if (MoveValidator.checkValidityOf(move, gameBoard)) {
                    executeMove(move);
                    if (move[1].getRow() == 0 || move[1].getRow() == 7) {
                        move[1].getDragonTower().promote();
                        if (isBlacksTurn)
                            blackPlayer.addToScore(move[1].getDragonTower().getValue());
                        else
                            whitePlayer.addToScore(move[1].getDragonTower().getValue());
                    }
                    isBlacksTurn = !isBlacksTurn;
                }
                checkForWin(); // checks for win and do what needs to be done
                if (MoveValidator.getTurnChange()) {
                    isBlacksTurn = !isBlacksTurn;
                }
            }
            gWindow.updateGamePanel(); // update gui (just the board...labels have to be manually changed in the loop)
        }
        // do whatever needs to be done after game ends (remove all pieces, show end
        // screen, etc)
    }

    private static void checkForWin(/* add a score parameter if we add new game mode */) {
        if (whitePlayer.getScore() > 0) {
            gameOver = !gameOver;
            gWindow.updateTurnLabel("White Wins!");
            // set winner (boolean in winner Panel)
            // showPanel("WinnerPanel");
        } else if (blackPlayer.getScore() > 0) {
            gameOver = !gameOver;
            gWindow.updateTurnLabel("Black Wins!");
            ;
            // set winner
            // showPanel("WinnerPanel");
        }
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
        System.exit(0);
    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    public static Square[] getMoveFromGUI() {
        return gWindow.getMove();
    }

    public static void clearBoard() {
        gameBoard.clearBoard();
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
