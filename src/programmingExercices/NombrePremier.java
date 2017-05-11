package programmingExercices;

/**
 * @author amadou
 * this programm determine if a number is prime
 */
public class NombrePremier {
	
	boolean isPrime(int n) {
		
		for (int i = 2; i < i*i; i++) {
			
			if ((n%i)==0) {
				
				return false;
			}
		}
		
		return true;
	}
	
	
	boolean isPrime2(int n){
		
		for(int x=2; x<Math.sqrt(n); x++){
			
			if( n% x ==0){
				return false;
			}
		}
		
		return true;
	}

}
