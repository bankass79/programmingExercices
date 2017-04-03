package programmingExercices;

public class TicTacToeBoarderPrinter {
	
	public static void drawOneCell(){
		
		int taille=1;

	  
	  for(int i=0; i<taille; i++){
		  
		  System.out.print("+--------+--------+------+\n");
		  
		  System.out.print("|"+ "  " +      "     " +   " |" + "        "  +    "|" +   "      " +  "|\n");
		
		  System.out.print("+--------+--------+------+\n");
		  
		  System.out.print("|"+ "  " +      "     " +   " |" + "        "  +    "|" +   "      " +  "|\n");
		  
		  System.out.print("+--------+--------+------+\n");
		  
		  System.out.print("|"+ "  " +      "     " +   " |" + "        "  +    "|" +   "      " +  "|\n");
		 
		  System.out.print("+--------+--------+------+\n");
	 
	  }
	  	  
	}

	public static void main(String[] args) {
	
		TicTacToeBoarderPrinter cell= new TicTacToeBoarderPrinter();
	
		cell.drawOneCell();
	
     
	}

}
