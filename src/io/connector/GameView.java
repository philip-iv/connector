package io.connector;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameView {
	private JPanel panel;
	private JButton[][] gameCells;
	
	public GameView() {
		panel = new JPanel();
		gameCells = new JButton[7][6]; // col, row
		panel.setLayout(new GridLayout(6, 7));
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				JButton btn = new JButton(" ");
				gameCells[col][row] = btn;
				panel.add(btn);
			}
		}
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public JButton[][] getButtons() {
		return gameCells;
	}
	
	public JButton getButton(int col, int row) {
		return gameCells[col][row];
	}
}
