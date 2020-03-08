package io.connector;

public class Board {
	private Piece[][] board;
	
	public Board() {
		board = new Piece[6][7];
	}
	
	public boolean addPiece(int col, Player owner) {
		/**
		 * Attempts to add a new piece owned by owner in the specified column. Columns fill bottom-up.
		 * If the column is full, the method returns false. Otherwise, it returns true.
		 */
		for (int i = 0; i <= 7; i++) {
			if (board[col][i] == null) {
				board[col][i] = new Piece(owner);
				return true;
			}
		}
		return false;
	}
}
