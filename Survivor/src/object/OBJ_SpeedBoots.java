package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SpeedBoots extends SuperObject {

	public OBJ_SpeedBoots() {
		
		name = "SpeedBoots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/SchuheSchnell.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

