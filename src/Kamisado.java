/*
 * Matthew Hayes, Alexander Imm, Nathaniel Turner, Isaac Reynolds 
 * CSC 445 Final Project
 */
public class Kamisado {
    private static Board gameBoard;
    public static void main(String[] args) throws Exception {
        gameBoard = new Board();
        gameBoard.initializeBoard();
        new GraphicsWindow(gameBoard);
    }
}
