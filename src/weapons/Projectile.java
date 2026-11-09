package weapons;

import java.awt.*;
import survivor.GamePanel;
public class Projectile {

    GamePanel gp;
    public double worldX, worldY;
    public double dx = 0, dy = 0; // Flugrichtung als Richtungsvektor
    double speed = 5;
    public int damage = 10;
    int aliveCounter = 0;
    int maxLifetime = 120;
    public boolean alive = true;

    //Wie oft darf das Projektil treffen, bevor es verschwindet?
    public int pierceHits;

    public Projectile(GamePanel gp, double startX, double startY, double dx, double dy) {
        this.gp = gp;
        this.worldX = startX;
        this.worldY = startY;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        worldX += dx * speed;
        worldY += dy * speed;

        aliveCounter++;
        if (aliveCounter >= maxLifetime) {
            alive = false;
        }
    }



    public void draw(Graphics2D g2) {
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(Color.RED);
        g2.fillRect((int)screenX, (int)screenY, 10, 10);
    }
}