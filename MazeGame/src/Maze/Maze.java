package Maze;
import java.awt.Dimension;
import java.io.File;

import javax.swing.*;

public class Maze {
	public static int gridSize;
	public static String mapName = "";
	public static int width;
	public static int height;
	public static JFrame frame = new JFrame();
	
	public Maze() {
		sizeChecker();
		chooser();
		width = gridSize * 32 + 16; // Makes it so the tiles fit the best
		height = gridSize * 32 + 38;
		frame.setTitle("Maze Game");
		frame.add(new Board());
		frame.setSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void sizeChecker() {
		try {
			gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows/ columns there will be"));
		} catch(Exception e) {
			System.exit(0);
		}
	}
	
	public void chooser() {
		JFileChooser fc = new JFileChooser("maps");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(null); 

		if (returnVal == JFileChooser.APPROVE_OPTION) { 
			File file = fc.getSelectedFile(); 
			// see if it's an image 
			// (better to write a function and check for all supported extensions) 
			if (file.getName().endsWith("txt")) { 
				mapName = file.getPath();
			} else { 
				System.out.println("Must be a .txt file!");
			} 
		} else { 
			System.out.println("Open command cancelled by user."); 
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new Maze();
	}
}
