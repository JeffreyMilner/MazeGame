package Maze;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

import javax.swing.*;

public class Maze {
	public static int gridSize = 20;
	public static String mapName = ""; 
	public static String mapDir  = "";
	public static int width;
	public static int height;

	public static int numOfSizes = 7;
	public static boolean First = true;
	
	private static CardLayout cl;
	public static JFrame frame = new JFrame();
	public static JPanel CardPanel = new JPanel();
	
	public static String BoardString = "Board";
	public static String MainMenuString = "MainMenu";
	public static String SizeMenuString = "SizeMenu";
	
	public Maze() {
		width = 20 * 32;
		height = 20 * 32 + 5;
		
		cl = new CardLayout(); 
		CardPanel.setLayout(cl);
		
		CardPanel.add(new MainMenu(), MainMenuString);
		CardPanel.add(new SizeMenu(), SizeMenuString);
		
		cl.show(CardPanel, MainMenuString);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.add(CardPanel);
		frame.pack();
		
		frame.setTitle("Maze Game");

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void afterChoose() {
		mapDir = "maps/size" + gridSize;
		getRandMap();
	}

	public static void toMainMenu() {
		cl.show(CardPanel, MainMenuString);
	}
	
	public static void toSizeMenu() {
		cl.show(CardPanel, SizeMenuString);
	}
	
	public static void toBoard() {
		JPanel B = new JPanel(); // Added later so that the map can be chosen before it tries to load
		B.add(new Board());
		CardPanel.add(B, BoardString);
		cl.show(CardPanel, BoardString);
	}
	
	public static int getRandMap() {
		String path = mapDir, oldMapName = mapName;

		File directory = new File(path);
		File[] listOfFiles = directory.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".txt");
			}
		}); 

		while(mapName == oldMapName) {
			if(listOfFiles.length == 1 && !First) {
				return 1; // the current map is the only one
			} else {
				int mapNum = getRandomNumberBetween(0, listOfFiles.length);		
				System.out.println("Maps: " + listOfFiles.length + ", MapNum = " + mapNum);

				mapName = listOfFiles[mapNum].getPath();
				System.out.println("MapName: " + mapName);
				if(mapName.equals(oldMapName)) {
					System.out.println("Continuing: Old: " + oldMapName + ", new: " + mapName);
					continue;
				} else {
					System.out.println("Returning: Old: " + oldMapName + ", new: " + mapName);
					First = true;
					return 2; // there is more than 1
				}
			}
		}
		return 0;
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
