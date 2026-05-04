package Entities;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;
import survivor.KeyHandler;
import survivor.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public int hasKey = 0; // wieviele Schlüssel Objects hat der Spieler eingesammelt
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2); //spieler immer in der Mitte vom Bildschirm
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();	//collision box
		solidArea.x = 6 * gp.scale;  //6 ist Position des Pixels der CollisionBox beim Player.png
		solidArea.y = 8 * gp.scale; 
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 6 * gp.scale ; 
		solidArea.height = 9 * gp.scale;
	
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 25; // mittig der Map bei 50x50 Grosser map
		worldY = gp.tileSize * 26;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		up1 = setup("PlayerUp1");
		up2 = setup("PlayerUp2");
		down1 = setup("PlayerDown1");
		down2 = setup("PlayerDown2");
		left1 = setup("PlayerLeft1");
		left2 = setup("PlayerLeft2");
		right1 = setup("PlayerRight1");
		right2 = setup("PlayerRight2");
		//System.out.println("klappt Spieler");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
			try {
			
				image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
				image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
				
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("Hilfe");
			}
			return image;
	}
	public void update() { //updated 60 mal pro Sekunde
	
		
		// Spieler bewegen mit Tastendruck
		// Bewegung funktioniert nur wenn cChecker false raus gibt
		
				double dx = 0;
				double dy = 0;
				
				collisionOn = false;
				gp.cChecker.checkTile(this);			
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);
		
				if(keyH.upPressed == true || keyH.downPressed == true ||
						keyH.leftPressed == true || keyH.rightPressed == true) { //damit sprite sich nicht ändert während man nichts drückt
					
					
					if(keyH.upPressed == true) {  
						direction = "up";		
						if(collisionOn == false) {
							dy--;
						}
						//
						// System.out.println("h");
					}
					if(keyH.downPressed == true) {
						direction = "down";	
						if(collisionOn == false) {
							dy++;
						}
						//System.out.println("u");
					}
					if(keyH.leftPressed == true) {
						direction = "left";		
						if(collisionOn == false) {
							dx--;
						}
						//System.out.println("l");
					}
					if(keyH.rightPressed == true) {
						direction = "right";	
						if(collisionOn == false) {
							dx++;
						}
						//System.out.println("r");
					}
					
					
					double length = Math.sqrt(dx * dx + dy * dy); //sorgt dafür das diagonales Laufen
					  											  //nicht schneller als Horizontales ist
					if(length != 0) {
						dx /= length; //macht das der Bewegungsvektor bei Diagonal 0.707 ist 
						dy /= length;

						worldX += dx * speed;
						worldY+= dy * speed;
					}
					spriteCounter++;
				}
					
					
					
					if(spriteCounter > 15 ) { //alle 15 Updates wechselt Sprite
						if(spriteNum == 1) {
							spriteNum = 2;
							
						}else if(spriteNum == 2) {
							spriteNum = 1;
						}
						spriteCounter = 0;
					}
					
			}
						
				
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSoundEffect(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("Key erhalten!");
				//System.out.println("Key:" + hasKey);
				break;
			case "DoorBrick":
				if(hasKey > 0) {
				hasKey--;
				gp.obj[i] = null;
				gp.ui.showMessage("Tür offen");
				}else {
					gp.ui.showMessage("Wo ist dein Key?");
				}
				//System.out.println("Key:" + hasKey);
				break;
			case "SpeedBoots":
				speed += 10;
				gp.obj[i] = null;
				break;
			case "Chest":  //TEST spiel beendet wenn Kiste offen
				gp.ui.gameFinished = true;
				gp.playMusic(0);
				
			}
			
			//gp.obj[i] = null; //entfernt das Object
		}
	}
	
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		default:
			image = down1; //falls was schiefgeht
			break;
		}

		g2.drawImage(image,Math.round(screenX), Math.round(screenY), null);
		System.out.println(screenX + "   " +  screenY);

		//g2.setColor(Color.RED);    //Malt CollisionBox
		//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
