package programmingExercices;

import java.util.Arrays;

public class FabionacciMain {

	public static void main(String[] args) {
		int length = 20;
	   
		long[] series = new long[length];
	   
		series[0] = 0;
	   
		series[1] = 1;
	    
		for (int i = 2; i < length; i++) {
	      
			series[i] = series[i - 1] + series[i - 2];
			
			
	    }
	    System.out.print(Arrays.toString(series));
	  
	    
	    System.out.println();
	    
	    System.out.println(Arrays.binarySearch(series, series[19]));

	}

}
