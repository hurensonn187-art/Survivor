package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;

public class OBJ_Key extends SuperObject {

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/GoldKeyGros.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}


