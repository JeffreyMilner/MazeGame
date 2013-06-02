package Maze;

import java.awt.*;
import javax.swing.*;     


@SuppressWarnings("serial")
public class SizeChooser extends JDialog {     

	JPanel startWin; 	// Initialise the window      
	JRadioButton size14, size20, size19, size27, none, size28;		// Initialize the radio buttons
	JButton start;		// Initialize the start button

	public SizeChooser(){     

		startWin = new JPanel();     
		startWin.setLayout(new GridLayout(Maze.numOfSizes + 1,1));     
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Choose Your Class");

		// Next five uncommented lines tell it to be centered on the screen, since this.setRelativeTo(null); 
		// is really bad at that for these windows
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2) - 100;
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);		// Puts it in the middle of the screen

		ButtonGroup operation = new ButtonGroup();		// Makes a buttongroup so that only one of the radiobuttons can be selected at a time

		//========================================
		//======== Button Initialization =========
		//========================================

		size14 = new JRadioButton("Map size of 14x14");
		operation.add(size14);
		startWin.add(size14);
		size14.setSelected(true);

		size19 = new JRadioButton("Map size of 19x19");     
		operation.add(size19);
		startWin.add(size19);  

		size20 = new JRadioButton("Map size of 20x20");
		operation.add(size20);
		startWin.add(size20);     

		size27 = new JRadioButton("Map size of 27x27");
		operation.add(size27);
		startWin.add(size27);     

		size28 = new JRadioButton("Map size of 28x28");
		operation.add(size28);
		startWin.add(size28);
		this.add(startWin);

		start = new JButton("Start");
		startWin.add(start);
		start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitActionPerformed(evt);
			}
		});
		this.add(startWin);
	}     


	/**
	 * A big if-else statement to get make the 
	 * start button do whatever was selected
	 * 
	 * @param evt
	 */
	private void exitActionPerformed(java.awt.event.ActionEvent evt) {
		if (size14.isSelected()) {
			Maze.gridSize = 14;
			Maze.afterChoose();
			this.dispose();
		} else if(size19.isSelected()) {
			Maze.gridSize = 19;
			Maze.afterChoose();
			this.dispose();
		} else if(size20.isSelected()) {
			Maze.gridSize = 20;
			Maze.afterChoose();
			this.dispose();
		} else if (size27.isSelected()) {
			Maze.gridSize = 27;
			Maze.afterChoose();
			this.dispose();
		} else if(size28.isSelected()){
			Maze.gridSize = 28;
			Maze.afterChoose();
			this.dispose();
		}
	}
}