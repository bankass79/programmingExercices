package programmingExercices;


/**
 * @author amadou
 * parcours de liste chainée
 */
public class Singly {
	int elements;
	
	Singly next;
	
	public Singly(int e) {
		
		e=elements;
		next=null;
		
	}
	
 static	boolean contains (Singly s, int x) {
	 
	 while (s !=null) {
		 
		if(s.elements==x){
			
			return true;
		}else{
			
			
			s=s.next;
		}
		
	}
	return false;
 }	
 
 
 static int length(Singly s){
	
	 
	 int len=0;
	 
	 for (; s != null; s=s.next) {
		 
		 len++;
		
	}
	 
	 return len;
	 
	 
 }

}
