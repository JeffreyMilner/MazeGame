package mapMaker;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.ImageIcon;

public class Map {

	private Scanner m;
	private static String Map[] = new String[MazeMaker.gridSize];

	private Image grass, finish, wall, start, blank, deco1, deco2, deco3, deco4, deco5;
	
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
		image = new ImageIcon("res/deco2.png");
		deco2 = image.getImage();
		image = new ImageIcon("res/deco3.png");
		deco3 = image.getImage();
		image = new ImageIcon("res/deco4.png");
		deco4 = image.getImage();
		image = new ImageIcon("res/deco5.png");
		deco5 = image.getImage();
		
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
	public Image getDeco(String num) {
		if(num.equals("1")) {
			return deco1;
		} else if(num.equals("2")) {
			return deco2;
		} else if(num.equals("3")) {
			return deco3;
		} else if(num.equals("4")) {
			return deco4;
		} else if(num.equals("5")) {
			return deco5;
		} else {
			return blank;
		}
	}
	
	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}
	
	public void setMap(int x, int y, char tile) {
		StringBuilder oldRow = new StringBuilder(Map[y]);
		oldRow.setCharAt(x, tile);
		Map[y] = oldRow.toString();
	}

	public static void newMap() {
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(MazeMaker.mapName), "utf-8"));
		    for(int y = 0; y < MazeMaker.gridSize; y++) {
				for(int x = 0; x < MazeMaker.gridSize; x++) {
					if(MazeMaker.border == true && (y == 0 || y == MazeMaker.gridSize-1 || x == 0 || x == MazeMaker.gridSize-1)) {
						writer.write("w");
					} else {
						writer.write("g");
					}
				}
				writer.write("\n");
		    }
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			   try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void openFile() {
		try {
			m = new Scanner(new File(MazeMaker.mapName));
		} catch (FileNotFoundException e) {
			System.out.println("Error loading map");
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int ix = 0; ix < MazeMaker.gridSize; ix++) {
				Map[ix] = m.next();
			}
		}
	}
	
	public static void writeToFile() {
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(MazeMaker.mapName), "utf-8"));
		    for(int y = 0; y < MazeMaker.gridSize; y++) {
		    	writer.write(Map[y] + "\n");
		    }
		} catch (IOException ex){
			ex.printStackTrace();
		} finally {
			   try {
				writer.close();
				String timeStamp = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime());
				Board.saveMessage = "Saved as of:";
				Board.saveTime = "  " + timeStamp;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeFile() {
		m.close();
	}
}
