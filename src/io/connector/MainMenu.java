package io.connector;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
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
		JLabel name = new JLabel(title);
		panel.add(name, BorderLayout.CENTER);
		
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
		GameModel model = new GameModel("1", "AI");
		GameView view = new GameView();
		GameController controller = new SinglePlayerGameController(view, model);
		controller.onEnd(winner -> displayPanel(panel));
		displayPanel(view.getPanel());
	}
	
	private void startTwoPlayerGame() {
		GameModel model = new GameModel("1", "2");
		GameView view = new GameView();
		GameController controller = new TwoPlayerGameController(view, model);
		controller.onEnd(winner -> displayPanel(panel));
		displayPanel(view.getPanel());
	}
	
	private void displayPanel(JPanel panel) {
		Container pane = frame.getContentPane();
		pane.removeAll();
		pane.add(panel);
		pane.validate();
	}
}
