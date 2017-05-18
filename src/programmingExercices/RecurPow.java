package programmingExercices;

public class RecurPow {
	
	static double powIt(double x, double n){
		
		double res=x;
	
		
		while (n-->1) {
			
			res*=x;
			
		}
		
		return res ;
		
		
	}
	
	

}
