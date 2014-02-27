import java.util.*;
import java.io.*;


public class ReadWords {	

	
	// Reads in ArrayList<Word> ( = words + listOfDefinitions) from file
	public static ArrayList<Word> getWordsandDefs(String filename)throws IOException{
		
		ArrayList<Word> words = new ArrayList<Word>();
	
		BufferedReader in = new BufferedReader( new FileReader(filename) );
	    
		String s = "";
		Scanner scan = new Scanner(s);
		while(true){
			try{
				s = in.readLine();
				scan = new Scanner(s);
				String theword = scan.next();
				
				// Now read in single definitions -------- maybe this can be an arraylist of alternative 
			    // definitions later?
				String singleDefinition = "";
				while(true){
					try{
						String nextBit = scan.next();
						singleDefinition = singleDefinition +  nextBit + " ";						
					}catch(NoSuchElementException nsee){
						break;
					}
				}
							
				Word wordObj = new Word(theword, singleDefinition);				
				words.add( wordObj );
	
								
			}catch(NullPointerException e){
				break;
			}
		}	
		return words;
	}

	
	
	
	

	// Reads in ArrayList of words from file
	public static ArrayList<String> getWords(String filename)throws IOException{
		
		ArrayList<String> words = new ArrayList<String>();
	
		BufferedReader in = new BufferedReader( new FileReader(filename) );
		String s = "";
		Scanner scan = new Scanner(s);
		while(true){
			try{
				s = in.readLine();
				scan = new Scanner(s);
				String word = scan.next();
				words.add( word );
				
			}catch(NullPointerException e){
				break;
			}
		}	
		return words;
	}
	
}
