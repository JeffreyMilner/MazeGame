package Maze;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private Timer timer;

	private Map m;
	private Player p;
	
	public boolean win = false, winner = false, playerSet = false;
	
	private String endMessage = "", resetMessage = "Press (r) to restart";

	private Font font1 = new Font("Serif", Font.BOLD, 48);
	private Font font2 = new Font("Serif", Font.PLAIN, 15);
	
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
			endMessage = "You Win!";
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		if(!win) {
			for(int y = 0; y < Maze.gridSize; y++) {
				for(int x = 0; x < Maze.gridSize; x++) {
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
						if(!playerSet) {
							p.setTileX(x);
							p.setTileY(y);
							playerSet = true;
						}
					}
					if(m.getMap(x, y).equals("b")) {
						g.drawImage(m.getBlank(), x*32, y*32, null);
					}
				}
			}
			g.drawImage(p.getPlayer(), p.getTileX() * 32, p.getTileY() * 32, null);
		} else if(win) {
			g.setColor(Color.red);
			g.setFont(font1);
			g.drawString(endMessage,   (Maze.width/2) - 110, (Maze.height/2)); // Extra stuff to center it
			g.setFont(font2);
			g.drawString(resetMessage, (Maze.width/2) - 60, (Maze.height/2) + 20);
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if(!win) {
				if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && p.getTileY() != 0) {
					if(!m.getMap(p.getTileX(), p.getTileY() - 1).matches("[wb]")) { // checks it against any/ all of the charasters in the []
						p.move(0, -1);
					}
				}
				if((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && p.getTileY() != Maze.gridSize) {
					if(!m.getMap(p.getTileX(), p.getTileY() + 1).matches("[wb]")) {				
						p.move(0, 1);
					}
				}
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && p.getTileX() != 0) {
					if(!m.getMap(p.getTileX() - 1, p.getTileY()).matches("[wb]")) {				
						p.move(-1, 0);
					}
				}
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && p.getTileX() != Maze.gridSize) {
					if(!m.getMap(p.getTileX() + 1, p.getTileY()).matches("[wb]")) {
						p.move(1, 0);
					}
				}
			} else if(win == true){
				if(key == KeyEvent.VK_R) {
					win = false;
					playerSet = false;
					repaint();
				}
			}
		}

		public void keyReleased(KeyEvent e) { } 
		public void keyTyped(KeyEvent e) { }
	}
	
}
