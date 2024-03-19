import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends JPanel{
    private final String COLOR;
    private DragonTower dTower;
    private boolean selected;

    public Square(String color, DragonTower dTower) {
        COLOR = color;
        this.dTower = dTower;
        selected = false;
    }

    public void setSelected(boolean s) {
        selected = s;
    }

    public String getColor() {
        return COLOR;
    }

    public DragonTower getDragonTower() {
        return dTower;
    }

    public void setDragonTower(String color, String team_color){
        dTower = new DragonTower(color, team_color);
    }

    public void setDragonTower(DragonTower d) {
        dTower = d;
    }

    public void resetdTower(){
        dTower = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        setColor(g2D);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        if(dTower != null) {
            if(dTower.getTeam() == "black")
                g2D.setColor(Color.BLACK);
            else
                g2D.setColor(Color.WHITE);
            g2D.fillOval(9, 9, 50, 50);
            if(selected)
                g2D.setColor(Color.LIGHT_GRAY);
            else
                g2D.setColor(Color.DARK_GRAY);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawOval(9, 9, 50, 50);
            setColor(g2D);
            
            g2D.drawString(getSymbol(), 25, 25);
        }
    }

    private void setColor(Graphics2D g2D) {
        switch(COLOR) {
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
                g2D.setColor(new Color(255, 222, 0));
                break;
            case "red":
                g2D.setColor(new Color(238, 58, 67));
                break;
            case "green":
                g2D.setColor(new Color(0, 162, 95));
                break;
            case "black":
                g2D.setColor(new Color(45, 45, 45));
                break;
            default:
                g2D.setColor(new Color(230, 230, 230)); //if this happens, somethings fucked up
                break;
        }
    }

    private String getSymbol() {
        switch(COLOR) {
            case "orange":
                return "";
            case "blue":
                return "";
            case "purple":
                return "";
            case "pink":
                return "";
            case "yellow":
                return "";
            case "red":
                return "紅";
            case "green":
                return "緑";
            case "black":
                return "";
            default:
                return "";
        }
    }
}