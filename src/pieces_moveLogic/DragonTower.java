package pieces_moveLogic;
import board.Square;

public class DragonTower {
    private boolean sumo;
    private String color;
    private String team;
    private int value;
    private Square square;

    public DragonTower(String color, String team_col, Square square) {
        sumo = false;
        this.color = color;
        team = team_col;
        value = 0;
        this.square = square;
    }

    public boolean isSumo() {
        return sumo;
    }

    public String getColor() {
        return color;
    }

    public String getTeam() {
        return team;
    }

    public int getValue() {
        return value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setValue(int score) {
    }

    public void promote() {
        // if dtower reaches end of board, promote, set sumo to True, increase value
        // not doing anything with this for now because I might move class
        setValue(value++);
        sumo = true;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }
}
