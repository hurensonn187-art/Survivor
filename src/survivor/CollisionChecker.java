package survivor;

import Entities.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int)entity.worldX + entity.solidArea.x;			//Koordinaten der CollisionBox, ein Rechteck um den Spieler oder anderen Sachen(-> Entities)
		int entityRightWorldX = (int)entity.worldX + entity.solidArea.x + entity.solidArea.width;  // Die 4 Eckpunkte
		int entityTopWorldY = (int)entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = (int)entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize; //4 Seiten
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;


		switch(entity.direction) {
		
		case "up": //weiß wohin gelaufen wird, damit man weniger collision checken muss, jeder case schaut nur 2 Punkte an und nicht alle 4
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // schaut sich auf der Tilemap 2 Tiles an, an welche die Figur läuft
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { // wenn diese 2 Tiles Kollision haben wird der Bewegungsvorgang gestoppt
				entity.collisionOn = true;
			}		
			break;
			
		case "down":
			entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize; 
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize; 
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; 
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX - entity.speed)/gp.tileSize; 
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	public int checkObject(Entity entity,boolean player) {   //wird nicht genutzt -> Assetsetter
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x); //entity x und y bekommen
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				gp.obj[i].solidArea.x = (int) (gp.obj[i].worldX + gp.obj[i].solidArea.x); //object x und y bekommen
				gp.obj[i].solidArea.y = (int) (gp.obj[i].worldY + gp.obj[i].solidArea.y);
				
				switch(entity.direction) {
				case "up" :
					entity.solidArea.y -= entity.speed; //position von entity nach der Bewegung
					if(entity.solidArea.intersects(gp.obj[i].solidArea)){ //wenn CollsionBox von entity mit der von obj überschneidet
						//System.out.println("up collision");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) { //damit andere Entities nicht mit Object interagieren können
							index = i; 
						}
					}
					break;
				case "down" :
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)){ 
						//System.out.println("down collision");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) { //damit andere Entities nicht mit Object interagieren können
							index = i; 
						}
					}
					break;
				case "left" :
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)){ 
						//System.out.println("left collision");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) { //damit andere Entities nicht mit Object interagieren können
							index = i; 
						}
					}
					break;
				case "right" :
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)){
						//System.out.println("right collision");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) { //damit andere Entities nicht mit Object interagieren können
							index = i; 
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX; //resettet damit nicht immer wieder addiert wird am Anfang der Methode
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
}






























