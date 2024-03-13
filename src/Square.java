import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends JPanel{
    private final String COLOR;
    private DragonTower dTower = null;

    public Square(String color, DragonTower dTower) {
        COLOR = color;
        this.dTower = dTower;
    }

    public String getColor() {
        return COLOR;
    }

    public DragonTower getDragonTower() {
        return dTower;
    }

    @Override
    protected void paintComponent(Graphics g) {
        switch(COLOR) {
            case "orange":
                g.setColor(Color.ORANGE);
                break;
            case "blue":
                g.setColor(Color.BLUE);
                break;
            case "purple":
                g.setColor(new Color(255, 0, 255));
                break;
            case "pink":
                g.setColor(Color.PINK);
                break;
            case "yellow":
                g.setColor(Color.YELLOW);
                break;
            case "red":
                g.setColor(Color.RED);
                break;
            case "green":
                g.setColor(Color.GREEN);
                break;
            case "black":
                g.setColor(Color.BLACK);
                break;
            default:
                g.setColor(Color.WHITE); //if this happens, somethings fucked up
                break;
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
