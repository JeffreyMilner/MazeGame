package mapMaker;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Map m;

	public boolean win = false, winner = false, playerSet = false;

	private String blankButton	= "Blank (b)";
	private String deco1Button 	= "Deco1 (d)";
	private String endButton 	= "End (e)";
	private String grassButton 	= "Grass (g)";
	private String startButton	= "Start (s)";
	private String wallButton 	= "Wall (w)";
	private String saveButton 	= "Save (C-S)";
	public static boolean grass = true, wall = false, start = false, end = false, blank = false, deco1 = false;

	public Board() {
		m = new Map();

		setFocusable(true);
		addKeyListener(new AL());
		addMouseListener(new ML());
		
		timer = new Timer(25, this);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.black);
		g.drawString(blankButton, MazeMaker.width + 5, 25);
		g.drawString(deco1Button, MazeMaker.width + 5, 50);
		g.drawString(endButton,   MazeMaker.width + 5, 75);
		g.drawString(grassButton, MazeMaker.width + 5, 100);
		g.drawString(startButton, MazeMaker.width + 5, 125);
		g.drawString(wallButton,  MazeMaker.width + 5, 150);
		g.drawString(saveButton,  MazeMaker.width + 5, 175);

		for(int y = 0; y < MazeMaker.gridSize; y++) {
			for(int x = 0; x < MazeMaker.gridSize; x++) {
				if(m.getMap(x, y).equals("f")) {
					g.drawImage(m.getFinish(), x*32, y*32, null);
				}
				if(m.getMap(x, y).equals("g")) {
					g.drawImage(m.getGrass(), x*32, y*32, null);
				}
				if(m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x*32, y*32, null);
				}
				if(m.getMap(x, y).equals("s")) {
					g.drawImage(m.getStart(), x*32, y*32, null);
				}
				if(m.getMap(x, y).equals("b")) {
					g.drawImage(m.getBlank(), x*32, y*32, null);
				}
				if(m.getMap(x, y).equals("d")) {
					g.drawImage(m.getDeco1(), x*32, y*32, null);
				}
			}
		}
		repaint();
	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int x = e.getX() / 32;
			int y = e.getY() / 32;

			if(grass) {
				m.setMap(x,  y,  'g');
			} else if(wall) {
				m.setMap(x,  y,  'w');
			} else if(start) {
				m.setMap(x,  y,  's');
			} else if(end) {
				m.setMap(x,  y,  'f');
			} else if(blank) {
				m.setMap(x,  y,  'b');
			} else if(deco1) {
				m.setMap(x,  y,  'd');
			}
		} 
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if(e.isControlDown() && key == KeyEvent.VK_S) {
				Map.writeToFile();
			}
			if(key == KeyEvent.VK_G) {
				Board.grass = true;
				Board.wall = false;
				Board.start = false;
				Board.end = false;
				Board.deco1 = true;
			}
			if(key == KeyEvent.VK_W) {
				Board.grass = false;
				Board.wall = true;
				Board.start = false;
				Board.end = false;
				Board.deco1 = true;
			}
			if(key == KeyEvent.VK_S) {
				Board.grass = false;
				Board.wall = false;
				Board.start = true;
				Board.end = false;
				Board.blank = false;
				Board.deco1 = true;
			}
			if(key == KeyEvent.VK_E) {
				Board.grass = false;
				Board.wall = false;
				Board.start = false;
				Board.end = true;
				Board.blank = false;
				Board.deco1 = true;
			}
			if(key == KeyEvent.VK_B) {
				Board.grass = false;
				Board.wall = false;
				Board.start = false;
				Board.end = false;
				Board.blank = true;
				Board.deco1 = true;
			}if(key == KeyEvent.VK_D) {
				Board.grass = false;
				Board.wall = false;
				Board.start = false;
				Board.end = false;
				Board.blank = false;
				Board.deco1 = true;
			}
		}
	}
}
