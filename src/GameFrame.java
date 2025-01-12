import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		
		GamePanel gamePanel = new GamePanel();
		this.add(gamePanel);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}
