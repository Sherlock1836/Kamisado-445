
public class Board {
    //board array is in [row, column] form ([y, x])
    private Square[][] boardArray = new Square[8][8];

    public Board() {
        resetBoard();
        printBoard();
    }

    //this method is fucking stupid but I spent too much time on it to quit so here it is...
    //generates the square colors with a function for each color
    public void resetBoard() {
        for(int row = 0; row < boardArray.length; row++) {
            for(int column = 0; column < boardArray[row].length; column++){
                if(row == column)
                    boardArray[row][column] = new Square("orange", null);
                else if(7 - row == column)
                    boardArray[row][column] = new Square("black", null);
                else if((row < 4 ? 3 - row : 7 - row + 4) == column)
                    boardArray[row][column] = new Square("pink", null);
                else if((row < 4 ? 4 + row : 0 + row - 4) == column)
                    boardArray[row][column] = new Square("yellow", null);
                else if(((row < 3) ? (6 - 3 * row) : ((row < 5) ? (5 - 3 * (row - 3)) : (7 - 3 * (row - 5)))) == column)
                    boardArray[row][column] = new Square("green", null);
                else if(((row < 3) ? (1 + 3 * row) : ((row < 5) ? (2 + 3 * (row - 3)) : (3 * (row - 5)))) == column)
                    boardArray[row][column] = new Square("blue", null);
                else if(((row % 7 == 0) ? (row == 0 ? 2 : 5) : ((row < 4) ? (7 - 3 * (row - 1)) : (9 - 3 * (row - 3)))) == column)
                    boardArray[row][column] = new Square("purple", null);
                else
                    boardArray[row][column] = new Square("red", null);
            }    
        }
    }

    //print method for testing
    public void printBoard() {
        for(int row = 0; row < boardArray.length; row++) {
            for(int column = 0; column < boardArray[row].length; column++){
                if(boardArray[row][column] != null && boardArray[row][column].getColor() != null){
                    switch(boardArray[row][column].getColor()) {
                        case "orange":
                            System.out.print(" O ");
                            break;
                        case "blue":
                            System.out.print(" B ");
                            break;
                        case "purple":
                            System.out.print(" P ");
                            break;
                        case "pink":
                            System.out.print(" p ");
                            break;
                        case "yellow":
                            System.out.print(" Y ");
                            break;
                        case "red":
                            System.out.print(" R ");
                            break;
                        case "green":
                            System.out.print(" G ");
                            break;
                        case "black":
                            System.out.print(" b ");
                            break;
                        default:
                            System.out.print(" * ");
                    }
                } else
                    System.out.print("*");
            }  
            System.out.println("");  
        }
    }
}
