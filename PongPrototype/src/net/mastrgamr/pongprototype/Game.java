package net.mastrgamr.pongprototype;

import javax.swing.JFrame;

public class Game extends JFrame {
	
	public Game(){
		super("Pong Prototype");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		add(new Board());
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
