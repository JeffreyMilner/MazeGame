package Maze;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private Timer timer;

	public static Map m;
	private Player p;

	public static boolean win = false, winner = false, playerSet = false, firstTimeThrough = true;

	public static String pattern = "[wb12345]"; // labels to collide with

	private String endMessage = "You Won!", resetMessage = "Press (r) to restart";
	private String quitMessage = "Press (q) to quit", newMessage = "Press (n) to start a new maze";

	private Font font1 = new Font("Arial", Font.BOLD, 48);
	private Font font2 = new Font("Arial", Font.PLAIN, 15);

	public static int xOffset = 0, yOffset = 0;
	public static int pStartX = 0, pStartY = 0, xOffStart = 0, yOffStart = 0; 
	
	
	public Board() {
		m = new Map();
		p = new Player();

		setPreferredSize(new Dimension(Maze.width, Maze.height));
		setFocusable(true);
		initKeys();

		timer = new Timer(25, this);
		timer.start();
	}

	public void initKeys() {
		InputMap im = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownArrow");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "R");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "N");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "Q");

		am.put("RightArrow", new KeyAction("RightArrow"));
		am.put("LeftArrow", new KeyAction("LeftArrow"));
		am.put("UpArrow", new KeyAction("UpArrow"));
		am.put("DownArrow", new KeyAction("DownArrow"));
		am.put("R", new KeyAction("R"));
		am.put("N", new KeyAction("N"));
		am.put("Q", new KeyAction("Q"));
	}
	
	public class KeyAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		private String cmd;

		public KeyAction(String cmd) {
			this.cmd = cmd;
		}

		public void actionPerformed(ActionEvent e) {
			if (cmd.equalsIgnoreCase("LeftArrow")) {
				moveLeft();
			} else if (cmd.equalsIgnoreCase("RightArrow")) {
				moveRight();
			} else if (cmd.equalsIgnoreCase("UpArrow")) {
				moveUp();
			} else if (cmd.equalsIgnoreCase("DownArrow")) {
				moveDown();
			} else if (cmd.equalsIgnoreCase("R")) {
				restart();
			} else if (cmd.equalsIgnoreCase("N")) {
				newMap();
			} else if (cmd.equalsIgnoreCase("Q")) {
				quit();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(m.getMap(p.getTileX(), p.getTileY()).equals("f") && !win) {
			win = true;
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		if(!win) {
			for(int y = 0; y < Maze.gridSize; y++) {
				for(int x = 0; x < Maze.gridSize; x++) {
					String tile = m.getMap(x, y);
					if(tile.equals("s") && !playerSet) { // Puts the player on the start tile at the beginning
						setPlayer(x, y);
					}
					g.drawImage(m.getImage(tile), x*32 - xOffset*32, y*32 - yOffset*32, null);
				}
			}
			g.drawImage(p.getPlayer(), p.getTileX() * 32 - xOffset*32, p.getTileY() * 32 - yOffset*32, null);
		} else if(win) {
			FontMetrics fm;
			g.setColor(Color.red);
			Rectangle2D rect;

			g.setFont(font1);
			fm = g.getFontMetrics();
			rect = fm.getStringBounds(endMessage, g);
			g.drawString(endMessage, (int) (Maze.width/2 - rect.getWidth()/2), (int) (Maze.height/2 - rect.getHeight()/2));

			g.setFont(font2);
			fm = g.getFontMetrics();
			rect = fm.getStringBounds(resetMessage, g);
			g.drawString(resetMessage, (int) (Maze.width/2 - rect.getWidth()/2), (int) (Maze.height/2 - rect.getHeight()/2 + 20));

			rect = fm.getStringBounds(newMessage, g);
			g.drawString(newMessage, (int) (Maze.width/2 - rect.getWidth()/2), (int) (Maze.height/2 - rect.getHeight()/2 + 40));

			rect = fm.getStringBounds(quitMessage, g);
			g.drawString(quitMessage, (int) (Maze.width/2 - rect.getWidth()/2), (int) (Maze.height/2 - rect.getHeight()/2 + 60));
		}
	}

	public void setPlayer(int x, int y) {
		p.setTileX(x);
		p.setTileY(y);
		
		playerSet = true;
		
		if(Maze.gridSize > 20) {
			if(x > Maze.gridSize - 10) {
				xOffset = Maze.gridSize - 20;
			} else if(x > 10) {
				xOffset = x - 10;
			}
			if(y > Maze.gridSize - 10) {
				yOffset = Maze.gridSize - 20;
			} else if(y > 10) {
				yOffset = y - 10;
			}
			if (firstTimeThrough == true) {
				pStartX = x;
				pStartY = y;
				xOffStart = xOffset;
				yOffStart = yOffset;
				firstTimeThrough = false;
			}
		}
	}
	
	public void moveUp() {
		if(!win && !m.getMap(p.getTileX(), p.getTileY() - 1).matches(pattern) && p.getTileY() != 0) { 
			if(yOffset >  0 && p.getTileY() == yOffset + 10) {
				Board.yOffset--;
				p.move(0, -1);
			} else {
				p.move(0, -1);
			}
		}
	}

	public void moveDown() {
		if(!win && !m.getMap(p.getTileX(), p.getTileY() + 1).matches(pattern) && p.getTileY() != Maze.gridSize-1) {				
			if(yOffset < Maze.gridSize - 20 && p.getTileY() == yOffset + 10) {
				Board.yOffset++;
				p.move(0, 1);
			} else {
				p.move(0, 1);
			}
		}
	}

	public void moveLeft() {
		if(!win && !m.getMap(p.getTileX() - 1, p.getTileY()).matches(pattern) && p.getTileX() != 0) {				
			if(xOffset > 0 && p.getTileX() == xOffset + 10) {
				Board.xOffset--;
				p.move(-1, 0);
			} else {
				p.move(-1, 0);
			}
		}
	}

	public void moveRight() {
		if(!win && !m.getMap(p.getTileX() + 1, p.getTileY()).matches(pattern) && p.getTileX() != Maze.gridSize-1) {
			if(xOffset < Maze.gridSize - 20 && p.getTileX() == xOffset + 10) {
				Board.xOffset++;
				p.move(1, 0);
			} else {
				p.move(1, 0);
			}
		}
	}

	public void restart() {
		if(win) {
			win = false;
			playerSet = false;
			repaint();
		}
	}
	
	public void newMap() {
		if(win) {
			win = false;
			playerSet = false;
			int num = Maze.getRandMap(); 
			if(num > 1) { // Only looks for a new map if there is more than one of that size
				Board.m = new Map();
			} else if(num <= 1) {
				xOffset = xOffStart;
				yOffset = yOffStart;
				
				p.setTileX(pStartX);
				p.setTileY(pStartY);
			}
			repaint();
		}
	}
	
	public void quit() {
		if(win) {
			System.exit(0);
		}
	}
}


