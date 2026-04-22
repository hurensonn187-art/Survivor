package survivor;

import object.OBJ_Chest;
import object.OBJ_DoorBrick;
import object.OBJ_Key;
import object.OBJ_Portal;
import object.OBJ_SpeedBoots;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject()	{
		
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 19 * gp.tileSize; //Position wo Key liegt
		gp.obj[0].worldY = 19 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 35 * gp.tileSize; 
		gp.obj[1].worldY = 19 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Chest(gp);
		gp.obj[2].worldX = 23 * gp.tileSize; 
		gp.obj[2].worldY = 11 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Portal(gp);
		gp.obj[3].worldX = 16 * gp.tileSize; 
		gp.obj[3].worldY = 14 * gp.tileSize;
		
		gp.obj[4] = new OBJ_DoorBrick(gp);
		gp.obj[4].worldX = 18 * gp.tileSize; 
		gp.obj[4].worldY = 13 * gp.tileSize;
		
		gp.obj[5] = new OBJ_SpeedBoots(gp);
		gp.obj[5].worldX = 20 * gp.tileSize; 
		gp.obj[5].worldY = 15 * gp.tileSize;
	}




}



