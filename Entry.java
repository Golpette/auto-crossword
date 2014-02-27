
public class Entry {

	// 'Entry' object needed for every word fitted into crossword.
	//  Holds starting position, direction (across/down), the actual word, its definition
	
    int start_x;
    int start_y;
    String direction;
    String word;
    String definition;
    

   public Entry(int start_x, int start_y, String direction, String word, String definition){
	   this.start_x = start_x;
	   this.start_y = start_y;
	   this.direction = direction;
	   this.word = word;
	   this.definition = definition;
   }
   
   
   public String toString(){
	   return "("+ this.start_x + "," + this.start_y + ") " + this.direction + ": " + this.word + " Def:" + this.definition;
   }
   
   public int getX(){
	   return this.start_x;
   }
   
   public int getY(){
	return this.start_y;   
   }
   
   public String getDirection(){
	   return this.direction;
   }
   
   public String getWord(){
	   return this.word;
   }
   
   public String getDefinition(){
	   return this.definition;
   }
   


}
