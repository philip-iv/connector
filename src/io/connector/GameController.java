package io.connector;

import java.util.function.Consumer;

import javax.swing.JButton;

public abstract class GameController {
	protected GameView view;
	protected GameModel model;
	private Consumer<Player> onEnd;
	
	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		bindButtons(view.getButtons());
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
				onEnd.accept(model.getWinner());
			}
		}
	}
	
	private void syncModelView() {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				JButton btn = view.getButton(col, row);
				Piece p = model.getPiece(col, row);
				if (p != null)
					btn.setText(p.toString());
			}
		}
	}
	
	abstract protected boolean placePiece(int col);


	public void onEnd(Consumer<Player> lambda) {
		onEnd = lambda;
	}
}
