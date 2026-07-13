package survivor;

import java.util.ArrayList;

import Entities.Slime;
import survivor.GamePanel;

public class EnemySpawner {

    GamePanel gp;
    int spawnCounter = 0;
    int spawnInterval = 10; // Alle 2 Sekunden bei 60 FPS  // noch ändern!!!

    public EnemySpawner(GamePanel gp) {
        this.gp = gp;
    }

    public void update(ArrayList<Slime> slimes) {
        spawnCounter++;

        if (spawnCounter >= spawnInterval) {
            spawnEnemy(slimes);
            spawnCounter = 0; // Timer zurücksetzen
        }
    }

    // wo und wie ein Gegner entsteht
    private void spawnEnemy(ArrayList<Slime> slimes) {
        // Zufällige Position auf der Map  (map ist 50x50 groß)
        int minTile = 10;
        int maxTile = 40;

        int randomX = minTile + (int) (Math.random() * (maxTile - minTile));
        int randomY = minTile + (int) (Math.random() * (maxTile - minTile));

        // Neuen Schleim erzeugen und der Liste hinzufügen
        slimes.add(new Slime(gp, randomX, randomY));

        //System.out.println("Schleim bei " + randomX + ", " + randomY + " gespawnt");
    }
}






