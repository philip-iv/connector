package io.connector;

import java.util.Random;

public class SinglePlayerGameController extends GameController {

	public SinglePlayerGameController(GameView view, GameModel model) {
		super(view, model);
	}

	protected boolean placePiece(int col) {
		// Place human's piece
		if (!model.placePiece(col))
			return false;
		
		// If the game is over, skip the AI's turn
		if (model.isOver())
			return true;
		
		// Place AI's piece
		Random r = new Random();
		int AIcol = r.nextInt(7);
		while (!model.placePiece(AIcol))
			AIcol = r.nextInt(7);
		return true;
	}
}
