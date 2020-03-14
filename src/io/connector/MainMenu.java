package io.connector;

import javax.swing.BorderFactory;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainMenu {
	private String title = "Connector.IO";
	private JFrame frame;
	private JPanel panel;
	
	public MainMenu() {
		frame = new JFrame(title);
        frame.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {System.exit(0);}
        });
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panel.setLayout(new GridLayout(4, 1));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> System.exit(0));
		exit.setMnemonic(KeyEvent.VK_E);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem howTo = new JMenuItem("How to play");
		howTo.setMnemonic(KeyEvent.VK_P);
		// TODO: Make help button do something
		helpMenu.add(howTo);
		menuBar.add(helpMenu);
		frame.setJMenuBar(menuBar);
		
		JLabel name = new JLabel(title);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(name);
		
		JButton singlePlayerButton = new JButton("Player vs AI");
		singlePlayerButton.addActionListener(e -> startSinglePlayerGame());
		panel.add(singlePlayerButton);
		
		JButton twoPlayerButton = new JButton("Player vs Player");
		twoPlayerButton.addActionListener(e -> startTwoPlayerGame());
		panel.add(twoPlayerButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(e -> System.exit(0));
		panel.add(quitButton);
		
		frame.getContentPane().add(panel);
		frame.pack();
		
		frame.setVisible(true);
	}
	
	private void startSinglePlayerGame() {
		String player1 = promptName("Player 1");
		GameModel model = new GameModel(player1, "AI");
		GameView view = new GameView();
		GameController controller = new SinglePlayerGameController(view, model);
		controller.onEnd(winner -> displayPanel(panel));
		displayPanel(view.getPanel());
	}
	
	private void startTwoPlayerGame() {
		String player1 = promptName("Player 1");
		String player2 = promptName("Player 2");
		GameModel model = new GameModel(player1, player2);
		GameView view = new GameView();
		GameController controller = new TwoPlayerGameController(view, model);
		controller.onEnd(winner -> displayPanel(panel));
		displayPanel(view.getPanel());
	}
	
	private void displayPanel(JPanel panel) {
		Container pane = frame.getContentPane();
		pane.removeAll();
		pane.add(panel);
		pane.revalidate();
		pane.repaint();
	}

	private String promptName(String player) {
		/**
		 * Shows a {@link JOptionPane} to prompt for a player's name.
		 */
		return JOptionPane.showInputDialog(frame, "Enter name for " + player);
	}
}
