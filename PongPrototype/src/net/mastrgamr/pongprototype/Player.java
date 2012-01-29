package net.mastrgamr.pongprototype;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
	
	public Player(){ }
	
	public Player(String fileLoc, int x, int y){
		super(fileLoc, x, y);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public void update(Rectangle windowBounds) {
		if(y < 0)
			y = 0;
		if(y > (int)windowBounds.getHeight() - this.getImageHeight())
			y = (int)windowBounds.getHeight() - this.getImageHeight();
		
		y += speed;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			speed = -3;
			break;
		case KeyEvent.VK_DOWN:
			speed = 3;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			speed = 0;
			break;
		case KeyEvent.VK_DOWN:
			speed = 0;
			break;
		}
	}
}
