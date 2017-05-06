package programmingExercices;

import java.lang.reflect.Constructor;

public class Sum10 {

	int n;
	
	int som=0;
	
  public Sum10( int a) {
	  
	n=a;
	
}
	 
 
    public int sumOf10() {
		
    	  for( int i=0; i<=10; i++){
    		  
    		  som +=i;
    		 
    		  
    	  }
    	 
    	  return som;
		
	}
	
	public static void main(String[] args) {
		
		Sum10  s= new Sum10(10);
		 
	  
		System.out.println(s.sumOf10());
	}

}
