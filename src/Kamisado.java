/*
 * Matthew Hayes, Alexander Imm, Nathaniel Turner, Isaac Reynolds 
 * CSC 445 Final Project
 */
public class Kamisado {
    private static Board gameBoard;
    private static GraphicsWindow gWindow;
    public static void main(String[] args) throws Exception {
        gameBoard = new Board();
        gameBoard.initializeBoard();
        gameBoard.resetBoard();

        gWindow = new GraphicsWindow(gameBoard);
    }

    public static void showPanel(String panel) {
        gWindow.showPanel(panel);
    }

    public static void endGame() {
        gWindow.dispose();
    }

    public static void gameLoop(){
        
    }
}
