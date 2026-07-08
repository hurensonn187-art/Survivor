package survivor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entities.Player;
import Entities.Slime;
import object.SuperObject;
import survivor.EnemySpawner;
import tiles.TileManager;
import weapons.Projectile;


public class GamePanel extends JPanel implements Runnable{

	//Bildschirm Einstellungen
	final int originalTileSize = 16;
	public final int scale = 4;
	
	public final int tileSize = originalTileSize * scale; //testen und verändern auf 1080 1920
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 9;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	// Welt Einstellungen
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWith = tileSize * maxWorldCol;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	public int FPS = 60;
	
	//System
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	Thread gameThread;
	public UI ui = new UI(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);

	public EnemySpawner spawner = new EnemySpawner(this);
	
	//Entities und Objects
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	public ArrayList<Slime> slimes = new ArrayList<>();

	//Waffen
	public ArrayList<Projectile> projectiles = new ArrayList<>();
	int weaponAttackCounter = 0;
	int weaponCooldown = 60; // Schießt jede Sekunde (bei 60 FPS)
	
	
	
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.LIGHT_GRAY);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame()	{   //platziert alle Items
		aSetter.setObject();
		//playMusic(0); // -> sound array [1] -> BackgroundSongTest
	}
	
	
	// Gameloop machen 
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() { // updaten und screen drawen
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) { //while gameThread läuft immer
			
			
			
			update();// updated Informationen
			
			repaint();// malt die Informationen
				
				
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();	
				remainingTime = remainingTime/1000000;
				
				if(remainingTime <0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime); //hält den Thread an damit er in 60fps läuft und nicht so schnell wie der pc kann
				
				nextDrawTime = nextDrawTime + drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
			
	}

	public void update() {
		
		player.update();

		// --- WAFFENSYSTEM AUTOMATISCHES FEUERN ---
		weaponAttackCounter++;
		if(weaponAttackCounter >= weaponCooldown) {
			// Ein Projektil an der Position des Spielers in seine Blickrichtung abfeuern
			projectiles.add(new Projectile(this, player.worldX, player.worldY, player.direction));
			weaponAttackCounter = 0; // Cooldown zurücksetzen
		}

		//Projectiles
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			p.update();

			// Prüfen ob das Projektil einen Schleim trifft
			for (int j = 0; j < slimes.size(); j++) {
				Slime s = slimes.get(j);

				double xDist = p.worldX - s.worldX;
				double yDist = p.worldY - s.worldY;
				double distSq = (xDist * xDist) + (yDist * yDist);
				double hitRadius = 24; // Treffer-Radius

				if (distSq < (hitRadius * hitRadius)) {
					// Schleim verliert Leben (muss noch gemacht werden
					// s.healthPoints -= p.damage;

					// Schleim testweise direkt löschen, wenn getroffen:
					slimes.remove(j);

					p.alive = false; // Projektil zerstören
					break;
				}
			}

			// Wenn das Projektil abgelaufen oder getroffen hat, aus Liste entfernen
			if (!p.alive) {
				projectiles.remove(i);
				i--; // Index anpassen
			}
		}


		//Spawner Slimes
		spawner.update(slimes); //updated die Slimes (Spawncounter usw.)

		for (int i = 0; i < slimes.size(); i++) { //Bewegt alle Schleime
			if (slimes.get(i) != null) {  //
				slimes.get(i).moveSlime();
			}
		}
	}
	
	public void paintComponent(Graphics g) { //Paint Component um alles zu malen
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Debug
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
		drawStart = System.nanoTime();  //zeigt an wieviel Zeit pro Update vergeht
		}
		
		//Tile
		tileM.draw(g2);
		
		//Objects
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//Player
		player.draw(g2);

		//Projectile
		for (Projectile p : projectiles) { p.draw(g2); }

		//Enemies
		for (int i = 0; i < slimes.size(); i++) {
			slimes.get(i).draw(g2);
		}
		//UI
		ui.draw(g2);
		
		//debug
		if(keyH.checkDrawTime == true) {
		long drawEnd = System.nanoTime();
		long passed =drawEnd - drawStart;
		g2.setColor(Color.white);
		g2.drawString("drawTime: " + passed, 10, 400);
		System.out.println(passed);
		
		}
		
		g2.dispose();
	}

	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}
	public void playSoundEffect(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}


}



















