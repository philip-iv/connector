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
		
		// TODO: Handle full board after P1's move
		
		// Place AI's piece
		Random r = new Random();
		int AIcol = r.nextInt(7);
		while (!model.placePiece(AIcol))
			AIcol = r.nextInt(7);
		return true;
	}
}
