package survivor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Font arial_40, arial_80B; // B -> Bold
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = " ";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.0"); //1 Decimalstelle Bei Playtime
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
	}
	public void showMessage(String text) {  //Um Nachrichten erscheinen zu lassen
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics g2) {  //malt Font und Text an der Stelle
		
		if (gameFinished == true) {
			
			String text;
			int textLength;
			int x;
			int y;
			
			g2.setFont(arial_80B);
			g2.setColor(Color.BLUE);
			
			text = "Spiel Vorbei :(";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //gibt Länge von Text	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - gp.tileSize * 2;
			g2.drawString(text, x, y);
			
			g2.setFont(arial_40);
			g2.setColor(Color.RED);
			
			text = "Finale Zeit: " + dFormat.format(playTime);
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //gibt Länge von Text	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + gp.tileSize * 2;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		}else {	
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 90, 70);
			
		//Time
			playTime += (double)1/60;
			g2.drawString("Time " + dFormat.format(playTime), gp.tileSize * 13, 70);
			
		//Nachricht
			if(messageOn == true) {
			
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize * 1, gp.tileSize * 4);
			
				messageCounter ++;
			
				if(messageCounter > 120) {  //lässt nachricht nach 120 Frames verschwinden
				messageCounter = 0;
				messageOn = false;
				}
			}
		}
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
}











