
public class Board {
    //board array is in [row, column] form ([y, x])
    private Square[][] boardArray = new Square[8][8];

    public Board() {
        resetBoard();
    }

    public void resetBoard() {
        String[] colors = new String[] {"orange", "blue", "purple", "pink", "yellow", "red", "green", "black"};
        for(int row = 0; row < boardArray.length; row++) {
            for(int column = 0; column < boardArray[row].length; column++){
               
            }    
        }
    }
}
