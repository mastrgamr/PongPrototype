package net.mastrgamr.pongprototype;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy extends Sprite {
	
	public Enemy(){ }
	
	public Enemy(String fileLoc, int x, int y, int speed){
		super(fileLoc, x, y, speed);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public void update(Rectangle windowBounds, Ball ball) {
		if(y < 0)
			y = 0;
		if(y > (int)windowBounds.getHeight() - this.getImageHeight())
			y = (int)windowBounds.getHeight() - this.getImageHeight();
		
		if(ball.getY() < this.getY() && ball.getX() > (int)windowBounds.getWidth() / 2)
			y -= speed;
		if(ball.getY() > this.getY() && ball.getX() > (int)windowBounds.getWidth() / 2)
			y += speed;
	}

	@Override
	public void update(Rectangle windowBounds) {
		// TODO Auto-generated method stub
		
	}
}
