package io.connector;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PieceButton extends JButton {
	private static final Color boardColor = new Color(52, 158, 235);
	private Color pieceColor;
	
	public PieceButton() {
		setOpaque(true);
		pieceColor = getBackground();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Paint the board
		g.setColor(boardColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Paint the piece
		int width = (int) (getWidth() * 0.8);
		int height = (int) (getHeight() * 0.8);
		int x = (getWidth() - width) / 2;
		int y = (getHeight() - height) / 2;
		g.setColor(pieceColor);
		g.fillOval(x, y, width, height);
	}
	
	public void paintBorder(Graphics g) {
		return;
	}
	
	public void setColor(Color c) {
		pieceColor = c;
		repaint();
	}
}
