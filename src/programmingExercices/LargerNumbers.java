package programmingExercices;

import java.util.Arrays;

/**
 * @author amadou
 * @param numbers 
 * @return maximum number for given array
 */
public class LargerNumbers {

	
	public static int max (int [] numbers){
		
		if(numbers==null || numbers.length==0){
			
			
			throw new IllegalArgumentException("Invalid input:" + Arrays.toString(numbers));
		}
		
		int max = numbers[0];
		
		for(int i=1; i<numbers.length; i++){
			
			if (numbers[i]> max){
				
				max= numbers[i];
			}
		}
		
		
		
		return max;
		
		
	}
	
	
	
  
}


// Method calculate minimum  of an Array return the minimum number from array

class SmallerNumbers {
public static int min (int [] numbers){
	
	if(numbers==null || numbers.length==0){
		
		
		throw new IllegalArgumentException("Invalid input:" + Arrays.toString(numbers));
	}
	
	int min = numbers[0];
	
	for(int i=1; i<numbers.length; i++){
		
		if (numbers[i]< min){
			
			min= numbers[i];
		}
	}
	
	
	
	return min;
	
}
}
