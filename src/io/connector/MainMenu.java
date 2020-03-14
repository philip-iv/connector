package io.connector;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class MainMenu {
	private String title = "Connector.IO";
	private String helpText = "Rules: \n" +
			"1. Users take turns placing a piece into a column of the game board.\n" +
			"2. If a player has 4 pieces in a row vertically, horizontally, or diagonally, they win and the game ends.\n" +
			"3. If the board fills without any player getting 4 in a row, the game ends in a draw.\n\n" +
			"Game Modes:\n" +
			"Player vs AI: A single human plays against the computer\n" +
			"Player vs Player: Two humans play against each other, taking turns to place pieces.\n" +
			"A turn indicator at the top of the game board says who's turn it is. Player 1 always goes first.";
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
		howTo.addActionListener(e -> showHelpDialog());
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
	
	private void showHelpDialog() {
		JOptionPane.showMessageDialog(frame, helpText, "How to play", JOptionPane.PLAIN_MESSAGE);
	}

	private void startSinglePlayerGame() {
		Player player1 = promptPlayer("Player 1", Color.RED);
		if (player1 == null)
			return;
		Player ai = new Player("AI", (player1.getColor().equals(Color.RED) ? Color.YELLOW : Color.RED ));
		GameModel model = new GameModel(player1, ai);
		GameView view = new GameView();
		GameController controller = new SinglePlayerGameController(view, model);
		controller.onEnd(winner -> displayPanel(panel));
		displayPanel(view.getPanel());
	}
	
	private void startTwoPlayerGame() {
		Player player1 = promptPlayer("Player 1", Color.RED);
		if (player1 == null)
			return;
		Player player2 = promptPlayer("Player 2", Color.YELLOW);
		if (player2 == null)
			return;
		if (player1.getName().equals(player2.getName())) {
			JOptionPane.showMessageDialog(frame,
					"Both players cannot have the same name",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (player1.getColor().equals(player2.getColor())) {
			JOptionPane.showMessageDialog(frame,
					"Both players cannot pick the same color",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
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

	private Player promptPlayer(String player, Color defaultColor) {
		/**
		 * Shows a {@link JOptionPane} to prompt for a player's name and preferred color.
		 */
		JPanel input = new JPanel();
		input.add(new JLabel("Name: "));
		
		JTextField name = new JTextField(12);
		// Make the text box the focus when the dialog opens
		name.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent e) {
				JComponent c = e.getComponent();
				c.requestFocusInWindow();
			}
			public void ancestorRemoved(AncestorEvent e) {}
			public void ancestorMoved(AncestorEvent event) {}
		});
		input.add(name);
		
		JLabel colorLabel = new JLabel("Token color: ");
		input.add(colorLabel);
		
		JColorChooser colorPicker = new JColorChooser(defaultColor);
		colorPicker.setPreviewPanel(new JPanel()); // Remove preview panel
		// Only enable the HSV picker
		AbstractColorChooserPanel[] allPanels = colorPicker.getChooserPanels();
		AbstractColorChooserPanel[] desiredPanels = { allPanels[1] };
		colorPicker.setChooserPanels(desiredPanels);
		input.add(colorPicker);
		
		JOptionPane.showMessageDialog(frame, input, "Settings for " + player, JOptionPane.PLAIN_MESSAGE);
		if (name.getText().equals("")) //TODO: handle X out vs empty name differently
			return null;
		return new Player(name.getText(), colorPicker.getColor());
	}
}
