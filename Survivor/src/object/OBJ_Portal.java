package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;

public class OBJ_Portal extends SuperObject {

	GamePanel gp;
	
	public OBJ_Portal(GamePanel gp) {
		
		name = "Portal";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Portal.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}