import java.util.*;

/*  
 *  It takes as input some 'toWorkWith' string in grid that could potentially fit a word,
 *  subject to the constraints of the letters already present, and tries to find the **biggest**
 *  word that satisfies these constraints.
 */

public class SearchWords {

	
	// Given a "toWorkWith" String, search word list for suitable entries
	public static String[] findWord(ArrayList<Word> wordlist, String toWorkWith){
		
		
		String wordFound="";
		String definition = "";
		String hold = toWorkWith;
		String[] word_and_def = new String[2];

		
		//Make list of all possible lengths the desired word could be
		ArrayList<Integer> possSizes = new ArrayList<Integer>();
		for( int i=2; i<hold.length()-1; i++){
			if( hold.charAt(i+1) == '_'){
				int possL = (i+1);
				possSizes.add( possL );
			}
		}
		possSizes.add( hold.length() );

		
		
		while( possSizes.size() > 0 ){

			//pick a size randomly -- EDITED, SEE BELOW
			//int rand_point = (int)( Math.random() * possSizes.size() );
			//int size = possSizes.get( rand_point );

			// Changed above so as to always try and fit the biggest word possible first
			int size = possSizes.get( possSizes.size()-1 );
			
			//create template
			String hold_2 = "";
			for( int j=0; j<size; j++){
				hold_2 = hold_2 + hold.charAt(j);
			}

			boolean allBlank=true;
			for(int t=0; t<hold_2.length(); t++){
				if( hold_2.charAt(t) != '_'){
					allBlank = false;
				}
			}


			// Check word list --- do this in a random way. Start at a random position in the list,
			// and go forward or back with equal probability
			int rand_start_point = (int)( Math.random() * wordlist.size() );						
			// Choose to cycle forward (0), or back (1) randomly
			int forw_bk = (int)( Math.random()* 2 );

			if( forw_bk == 0 ){
				for( int w=rand_start_point; w<wordlist.size(); w++){
					String try_word = wordlist.get(w).getWord();
					boolean match = true;
					//check sizes match				
					if( try_word.length() == size ){

						if( allBlank ){
							wordFound = try_word;
							definition = wordlist.get(w).getDef();
							match = true;
						}
						else{
							//check existing characters match
							for( int c=0; c<size; c++){
								if( hold_2.charAt( c ) != '_'){
									if( hold_2.charAt(c) != try_word.charAt(c) ){
										match = false;
									}
								}
							}
						}
					}
					else{
						match = false;
					}
					if( match == true ){
						wordFound = try_word;
						definition = wordlist.get(w).getDef();
						w = wordlist.size() + 5; //(so as to break loop)
						possSizes.clear();
					}			
					
				}//end *FORWARD* for loop	
			}// if(frw_bk == 0 )
			
			
			// else cycle through words list in reverse direction
			else if( forw_bk == 1 ){
				for( int w=rand_start_point;  w>0;  w--){
					String try_word = wordlist.get(w).getWord();
					boolean match = true;
					//check sizes match				
					if( try_word.length() == size ){

						if( allBlank ){
							wordFound = try_word;
							definition = wordlist.get(w).getDef();
							match = true;
						}
						else{
							//check existing characters match
							for( int c=0; c<size; c++){
								if( hold_2.charAt( c ) != '_'){
									if( hold_2.charAt(c) != try_word.charAt(c) ){
										match = false;
									}
								}
							}
						}
					}
					else{
						match = false;
					}
					if( match == true ){
						wordFound = try_word;
						definition = wordlist.get(w).getDef();
						w = -3; // (so as to break loop)
						possSizes.clear();
					}			
					
				}//end *BACKWARD* for loop	
			}//if( forw_bk == 1)			
			
			
			
			//Check if there are other possible sizes the word could take
			if( possSizes.size() !=0 ){
				//possSizes.remove( rand_point );
				
				// Remove the largest size, and thus try next largest
				possSizes.remove( possSizes.size()-1 );
			}
		}// end while loop

		word_and_def[0] = wordFound;   word_and_def[1] = definition;
		return word_and_def;
//		return wordFound;
	}


}
