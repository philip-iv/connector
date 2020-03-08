package io.connector;

public class Game {
    
    Board board;
    Player player1;
    Player player2;

    //intitalize game controller
    public Game(Board board, Player player1, Player player2)
    {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        int turn = 1;
    }     
    
    //change turn 
    public void changeTurn(){
        if (turn == 1)
        {
            turn = 2;
        }
        else
        {
            turn = 1
        }
    }
    
    //place piece
    //Takes onClick event and sends it to the board
    //If it is a legal move, perform action
    //Otherwise, have the player go again
    public bool checkLegal()
    {
        return board.addPiece();
        
    }
    
    //listens for player to place a piece
    public void placePiece()
    {
        
    }
    
    
    //Player Turn
    public void playerTurn()
    {
        
        //allow player to place a piece
        placePiece();
        
        //will loop until player makes a legal move
        while (!checkLegal())
        {
            placePiece();
        }
        
        //makes move when legal move is created
        board.makeMove()
        
        //player turn ends and changes players
        changeTurn();
    }
    
}
