package weapons;

import java.awt.*;
import survivor.GamePanel;

public class Projectile {
    GamePanel gp;
    public double worldX, worldY;
    double dx, dy; // Flugrichtung
    double speed = 5;
    public int damage = 10;
    int aliveCounter = 0;
    int maxLifetime = 120; // Verschwindet nach 2 Sekunden bei 60 FPS
    public boolean alive = true;

    public Projectile(GamePanel gp, double startX, double startY, String direction) {
        this.gp = gp;
        this.worldX = startX;
        this.worldY = startY;

        // Richtung basierend auf der Blickrichtung des Spielers festlegen
        switch(direction) {
            case "up":    dy = -1; break;
            case "down":  dy = 1;  break;
            case "left":  dx = -1; break;
            case "right": dx = 1;  break;
        }
    }

    public void update() {
        // Projektil bewegen
        worldX += dx * speed;
        worldY += dy * speed;

        // Lebensdauer erhöhen
        aliveCounter++;   //aliveCounter geht hoch bis maxLifetime dann geht projectile weg
        if(aliveCounter >= maxLifetime) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        // Auf dem Bildschirm relativ zum Spieler zeichnen
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(Color.YELLOW);
        g2.fillRect((int)screenX, (int)screenY, 10, 10);
    }
}