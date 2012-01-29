package net.mastrgamr.pongprototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	
	private Rectangle windowBounds;
	private BufferedImage background;
	private Thread animate;
	private final int DELAY = 25;
	
	public static boolean isPlaying = false;
	public static int playerScore = 0;
	public static int enemyScore = 0;
	
	private Player player;
	private Enemy enemy;
	private Ball ball;
	
	public Board(){
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 340));
		setBounds(0, 0, 600, 340);
		addKeyListener(new KeyAdapter());
		setFocusable(true);
		initialize();
	}
	
	public void initialize(){
		try {
			background = ImageIO.read(new File("images/background.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not load background image!");
			System.exit(0);
		}
		
		player = new Player("images/paddle.png", (int)(this.getBounds().getX()), (int)(this.getBounds().getHeight() / 2 - 50));
		enemy = new Enemy("images/paddle.png", (int)(this.getBounds().getWidth() - 20), (int)(this.getBounds().getHeight() / 2 - 50), 2);
		ball = new Ball("images/ball.png", (int)(this.getBounds().getWidth() / 2), (int)(this.getBounds().getHeight() / 2), 3);
		
		windowBounds = this.getBounds();
		
		animate = new Thread(this);
		animate.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(background, 0, 0, null);
		g2d.setColor(Color.WHITE);
		g2d.drawString("" + playerScore, (int) (this.getBounds().getWidth() / 2) - 14, 14);
		g2d.drawString("" + enemyScore, (int) (this.getBounds().getWidth() / 2) + 5, 14);
		
		player.draw(g2d);
		enemy.draw(g2d);
		ball.draw(g2d);
	}
	
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		while(true){
			if(isPlaying){
				player.update(windowBounds);
				enemy.update(windowBounds, ball);
				ball.update(windowBounds);
				if(ball.getRect().intersects(player.getRect()) ^ ball.getRect().intersects(enemy.getRect()))
					ball.reverseSpeedX();
				repaint();
			}
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = timeDiff - DELAY;
			
			if(sleep < 0)
				sleep = 5;
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) { } //there should not be an Exception thrown
			
			beforeTime = System.currentTimeMillis();
		}
	}
	
	private class KeyAdapter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) { 
			player.keyPressed(e);
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(!isPlaying)
					isPlaying = true;
				else
					isPlaying = false;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) { player.keyReleased(e); }
		@Override
		public void keyTyped(KeyEvent e) { }
	}
}
