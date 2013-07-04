package Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static JButton StartGame, ChooseCharacter, EditMap, QuitGame; 
	
	public MainMenu() {
		StartGame = new JButton("Start Game");
//		ChooseCharacter = new JButton("Choose Character");
//		EditMap = new JButton("Edit Map");
		QuitGame = new JButton("Quit Game");
		
		StartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Code to switch card here
				Maze.toSizeMenu();
			}
		});

		QuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				exitActionPerformed(evt);
			}
		});
		

		
		add(StartGame);
		add(QuitGame);
	}
	
	// play -> chooser
	// choose character? 
	// quit
	// edit map?
	
	//==> should all go to make it change to a different card?
}
