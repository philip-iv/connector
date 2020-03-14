package io.connector;

import java.awt.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		playGuiBased();
	}
	
	private static void playGuiBased() {
		new MainMenu();
	}
	
	@SuppressWarnings("unused")
	private static void playTextBased() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Player 1's name: ");
		String p1 = in.nextLine();
		System.out.print("Enter Player 2's name: " );
		String p2 = in.nextLine();
		Player player1 = new Player(p1, Color.RED);
		Player player2 = new Player(p2, Color.YELLOW);
		GameModel game = new GameModel(player1, player2);
		
		while (!game.isOver()) {
			System.out.println(game);
			System.out.println("Which col would " + game.getCurrentPlayer() + " like to place a piece in?");
			int col;
			try {
				col = in.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("Col must be an integer between 1 and 7");
				continue;
			}
			if (col > 7 || col < 1) {
				System.out.println("Col must be an integer between 1 and 7");
				continue;
			}
			boolean result = game.placePiece(col-1);
			if (!result) {
				System.out.println("That column is full. Please choose an empty column.");
				continue;
			}
		}
		System.out.println(game);
		System.out.println(game.getWinner() + " wins!");
		
		in.close();
	}
}
