package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_DoorBrick extends SuperObject {

	public OBJ_DoorBrick() {
		
		name = "DoorBrick";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TurBrick.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	
}