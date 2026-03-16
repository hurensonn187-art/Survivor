package survivor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{  // Alles was mit Tastendrücken zu tun hat

	public boolean upPressed, downPressed, leftPressed, rightPressed, upLeftPressed, upRightPressed, downLeftPressed, downRightPressed; 
	
	
	public void keyTyped(KeyEvent e) { //merkt wenn eine Taste runtergedrückt wird
	}

	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
				
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;			
		}		
		if(code == KeyEvent.VK_S) {
			downPressed = true;			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			
		}
		
	}

	
	public void keyReleased(KeyEvent e) {  //merkt wenn eine Taste losgelassen wird
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
