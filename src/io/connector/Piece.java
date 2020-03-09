package io.connector;

public class Piece {
	private Player owner;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}

	public boolean sameOwner(Piece piece) {
		if (piece == null)
			return false;
		return this.owner.equals(piece.getOwner());
	}
	
	public String toString() {
		return owner.toString().substring(0, 1);
	}
}
