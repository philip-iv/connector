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
				String winnerText;
				if (model.isWinner())
					winnerText = model.getWinner() + " wins!";
				else
					winnerText = "The game is a draw, nobody wins.";
				Object[] options = { "Rematch", "Main Menu"};
				int opt = JOptionPane.showOptionDialog(view.getPanel(),
						winnerText,
						"Game Over",
						JOptionPane.CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				switch (opt) {
				case 0: // Rematch
					model.reset();
					syncModelView();
					break;
				case -1: // Exit the dialog without clicking a button
				case 1: // Main Menu
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
				PieceButton btn = view.getButton(col, row);
				Piece p = model.getPiece(col, row);
				if (p != null)
					btn.setColor(p.getOwner().getColor());
				else
					btn.setColor(btn.getBackground());
			}
		}
		view.setActivePlayer(model.getCurrentPlayer());
	}
	
	abstract protected boolean placePiece(int col);


	public void onEnd(Consumer<Player> lambda) {
		onEnd = lambda;
	}
}
