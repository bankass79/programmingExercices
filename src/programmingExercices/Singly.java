package programmingExercices;


/**
 * @author amadou
 * parcours de liste chain�e
 */
public class Singly {
	int elements;
	
	Singly next;
	
	public Singly(int e, Singly s) {
		
		e=elements;
		s=next;
		
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

}
