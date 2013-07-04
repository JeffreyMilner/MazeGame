package Maze;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SizeMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	JRadioButton size14, size19, size20, size21, size27, size28, size29, size30;	// Initialize the radio buttons
	JButton start;	// Initialize the start button
	
	public static int buttonNum;

	
	public SizeMenu() {
		ButtonGroup operation = new ButtonGroup();		// Makes a buttongroup so that only one of the radiobuttons can be selected at a time

		//========================================
		//======== Button Initialization =========
		//========================================

		size14 = new JRadioButton("Map size of 14x14");
		operation.add(size14);
		add(size14);
		size14.setSelected(true);

		size19 = new JRadioButton("Map size of 19x19");     
		operation.add(size19);
		add(size19);  

		size20 = new JRadioButton("Map size of 20x20");
		operation.add(size20);
		add(size20);     
		
		size21 = new JRadioButton("Map size of 21x21");
		operation.add(size21);
		add(size21);

		size27 = new JRadioButton("Map size of 27x27");
		operation.add(size27);
		add(size27);     

		size28 = new JRadioButton("Map size of 28x28");
		operation.add(size28);
		add(size28);
		
		size29 = new JRadioButton("Map size of 29x29");
		operation.add(size29);
		add(size29);
		
		size30 = new JRadioButton("Map size of 30x30");
		operation.add(size30);
		add(size30);
		
		
		OldMaze.numOfSizes = operation.getButtonCount();

		start = new JButton("Start");
		add(start);

		start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				start();
			}
		});

		buttonNum = operation.getButtonCount();
		
		setLayout(new GridLayout(buttonNum + 1,1));     
	}

	private void start() {
		if (size14.isSelected()) {
			Maze.gridSize = 14;
			Maze.afterChoose();
		} else if(size19.isSelected()) {
			Maze.gridSize = 19;
			Maze.afterChoose();
		} else if(size20.isSelected()) {
			Maze.gridSize = 20;
			Maze.afterChoose();
		} else if(size21.isSelected()) {
			Maze.gridSize = 21;
			Maze.afterChoose();
		} else if (size27.isSelected()) {
			Maze.gridSize = 27;
			Maze.afterChoose();
		} else if(size28.isSelected()){
			Maze.gridSize = 28;
			Maze.afterChoose();
		} else if(size29.isSelected()){
			Maze.gridSize = 29;
			Maze.afterChoose();
		} else if(size30.isSelected()){
			Maze.gridSize = 30;
			Maze.afterChoose();
		}
		Maze.toBoard();
	}
}
