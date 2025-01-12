import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener{
	
	Image pozadina;
	static final int screenWidth = 1280;
	static final int screenHeight = 720;
	static final int windowWidth = 600;
	static final int windowHeight = 600;
	int startDrawingX = 340;
	int startDrawingY = 60;
	static final int unitSize = 25;
	static final int gameUnits = (windowWidth * windowHeight) / unitSize;
	int delay = 100;
	final int x[] = new int[gameUnits];
	final int y[] = new int[gameUnits];
	int bodyParts = 4;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	boolean pause;
	Timer timer;
	Random random;
	

	public GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		pozadina = new ImageIcon("res/pozadina2.jpg").getImage();
		this.setBackground(Color.darkGray);
		this.setFocusable(true);
		this.addKeyListener(new KeyHandler(this));
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void pauseGame() {
		pause = true;
		timer.stop();
	}
	
	public void resumeGame() {
		pause = false;
		timer.start();
	}
	
	public void move() {
		
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - unitSize;
			break;
		case 'D':
			y[0] = y[0] + unitSize;
			break;
		case 'R':
			x[0] = x[0] + unitSize;
			break;
		case 'L':
			x[0] = x[0] - unitSize;
			break;
		}
		
	}
	
	public void newApple() {
		
		int randomCol = random.nextInt((int)(windowWidth/unitSize));
		int randomRow = random.nextInt((int)(windowHeight/unitSize));
		
		appleX = startDrawingX + randomCol * unitSize;
		appleY = startDrawingY + randomRow * unitSize;
		
	}
	
	public void checkApple() {
		
		if((x[0] + startDrawingX == appleX) && (y[0] + startDrawingY == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
		
	}
	
	
	public void chechCollisions() {
		
		for (int i = bodyParts; i > 0; i--) {
	        if ((x[0] + startDrawingX) == (x[i] + startDrawingX) &&
	            (y[0] + startDrawingY) == (y[i] + startDrawingY)) {
	            running = false;
	        }
	    }
		
		 if (x[0] + startDrawingX < startDrawingX || 
			 x[0] + startDrawingX >= startDrawingX + windowWidth || 
			 y[0] + startDrawingY < startDrawingY || 
			 y[0] + startDrawingY >= startDrawingY + windowHeight) {
			 running = false;
			 }
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(pozadina, 0, 0, screenWidth, screenHeight, this);
		draw((Graphics2D)g);
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(70, 70, 70));
		for(int i = 0; i < unitSize; i++) {
			g.drawLine(startDrawingX, i * unitSize + startDrawingY, startDrawingX + windowWidth, i * unitSize + startDrawingY);
			g.drawLine(startDrawingX + i * unitSize, startDrawingY, startDrawingX + i * unitSize, startDrawingY + windowHeight);
		}
		
		for(int i = 0; i < bodyParts; i++) {
			
			if(i == 0) {
				g.setColor(Color.green);
				g.fillRect(x[i]+340, y[i]+60, unitSize, unitSize);
			} else {
				Image telo = new ImageIcon("res/telo.png").getImage();
				//g.setColor(new Color(45, 180, 0));
				//g.fillRect(x[i]+340, y[i]+60, unitSize, unitSize);
				g.drawImage(telo, x[i]+340, y[i]+60, unitSize, unitSize, this);
			}
			
		}
		
		//jabuka
		Image appleImage = new ImageIcon("res/jabuka.png").getImage();
		g.drawImage(appleImage, appleX, appleY, unitSize, unitSize, this);
		
		g.setColor(Color.orange);
		g.setStroke(new BasicStroke(5));
		g.drawRect(startDrawingX, startDrawingY, windowWidth, windowHeight);
		
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.PLAIN, 70));
		g.drawString("Score", 1000, 100);
		g.setFont(new Font("Rubik Vinyl", Font.BOLD, 120));
		//g.drawString("" + applesEaten, 1050, 210);
		if(applesEaten > 9) {
			g.drawString("" + applesEaten, 1020, 210);
		} else {
			g.drawString("" + applesEaten, 1060, 210);
		}
		
		if(!running) {
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, 150));
			g.drawString("GAME OVER", startDrawingX - 150, startDrawingY + 300);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			chechCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			
		}
		
	}
	
}
