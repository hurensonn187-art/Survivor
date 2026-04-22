package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;

public class OBJ_SpeedBoots extends SuperObject {

	GamePanel gp;
	
	public OBJ_SpeedBoots(GamePanel gp) {
		
		name = "SpeedBoots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/SchuheSchnell.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

