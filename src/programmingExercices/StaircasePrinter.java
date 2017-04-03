package programmingExercices;

public class StaircasePrinter {
	
	public  static void  drawStaircace (){
		
		System.out.print("\t\t\t\t+-------+\r");
		
		System.out.print("\t\t\t\t|       |\r");
		
		System.out.print("\t\t\t+-------+-------+\r");
		
		System.out.print(" \t\t\t|       |       |\r");
		
		System.out.print("\t\t+-------+-------+-------+\r");
		System.out.print("\t\t|       |       |       |\r");
		
		System.out.print("\t+-------+-------+-------+-------+\r");
		
		System.out.print("\t|       |       |       |       |\r");
		
		System.out.print("\t+-------+-------+------+--------+");
		
		
	}
	
	public static void main (String [] args){
		
		
		StaircasePrinter sp= new StaircasePrinter();
		
		sp.drawStaircace();
	}

}
