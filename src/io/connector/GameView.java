package io.connector;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView {
	private JPanel mainPanel;
	private JPanel boardPanel;
	private JButton[][] gameCells;
	private JLabel activePlayer;
	
	public GameView() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		boardPanel = new JPanel();
		gameCells = new JButton[7][6]; // col, row
		boardPanel.setLayout(new GridLayout(6, 7));
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				JButton btn = new JButton(" ");
				gameCells[col][row] = btn;
				boardPanel.add(btn);
			}
		}
		mainPanel.add(boardPanel, BorderLayout.CENTER);

		activePlayer = new JLabel();
		mainPanel.add(activePlayer, BorderLayout.NORTH);
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
	
	public JButton[][] getButtons() {
		return gameCells;
	}
	
	public JButton getButton(int col, int row) {
		return gameCells[col][row];
	}

	public void setActivePlayer(Player p) {
		activePlayer.setText("Current Player: " + p);
	}
}
