import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class to take String[][] grid and paint the crossword solution
 */
public class DrawSolution extends JComponent {
	
	int x,y;
	
	//Size of grid
	private static final int SITE_LENGTH = 20;
	
	
	//Field to hold the String[][] grid whose state we want to view
	private String[][] gridy;
	

	// CONSTRUCTOR
	public DrawSolution(String[][] grid,int x, int y){
		gridy = grid;
		setOpaque(true);
		setBackground(Color.WHITE);
		this.x = x;
		this.y = y;
	}
	
	// PAINTING
	/**
	 *  Draw rectangle fill, then border, then any text
	 */
	private void drawRect(Graphics g, int cx, int cy, int r, String s) {
		g.fillRect( cx, cy, r, r );
		g.setColor(Color.black);
		g.drawRect( cx, cy, r, r );
		g.drawString( s, cx+(r/2 - 2), cy+(r - 6 ));	
	}
	

	/**
	 * This is the method in which all the drawing happens. It is called automatically by Swing when the component needs to
	 * be drawn or updated.
	 * 
	 * g current graphics context (an object that provides the drawing methods)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// First, get the dimensions of the component
		int width = getWidth(), height = getHeight();

		// Clear the background if we are an opaque component
		if(isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, width, height);
		}
		
		// The origin goes at the centre; in Swing, the y coordinate increases from 0 at the top to height at the bottom
		int ox = 0, oy = 0;
		int sx = SITE_LENGTH; int sy = SITE_LENGTH;
			
		for(int q =1; q<x-1; q++){
			for(int qq = 1; qq<y-1; qq++){
					if (gridy[q][qq] == "_") { // unused site is black				
						g.setColor(Color.BLACK);
						drawRect(g, ox+q*sx, oy+qq*sy, SITE_LENGTH, gridy[q][qq]);							
					}
					if (gridy[q][qq] != "_") { // used site white
						g.setColor(Color.WHITE);
						drawRect(g, ox+q*sx, oy+qq*sy, SITE_LENGTH, gridy[q][qq]);
					}	
				}					
			}
	}
	


	
	public static void main(String[][] in_grid, int x, int y) {
		
	
		String[][] grid = in_grid;

				
		// Set up a Frame (window) to open on the screen
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Auto-crossword solution!  (I bet you're cheating...) ");
		frame.setPreferredSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a pendulum viewer, and place this within the frame
		DrawSolution view = new DrawSolution(grid,x,y);
		frame.getContentPane().add(view);
		
		// Lay out the frame's contents, and put it on screen
		frame.pack();
		frame.setVisible(true);
		
		
		view.repaint();

				
	}
	
}
