import java.util.*;

// Needed this to hold list of words and definitions from input file.
// Word object holds a word, its definition (singleDef for now)
// and an ArrayList of multiple definitions for later on

public class Word {

	String word;
	ArrayList<String> definitions;
	String singleDef;
	
	public Word(String word, ArrayList<String> definitions){
		this.word = word;
		//this line might be problematic...
		this.definitions = definitions;
	}
	
	public Word(String word, String singleDef){
		this.word = word;
		this.singleDef = singleDef;
	}
	
	

	public String getWord(){
		return this.word;
	}
	public String getDef(){
		return this.singleDef;
	}
	public String getDefinition(int def){
		return this.definitions.get( def );
	}
	
	
}
