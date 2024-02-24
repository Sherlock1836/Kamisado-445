
public class Square {
    private final String COLOR;
    private DragonTower dTower = null;

    public String getColor() {
        return COLOR;
    }

    public DragonTower getDragonTower() {
        return dTower;
    }

    public Square(String color, DragonTower dTower) {
        COLOR = color;
        this.dTower = dTower;
    }
}
