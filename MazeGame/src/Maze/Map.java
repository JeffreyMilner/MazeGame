package Maze;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;

public class Map {

	private Scanner m;
	private String Map[] = new String[Maze.gridSize];

	private Image grass, finish, wall, start, blank, deco1, deco2, deco3, deco4, deco5;
	
	public Map() {
		ImageIcon image;

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
		image = new ImageIcon("res/deco2.png");
		deco2 = image.getImage();
		image = new ImageIcon("res/deco3.png");
		deco3 = image.getImage();
		image = new ImageIcon("res/deco4.png");
		deco4 = image.getImage();
		image = new ImageIcon("res/deco5.png");
		deco5 = image.getImage();
		
		// Blank (Solid)
		image = new ImageIcon("res/blank.png");
		blank = image.getImage();
		
		openFile();
		readFile();
		closeFile();
	}
	
	
	public Image getImage(String tile) {
		if(tile.equals("g")) {
			return grass;
		} else if(tile.equals("w")) {
			return wall;
		} else if(tile.equals("f")) {
			return finish;
		} else if(tile.equals("s")) {
			return start;
		} else if(tile.equals("b")) {
			return blank;
		} else if(tile.equals("1")) {
			return deco1;
		} else if(tile.equals("2")) {
			return deco2;
		} else if(tile.equals("3")) {
			return deco3;
		} else if(tile.equals("4")) {
			return deco4;
		} else if(tile.equals("5")) {
			return deco5;
		} else {
			return blank;
		}
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
