package io.connector;

public class GameModel {

	private Piece[][] board = new Piece[7][6];
    private Player[] players = new Player[2];
    private int turn;
    

    public GameModel(Player player1, Player player2)
    {
        players[0] = player1;
        players[1] = player2;
        turn = 0;
    }
    
    public GameModel(String player1, String player2) {
    	this(new Player(player1), new Player(player2));
    }
    
    public Player getCurrentPlayer() {
    	return players[turn % 2];
    }
    
    public boolean placePiece(int col)
    {
    	for (int i = board[col].length - 1; i >= 0; i--) {
			if (board[col][i] == null) {
				board[col][i] = new Piece(players[turn % 2]);
				turn++;
				return true;
			}
		}
		return false;
    }
    
    public boolean isOver() {
    	return getWinner() != null;
    }
    
    public Piece getPiece(int col, int row) {
    	return board[col][row];
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