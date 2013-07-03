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

	public boolean win = false, winner = false, playerSet = false;

	private String endMessage = "You Won!", resetMessage = "Press (r) to restart";
	private String quitMessage = "Press (q) to quit", newMessage = "Press (n) to start a new maze";

	private Font font1 = new Font("Arial", Font.BOLD, 48);
	private Font font2 = new Font("Arial", Font.PLAIN, 15);

	public static int xOffset = 0, yOffset = 0;

	public Board() {
		m = new Map();
		p = new Player();

		addKeyListener(new AL());
		setFocusable(true);

		timer = new Timer(25, this);
		timer.start();
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
						}
						p.setTileX(x);
						p.setTileY(y);
						playerSet = true;
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

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			String pattern = "[wb12345]"; // labels to collide with

			if(!win) {
				if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && p.getTileY() != 0) {
					if(!m.getMap(p.getTileX(), p.getTileY() - 1).matches(pattern)) { // checks it against any/ all of the characters in the []
						if(yOffset >  0 && p.getTileY() == yOffset + 10) {
							Board.yOffset--;
							p.move(0, -1);
						} else {
							p.move(0, -1);
						}
					}
				}
				if((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && p.getTileY() != Maze.gridSize-1) {
					if(!m.getMap(p.getTileX(), p.getTileY() + 1).matches(pattern)) {				
						if(yOffset < Maze.gridSize - 20 && p.getTileY() == yOffset + 10) {
							Board.yOffset++;
							p.move(0, 1);
						} else {
							p.move(0, 1);
						}

					}
				}
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && p.getTileX() != 0) {
					if(!m.getMap(p.getTileX() - 1, p.getTileY()).matches(pattern)) {				
						if(xOffset > 0 && p.getTileX() == xOffset + 10) {
							Board.xOffset--;
							p.move(-1, 0);
						} else {
							p.move(-1, 0);
						}
					}
				}
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && p.getTileX() != Maze.gridSize-1) {
					if(!m.getMap(p.getTileX() + 1, p.getTileY()).matches(pattern)) {
						if(xOffset < Maze.gridSize - 20 && p.getTileX() == xOffset + 10) {
							Board.xOffset++;
							p.move(1, 0);
						} else {
							p.move(1, 0);
						}
					}
				}
			} else if(win == true){
				if(key == KeyEvent.VK_R) {
					win = false;
					playerSet = false;
					repaint();
				}
				if(key == KeyEvent.VK_N) {
					win = false;
					playerSet = false;
					Maze.getRandMap();
					Board.m = new Map();
					repaint();
				}
				if(key == KeyEvent.VK_Q) {
					System.exit(0);
				}
			}
		}

		public void keyReleased(KeyEvent e) { } 
		public void keyTyped(KeyEvent e) { }
	}

}
