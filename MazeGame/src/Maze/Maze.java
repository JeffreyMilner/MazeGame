package Maze;
import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

import javax.swing.*;

public class Maze {
	public static int gridSize;
	public static String mapName = ""; 
	public static String mapDir  = "";
	public static int width;
	public static int height;
	public static JFrame frame = new JFrame();

	public static int numOfSizes = 7;
	
	public Maze() {
//		sizeChecker();
//		chooser();
		SizeChooser gui = new SizeChooser();
		gui.setSize(170, 25 * (SizeChooser.buttonNum + 1));
	}
	
	public static void afterChoose() {
		mapDir = "maps/size" + gridSize;
		getRandMap();
		width = gridSize * 32 + 6; // Makes it so the tiles fit the best
		height = gridSize * 32 + 28;
		frame.setTitle("Maze Game");
		frame.add(new Board());
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void getRandMap() {
		String path = mapDir; 

		File directory = new File(path);
		File[] listOfFiles = directory.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".txt");
			}
		}); 

		int mapNum = getRandomNumberBetween(0, listOfFiles.length);		
//		System.out.println("Maps: " + listOfFiles.length);

		try {
			mapName = listOfFiles[mapNum].getPath();
			System.out.println("MapName: "+ mapName);
		} catch(Exception e) {
			System.out.println("mapName = " + mapName);
		}
	}
	
	public static int getRandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
	
	public void chooser() {
		JFileChooser fc = new JFileChooser("maps");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(null); 

		if (returnVal == JFileChooser.APPROVE_OPTION) { 
			File file = fc.getSelectedFile(); 
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
