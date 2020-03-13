package io.connector;

import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public abstract class GameController {
	protected GameView view;
	protected GameModel model;
	private Consumer<Player> onEnd;
	
	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		bindButtons(view.getButtons());
		view.setActivePlayer(model.getCurrentPlayer());
	}
	

	private void bindButtons(JButton[][] buttons) {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				JButton btn = buttons[col][row];
				final int c = col;
				btn.addActionListener(e -> fullTurn(c));
			}
		}
	}
	
	protected void fullTurn(int col) {
		if(placePiece(col)) {
			syncModelView();
			if (model.isOver()) {
				Object[] options = { "Rematch", "Main Menu"};
				int opt = JOptionPane.showOptionDialog(view.getPanel(),
						model.getWinner() + " wins!",
						"Game Over",
						JOptionPane.CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				switch (opt) {
				case 0:
					// Rematch
					model.reset();
					syncModelView();
					break;
				case 1:
					// Main Menu
					onEnd.accept(model.getWinner());
					break;
				}
			}
		}
	}
	
	private void syncModelView() {
		/**
		 * Updates the {@link GameView view} to reflect the state of the
		 * {@link GameModel model}.
		 */
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				JButton btn = view.getButton(col, row);
				Piece p = model.getPiece(col, row);
				if (p != null)
					btn.setText(p.toString());
				else
					btn.setText(" ");
			}
		}
		view.setActivePlayer(model.getCurrentPlayer());
	}
	
	abstract protected boolean placePiece(int col);


	public void onEnd(Consumer<Player> lambda) {
		onEnd = lambda;
	}
}
