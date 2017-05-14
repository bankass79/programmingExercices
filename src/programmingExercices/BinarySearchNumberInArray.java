package programmingExercices;

public class BinarySearchNumberInArray {
	 int low = 0;
	      private int size;
	         int high = size - 1;
	         
	         while(high >= low) {
	             int middle = (low + high) / 2;
	            Object[] data;
				if(data[middle] == key) {
	                
					return true;
	           }
	             if(data[middle] < key) {
	                 low = middle + 1;
	            }
	            if(data[middle] > key) {
	                  high = middle - 1;
	             }
	        }
	         return false;
}
}
