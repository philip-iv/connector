package io.connector;

public class Board {
	private Piece[][] board;
	
	public Board() {
		board = new Piece[7][6]; // col, row, indexed from top left
	}
	
	public boolean addPiece(int col, Player owner) {
		/**
		 * Attempts to add a new piece owned by owner in the specified column. Columns fill bottom-up.
		 * If the column is full, the method returns false. Otherwise, it returns true.
		 */
		for (int i = board[col].length - 1; i >= 0; i--) {
			if (board[col][i] == null) {
				board[col][i] = new Piece(owner);
				return true;
			}
		}
		return false;
	}
	
	public Player getWinner() {
		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				boolean vertPossible = row <= board[col].length - 4;
				boolean horiPossible = col <= board.length - 4;
				// Check for vertical W
				if (vertPossible) {
					Piece p = board[col][row];
					if (p != null) {
						boolean allSame = true;
						for (int i = 0; i < 4; i++) {
							if (!p.sameOwner(board[col][row+i]))
								allSame = false;
						}
						if (allSame)
							return p.getOwner();
					}
				}
				
				// Check for horizontal W
				if (horiPossible) {
					Piece p = board[col][row];
					if (p != null) {
						boolean allSame = true;
						for (int i = 0; i < 4; i++) {
							if (!p.sameOwner(board[col+i][row]))
								allSame = false;
						}
						if (allSame)
							return p.getOwner();
					}
				}
				
				// Check for down diagonal W
				if (horiPossible && vertPossible) {
					Piece p = board[col][row];
					if (p != null) {
						boolean allSame = true;
						for (int i = 0; i < 4; i++) {
							if (!p.sameOwner(board[col+i][row+i]))
								allSame = false;
						}
						if (allSame)
							return p.getOwner();
					}
				}
				
				// Check for up diagonal W
				if (row - 4 >= 0 && horiPossible) {
					Piece p = board[col][row];
					if (p != null) {
						boolean allSame = true;
						for (int i = 0; i < 4; i++) {
							if (!p.sameOwner(board[col+i][row-i]))
								allSame = false;
						}
						if (allSame)
							return p.getOwner();
					}
				}
			}
		}
		return null;
	}
	
	public String toString() {
		String repr = "";
		for (int row = 0; row < board[0].length; row++) {
			for (int col = 0; col < board.length; col++) {
				Piece piece = board[col][row];
				if (piece != null)
					repr += "[" + piece.toString() + "]";
				else
					repr += "[ ]";
			}
			repr += "\n";
		}
		return repr;
	}
}
