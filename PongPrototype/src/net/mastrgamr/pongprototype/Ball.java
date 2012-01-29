package net.mastrgamr.pongprototype;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends Sprite {
	
	private int speedX;
	private int speedY;
	
	public Ball(){ }
	
	public Ball(String fileLoc, int x, int y, int speed){
		super(fileLoc, x, y, speed);
		
		this.speedX = speed;
		this.speedY = speed;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public void update(Rectangle windowBounds) {
		if(x < 0){
			x = (int) (windowBounds.getWidth() / 2 - this.getImageWidth() / 2);
			y = (int) (windowBounds.getHeight() / 2 - this.getImageHeight() / 2);
			Board.playerScore++;
			Board.isPlaying = false;
		}
		if(y < 0)
			speedY *= -1;
		if(x > windowBounds.getWidth() - this.getImageWidth()){
			x = (int) (windowBounds.getWidth() / 2 - this.getImageWidth() / 2);
			y = (int) (windowBounds.getHeight() / 2 - this.getImageHeight() / 2);
			Board.enemyScore++;
			Board.isPlaying = false;
		}
		if(y > windowBounds.getHeight() - this.getImageHeight())
			speedY *= -1;
		
		x += speedX;
		y += speedY;
	}
	
	public void reverseSpeedX(){ this.speedX *= -1; }
}
