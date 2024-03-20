import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Square extends JPanel {
    private final String SQUARE_COLOR;
    private DragonTower dTower;
    private boolean selected;
    private int row;
    private int column;

    public Square(String color, DragonTower dTower, int rowY, int columnX) {
        SQUARE_COLOR = color;
        this.dTower = dTower;
        selected = false;
        row = rowY;
        column = columnX;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setSelected(boolean s) {
        selected = s;
    }

    public String getColor() {
        return SQUARE_COLOR;
    }

    public DragonTower getDragonTower() {
        return dTower;
    }

    public void setDragonTower(String color, String team_color) {
        dTower = new DragonTower(color, team_color);
    }

    public void setDragonTower(DragonTower d) {
        dTower = d;
    }

    public void resetdTower() {
        dTower = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        setColor(g2D, SQUARE_COLOR);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        if (dTower != null) {
            if (dTower.getTeam() == "black")
                g2D.setColor(Color.BLACK);
            else
                g2D.setColor(new Color(245, 245, 245));
            g2D.fillOval(9, 9, 50, 50);
            if (selected)
                g2D.setColor(Color.LIGHT_GRAY);
            else
                g2D.setColor(Color.DARK_GRAY);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawOval(9, 9, 50, 50);
            setColor(g2D, dTower.getColor());
            g2D.setFont(new Font("SansSerif", Font.BOLD, 23));
            ;
            g2D.drawString(getSymbol(), 22, 41);
        }
    }

    private void setColor(Graphics2D g2D, String color) {
        switch (color) {
            case "orange":
                g2D.setColor(new Color(245, 132, 40));
                break;
            case "blue":
                g2D.setColor(new Color(0, 121, 194));
                break;
            case "purple":
                g2D.setColor(new Color(124, 66, 153));
                break;
            case "pink":
                g2D.setColor(new Color(239, 128, 179));
                break;
            case "yellow":
                g2D.setColor(new Color(235, 202, 0));
                break;
            case "red":
                g2D.setColor(new Color(238, 58, 67));
                break;
            case "green":
                g2D.setColor(new Color(0, 162, 95));
                break;
            case "brown":
                g2D.setColor(new Color(84, 63, 48));
                break;
            default:
                g2D.setColor(new Color(230, 230, 230)); // if this happens, somethings fucked up
                break;
        }
    }

    private String getSymbol() {
        switch (dTower.getColor()) {
            case "orange":
                return "橙";
            case "blue":
                return "藍";
            case "purple":
                return "紫";
            case "pink":
                return "桃";
            case "yellow":
                return "黄";
            case "red":
                return "紅";
            case "green":
                return "緑";
            case "brown":
                return "褐";
            default:
                return "";
        }
    }
}