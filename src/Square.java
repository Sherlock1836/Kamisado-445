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
                g.setColor(new Color(245, 132, 40));
                break;
            case "blue":
                g.setColor(new Color(0, 121, 194));
                break;
            case "purple":
                g.setColor(new Color(124, 66, 153));
                break;
            case "pink":
                g.setColor(new Color(239, 128, 179));
                break;
            case "yellow":
                g.setColor(new Color(255, 222, 0));
                break;
            case "red":
                g.setColor(new Color(238, 58, 67));
                break;
            case "green":
                g.setColor(new Color(0, 162, 95));
                break;
            case "black":
                g.setColor(new Color(45, 45, 45));
                break;
            default:
                g.setColor(new Color(230, 230, 230)); //if this happens, somethings fucked up
                break;
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
