package io.connector;

public class Board {
	private Piece[][] board;
	
	public Board() {
		board = new Piece[7][6]; // col, row
	}
	
	public boolean addPiece(int col, Player owner) {
		/**
		 * Attempts to add a new piece owned by owner in the specified column. Columns fill bottom-up.
		 * If the column is full, the method returns false. Otherwise, it returns true.
		 */
		for (int i = 0; i <= board[col].length; i++) {
			if (board[col][i] == null) {
				board[col][i] = new Piece(owner);
				return true;
			}
		}
		return false;
	}
}
