package io.connector;

public class TwoPlayerGameController extends GameController {

	public TwoPlayerGameController(GameView view, GameModel model) {
		super(view, model);
	}

	protected boolean placePiece(int col) {
		return model.placePiece(col);
	}
}
