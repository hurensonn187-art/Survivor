package survivor;

import Entities.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int)entity.worldX + entity.solidArea.x;			//Koordinaten der KollisionsBox
		int entityRightWorldX = (int)entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = (int)entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = (int)entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; //"predicted" wohin gelaufen wird, damit man weniger
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // collision checken muss und schaut nur 2 Punkte an,
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];// Oben links und oben rechts der Box
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		case "down":
			break;
		case "left":
			break;
		case "right":
			break;
		}
	}
	
}
