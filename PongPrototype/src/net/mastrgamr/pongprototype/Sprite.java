package net.mastrgamr.pongprototype;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Sprite {
	
	protected int x;
	protected int y;
	protected int speed;
	protected BufferedImage img;
	
	public Sprite(){ }
	
	public Sprite(String fileLoc, int x, int y){
		this.x = x;
		this.y = y;
		loadImage(fileLoc);
	}
	
	public Sprite(String fileLoc, int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		loadImage(fileLoc);
	}
	
	public void loadImage(String fileLoc){
		try {
			img = ImageIO.read(new File(fileLoc));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not load images!");
			System.exit(0);
		}
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update(Rectangle windowBounds);
	
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	public int getImageWidth(){ return this.img.getWidth(); }
	public int getImageHeight(){ return this.img.getHeight(); }
	public Rectangle getRect(){
		return new Rectangle(
				this.x,
				this.y,
				this.img.getWidth(),
				this.img.getHeight());
	}
	
	public void update(Rectangle windowBounds, Ball ball) { }
}
