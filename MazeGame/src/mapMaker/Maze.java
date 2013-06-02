package mapMaker;
import java.awt.Dimension;

import javax.swing.*;

public class Maze {
	public static int gridSize = 19;
	public static String mapName = "maps//Map" + gridSize + ".txt";
	public static int width = gridSize * 32 + 16;
	public static int height = gridSize * 32 + 38;
	
	public Maze() {
		JFrame frame = new JFrame();
		frame.setTitle("Maze Game");
		frame.add(new Board());
		frame.setSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Maze();
	}
}
