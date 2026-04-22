package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;

public class OBJ_DoorBrick extends SuperObject {

	GamePanel gp;
	
	public OBJ_DoorBrick(GamePanel gp) {
		
		name = "DoorBrick";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TurBrick.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	
}