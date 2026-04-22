package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import survivor.GamePanel;
import survivor.UtilityTool;

public class TileManager {

	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];	//nummer versch. an Tiles (ändern vllt)
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/mapWelt1.txt");
	}
	
	public void getTileImage() { //Holt sich die Bilder der Tiles
		
			setup(0, "Background1Gras", false);
			setup(1, "BackgroundTest2_2", true);
			setup(2, "Background3WasserStill", true);
	
	}
	
	public void setup(int index, String imageName, boolean collision){  //scaled die Images damit es nicht im gameLoop gemacht wird und mehr Leistung zieht
		
		UtilityTool uTool = new UtilityTool();
		
		try{
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void loadMap(String filePath) {
		
		try { 
	
			InputStream is = getClass().getResourceAsStream(filePath); //lädt txt.file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); //liest txt.file
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine(); //liest eine line bis alle abgelaufen sind
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" "); // macht aus der Linie an Zahlen einzelne
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;										
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;  //passt Coordinaten an tilesize(64) an
			int worldY = worldRow * gp.tileSize;
			double screenX = worldX - gp.player.worldX + gp.player.screenX; //sagt wo die tiles an den screen gepackt werden
			double screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				 
				g2.drawImage(tile[tileNum].image,(int) screenX,(int) screenY, null);
				
			} //rendert nur die Tiles die um einen herum sind und nicht alle 
			worldCol++;		
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
				
			}
		}
		
		
	}
	
}














