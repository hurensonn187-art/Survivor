package survivor;

import java.util.ArrayList;

import Entities.Slime;
import survivor.GamePanel;

public class EnemySpawner {

    GamePanel gp;
    int spawnCounter = 0;
    int spawnInterval = 10; // Alle 2 Sekunden bei 60 FPS
    private double spawnMultiplier = 1.0;
    private int totalSpawnedCount = 0;
    private int nextMilestone = 50;

    public EnemySpawner(GamePanel gp) {
        this.gp = gp;
    }

    public void update(ArrayList<Slime> slimes) {
        spawnCounter++;

        if (spawnCounter >= spawnInterval) {

            int enemiesPerSpawn = (int) Math.floor(spawnMultiplier); // Abgerundeter Wert als Anzahl der Schleims für diesen Spawn-Tick

            for (int i = 0; i < enemiesPerSpawn; i++) {
                spawnEnemy(slimes);
            }

            spawnCounter = 0; // Timer zurücksetzen
        }
    }

    // wo und wie ein Gegner entsteht
    private void spawnEnemy(ArrayList<Slime> slimes) {
        // Zufällige Position auf der Map  (Map ist 50x50 groß)
        int minTile = 10;
        int maxTile = 40;

        int randomX = minTile + (int) (Math.random() * (maxTile - minTile));
        int randomY = minTile + (int) (Math.random() * (maxTile - minTile));

        // Neuen Schleim erzeugen und der Liste hinzufügen
        slimes.add(new Slime(gp, randomX, randomY));
        totalSpawnedCount++;

        if (totalSpawnedCount >= nextMilestone) { //alle 50 Kills wird mehr
            nextMilestone += 50;

            spawnMultiplier *= 1.2; //  exponentielles Wachstum, mit der Zeit werden mehr Slimes spawnen um dem Scaling des Spielers entgegenzu wirken

        //System.out.println("Schleim bei " + randomX + ", " + randomY + " gespawnt");
    }
}






