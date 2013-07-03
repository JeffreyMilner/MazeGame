package mapMaker;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Map m;
	
	public static boolean grass = true, wall = false, start = false, end = false, blank = false;
	public static boolean deco1 = false, deco2 = false, deco3 = false, deco4 = false, deco5 = false;

	public static int xOffset = 0, yOffset = 0;
	public static int height = 20 * 32;
	public static int width = 20 * 32;
	
	public Board() {
		m = new Map();

		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		addKeyListener(new AL());
		addMouseListener(new ML());
		
		timer = new Timer(25, this);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		String decoPattern = "[12345]"; // All the decoration tiles easily sorted
		
		for(int y = 0; y < MazeMaker.gridSize; y++) {
			for(int x = 0; x < MazeMaker.gridSize; x++) {
				if(m.getMap(x, y).equals("f")) {
					g.drawImage(m.getFinish(), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
				if(m.getMap(x, y).equals("g")) {
					g.drawImage(m.getGrass(), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
				if(m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
				if(m.getMap(x, y).equals("s")) {
					g.drawImage(m.getStart(), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
				if(m.getMap(x, y).equals("b")) {
					g.drawImage(m.getBlank(), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
				if(m.getMap(x, y).matches(decoPattern)) {
					g.drawImage(m.getDeco(m.getMap(x, y)), x*32 - xOffset*32, y*32 - yOffset*32, null);
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
				m.setMap(x+xOffset,  y+yOffset,  'g');
			} else if(wall) {
				m.setMap(x+xOffset,  y+yOffset,  'w');
			} else if(start) {
				m.setMap(x+xOffset,  y+yOffset,  's');
			} else if(end) {
				m.setMap(x+xOffset,  y+yOffset,  'f');
			} else if(blank) {
				m.setMap(x+xOffset,  y+yOffset,  'b');
			} else if(deco1) {
				m.setMap(x+xOffset,  y+yOffset,  '1');
			} else if(deco2) {
				m.setMap(x+xOffset,  y+yOffset,  '2');
			} else if(deco3) {
				m.setMap(x+xOffset,  y+yOffset,  '3');
			} else if(deco4) {
				m.setMap(x+xOffset,  y+yOffset,  '4');
			} else if(deco5) {
				m.setMap(x+xOffset,  y+yOffset,  '5');
			}
		} 
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(MazeMaker.gridSize > 20) {
				if((key == KeyEvent.VK_UP) && yOffset > 0) 						{ yOffset--; }
				if((key == KeyEvent.VK_DOWN) && yOffset < MazeMaker.gridSize-20) 	{ yOffset++; }
				if((key == KeyEvent.VK_LEFT) && xOffset > 0) 					{ xOffset--; }
				if((key == KeyEvent.VK_RIGHT)&& xOffset < MazeMaker.gridSize-20) 	{ xOffset++; }
			}
			if(e.isControlDown() && key == KeyEvent.VK_S) { Map.writeToFile(); }
			
			if(key == KeyEvent.VK_G) {
				Board.grass = true;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_W) {
				Board.grass = false;
				Board.wall  = true;
				Board.start = false;
				Board.end   = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_S) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = true;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_E) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = true;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_B) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = true;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_1) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = true;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_2) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = true;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_3) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = true;
				Board.deco4 = false;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_4) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = true;
				Board.deco5 = false;
			}
			if(key == KeyEvent.VK_5) {
				Board.grass = false;
				Board.wall  = false;
				Board.start = false;
				Board.end   = false;
				Board.blank = false;
				Board.deco1 = false;
				Board.deco2 = false;
				Board.deco3 = false;
				Board.deco4 = false;
				Board.deco5 = true;
			}
		}
	}
}
