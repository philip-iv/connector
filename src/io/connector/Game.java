package io.connector;

public class Game {
    
    Board board;
    Player[] players = new Player[2];
    int turn;
    

    public Game(Player player1, Player player2)
    {
        board = new Board();
        players[0] = player1;
        players[1] = player2;
        turn = 0;
        board.addPiece(0, player1);
        board.addPiece(1, player2);
        board.addPiece(1, player1);
        board.addPiece(2, player2);
        board.addPiece(2, player2);
        board.addPiece(2, player1);
        board.addPiece(3, player2);
        board.addPiece(3, player2);
        board.addPiece(3, player2);
        board.addPiece(3, player1);
    }
    
    public Player getCurrentPlayer() {
    	return players[turn % 2];
    }
    
    public boolean placePiece(int col)
    {
        boolean result = board.addPiece(col, players[turn % 2]);
        if (result)
        	turn++;
        return result;
    }
    
    public boolean isOver() {
    	return getWinner() != null;
    }
    
    public Player getWinner() {
    	return board.getWinner();
    }
    
    public void printBoard() {
    	System.out.print(board.toString());
    }
    
}
