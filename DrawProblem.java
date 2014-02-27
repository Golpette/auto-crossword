import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.util.*;

/**
 * Class to take String[][] grid and paint the crossword as it should look
 */

public class DrawProblem extends JComponent {
	
	int x,y;
	
	//Size of grid
	private static final int SITE_LENGTH = 20;
	
	
	//Field to hold the String[][] grid whose state we want to view
	private String[][] Grid_init;
	private String[][] Grid;
	private ArrayList<String> cluesACROSS;
	private ArrayList<String> cluesDOWN;	

	// CONSTRUCTOR
	public DrawProblem(String[][] grid_init, String[][] grid, int x, int y, ArrayList<String> cluesACROSS, ArrayList<String> cluesDOWN){
		this.Grid_init = grid_init;
		this.Grid = grid;
		setOpaque(true);
		setBackground(Color.WHITE);
		this.x = x;
		this.y = y;
		this.cluesACROSS = cluesACROSS;
		this.cluesDOWN = cluesDOWN;
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	// PAINTING
	/**
	 *  Draw rectangle fill, then border, then any text
	 */
	private void drawRect(Graphics g, int cx, int cy, int r, String s) {
		g.fillRect( cx, cy, r, r );
		g.setColor(Color.black);
		g.drawRect( cx, cy, r, r );
		g.drawString( s, cx+2, cy+(r/2));	
	}
	
	private void drawClue(Graphics g, int cx, int cy, String clue){
		g.drawString( clue , cx, cy);
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

		Font myFont = new Font("Times New Roman", Font.PLAIN, 11);
		g.setFont(myFont);
		
		for(int q =1; q<x-1; q++){
			for(int qq = 1; qq<y-1; qq++){
					if (Grid[q][qq] == "_") { // Unused sites are black				
						g.setColor(Color.BLACK);
						drawRect(g, ox+q*sx, oy+qq*sy, SITE_LENGTH, Grid_init[q][qq]);							
					}
					if (Grid[q][qq] != "_") { // Used sites are white
						g.setColor(Color.WHITE);
						drawRect(g, ox+q*sx, oy+qq*sy, SITE_LENGTH, Grid_init[q][qq]);
					}	
				}					
		}
		
		myFont = new Font("Times New Roman", Font.BOLD, 12);
		g.setFont(myFont);
		drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, oy+SITE_LENGTH, "ACROSS:" );
		int y_coord = 0;
		myFont = new Font("Times New Roman", Font.PLAIN , 12);
//		myFont = new Font("Verdana", Font.PLAIN , 10);
		g.setFont(myFont);
		int lineNumber = -1;
		for( int cl=0; cl<cluesACROSS.size(); cl++){
			lineNumber++;
			y_coord = oy+SITE_LENGTH + (17*( lineNumber +1));
			
//			//If clue > 100 characters, split it over 2 lines   ___LOOKS SHIT. deal with this later
//			if( cluesACROSS.get(cl).length() > 100 ){
//				String addbit="";
//				String addbit2="";
//				String line1 = "";
//				for( int d=0; d<100; d++){
//					line1 = line1 + cluesACROSS.get(cl).charAt( d );
//				}
//				String line2 = "";
//				for( int d=100; d<cluesACROSS.get(cl).length(); d++){
//					line2 = line2 + cluesACROSS.get(cl).charAt( d );
//				}
//				if( cluesACROSS.get(cl).charAt(100) == ' ' ){
//						addbit = "";   addbit2 = "    ";				
//				}
//				else{  addbit = "-";   addbit2="     ";  }
//				drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, y_coord, line1+addbit );  //This '5' should prob be set by font size
//				lineNumber++;	
//				y_coord = oy+SITE_LENGTH + (17*( lineNumber +1));
//				drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, y_coord, addbit2+line2 );  //This '5' should prob be set by font size
//
//			}
		//	else{
				drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, y_coord, cluesACROSS.get( cl ) );  //This '5' should prob be set by font size
		//	}
		}
		myFont = new Font("Times New Roman", Font.BOLD , 12);
		g.setFont(myFont);
		int startHere = y_coord + 25;
		drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, startHere, "DOWN:" );
		myFont = new Font("Times New Roman", Font.PLAIN , 12);
		g.setFont(myFont);
		for( int cl=0; cl<cluesDOWN.size(); cl++){
			y_coord = startHere + (17*(cl+1));
			drawClue(g, ox+(x*SITE_LENGTH)+SITE_LENGTH, y_coord, cluesDOWN.get( cl ) );    //This '5' should prob be set by font size ##############################
		}
				
		repaint();
	}
	

	
	public static void main(String[][] grid_init, String[][] grid, int x, int y, ArrayList<String> cluesACROSS, ArrayList<String> cluesDOWN) {
		
		// FAIL. Can't even get a friggin scroll bar.
				
		// Set up a Frame (window) to open on the screen
		JFrame frame = new JFrame("Auto-crossword!");
		frame.setPreferredSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scroller = new JScrollPane( new DrawProblem(grid_init, grid, x, y, cluesACROSS, cluesDOWN) );
		scroller.setSize(2000,2000);

		scroller.createHorizontalScrollBar();
		
		frame.add( scroller, BorderLayout.CENTER );

		// Lay out the frame's contents, and put it on screen
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
