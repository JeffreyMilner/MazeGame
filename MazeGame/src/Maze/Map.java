package Maze;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;

public class Map {

	private Scanner m;
	private String Map[] = new String[Maze.gridSize];

	private Image grass, finish, wall, start, blank, deco1;
	
	public Map() {
		ImageIcon image;

		// Blank
		image = new ImageIcon("res/blank.png");
		blank = image.getImage();
		
		// Walk-over-able
		image = new ImageIcon("res/grass.png");
		grass = image.getImage();
		image = new ImageIcon("res/finish.png");
		finish = image.getImage();
		image = new ImageIcon("res/start.png");
		start = image.getImage();
		
		// Solid
		image = new ImageIcon("res/wall.png");
		wall = image.getImage();
		
		// Decoration (Solid)
		image = new ImageIcon("res/deco1.png");
		deco1 = image.getImage();
		
		
		openFile();
		readFile();
		closeFile();
	}
	
	public Image getGrass() {
		return grass;
	}
	public Image getWall() {
		return wall;
	}
	public Image getFinish() {
		return finish;
	}
	public Image getStart() {
		return start;
	}
	public Image getBlank() {
		return blank;
	}
	public Image getDeco1() {
		return deco1;
	}
	
	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}
	
	public void openFile() {
		try {
			m = new Scanner(new File(Maze.mapName));
		} catch (FileNotFoundException e) {
			System.out.println("Error loading map");
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int ix = 0; ix < Maze.gridSize; ix++) {
				Map[ix] = m.next();
			}
		}
	}
	
	public void closeFile() {
		m.close();
	}
}
