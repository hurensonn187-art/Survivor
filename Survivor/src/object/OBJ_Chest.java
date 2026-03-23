package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;

public class OBJ_Chest extends SuperObject {

	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		
		name = "Chest";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TruheNormal.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}