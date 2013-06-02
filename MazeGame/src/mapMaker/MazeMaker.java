package mapMaker;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class MazeMaker {
	public static int gridSize = 19;
	public static String mapName = ""; //"maps/Map" + gridSize + ".txt";
	public static int width, height;
	public static int newMap;
	
	public MazeMaker() {
		try {
			newMap = JOptionPane.showConfirmDialog(null, "Do you want to create a new map?", "New Map", JOptionPane.YES_NO_OPTION);
		} catch(Exception e) {
			System.exit(0);
		}
		
		if(newMap == JOptionPane.YES_OPTION) {
			sizeChecker();
			chooser();
			Map.newMap();
		} else {
			sizeChecker();
			chooser();
		}
		width = gridSize * 32 + 16; // Makes it so the tiles fit the best
		height = gridSize * 32 + 38;
		JFrame frame = new JFrame();
		frame.setTitle("Maze Maker");
		frame.add(new Board());
		frame.setSize(new Dimension(width + 100, height));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void sizeChecker() {
		try {
			gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter number of rows/ columns there will be"));
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
		new MazeMaker();
	}
}