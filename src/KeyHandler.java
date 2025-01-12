import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public static boolean upPressed;
	public static boolean downPressed;
	public static boolean leftPressed;
	public static boolean rightPressed;
	public static boolean pausePressed;
	
	GamePanel gamePanel;
	
	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			if(gamePanel.direction != 'D') {
				upPressed = true;
				gamePanel.direction = 'U';
			}
		}
		
		if(code == KeyEvent.VK_S) {
			if(gamePanel.direction != 'U') {
				downPressed = true;
				gamePanel.direction = 'D';
			}
		}
		
		if(code == KeyEvent.VK_A) {
			if(gamePanel.direction != 'R') {
				leftPressed = true;
				gamePanel.direction = 'L';
			}
		}
		
		if(code == KeyEvent.VK_D) {
			if(gamePanel.direction != 'L') {
				rightPressed = true;
				gamePanel.direction = 'R';
			}
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			
			if(gamePanel.pause) {
				gamePanel.resumeGame();
			} else {
				gamePanel.pauseGame();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
