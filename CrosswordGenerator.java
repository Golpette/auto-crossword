import java.io.*;
import java.util.*;

public class CrosswordGenerator{


	public static void main(String args[])throws IOException{

		int gridsize = 17;    // (Actual crossword will be 2 squares smaller than this)
		int x=gridsize;     int y=gridsize;
		String[][] grid = new String[x][y];
		String[][] grid_init = new String[x][y];
		ArrayList<String> cluesACROSS = new ArrayList<String>();
		ArrayList<String> cluesDOWN = new ArrayList<String>();

		
		// Read in word and definition list.
		ArrayList<Word> words2 = new ArrayList<Word>();
		words2 = ReadWords.getWordsandDefs("words_CambridgeDef.txt");
		
		// Create grids until you find a connected one!  
		//(THIS IS STUPID?? Doesn't take that long though for size <= 30x30...)
		boolean connected = false;
		while( !connected ){

			// Actual 'active' grid is 2 shorter in each direction 
			// (edges kept blank for simplicity in later methods)
			grid = new String[x][y];		
			for(int i=0; i<y; i++){
				for( int j=0; j<x; j++){
					grid[i][j] = "_";
				}
			}	
			ArrayList<Entry> entries = new ArrayList<Entry>();

			
			//Try fit a word "trys" number of times. Randomly select it to be 'across' or 'down'.
			for( int trys=0; trys < 10000; trys++){
				int up_dwn = (int)(Math.random() * 2);
				if( up_dwn == 0 ){
					FitWords.fitEntryAcross(grid, x, y, words2, entries);
				}
				else if( up_dwn == 1 ){
					FitWords.fitEntryDown(grid, x, y, words2, entries);
				}
			}


			// Now generate  2 lists of ACROSS and DOWN clues from 'entries' and generate
			// a 'grid_init' to hold the clue indices.  REFRESH grid_init.
			grid_init = new String[x][y];
			for(int i=0; i<y; i++){
				for( int j=0; j<x; j++){
					grid_init[i][j] = "";
				}
			}

			//Clear clues from last attempt
			cluesACROSS.clear();
			cluesDOWN.clear();
			

			int num=0;
			for(int i=1; i<y-1; i++){
				for( int j=1; j<x-1; j++){

					boolean twoOnSameSite = false;
					for( int u=0; u<entries.size(); u++){

						if( entries.get(u).getX() == j  &&  entries.get(u).getY() == i){

							if( !twoOnSameSite ){   num++;   }
							twoOnSameSite=true;
							grid_init[j][i] = Integer.toString( num );
							if( entries.get(u).getDirection().equals("Across") ){
								cluesACROSS.add( Integer.toString(num) + ". " + entries.get(u).getDefinition() );
							}
							else if( entries.get(u).getDirection().equals("Down") ){
								cluesDOWN.add( Integer.toString(num) + ". " + entries.get(u).getDefinition()  );
							}						
						}
					}				
				}
			}	

			//Check that crossword is connected using HoshenKopelman. 
			//If not, generate another one.
			connected = HoshenKopelman.isConnected(grid, x, y);	


		}// End while( !connected )



		// Now draw both the solution and puzzle grids

		DrawSolution.main( grid, x, y );
		DrawProblem.main(grid_init, grid, x, y, cluesACROSS, cluesDOWN);





	}// End main method___________________________________________________
	//____________________________________________________________________
	
	
	

	
	public static void printGrid(String[][] grid, int x, int y){
		System.out.println();
		for(int i=1; i<y-1; i++){
			for( int j=1; j<x-1; j++){
				System.out.print( grid[j][i] + " ");
			}
			System.out.println();
		}
	}



}
