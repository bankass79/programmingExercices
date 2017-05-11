package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindingPiMonteCarloTest {
	
	 FindingPiMonteCarlo  fpi= new FindingPiMonteCarlo();
	
	 
	 
	 @Test
	public void testCalculerPI() {
		
	assertEquals("3.13512 ", new FindingPiMonteCarlo().calculerPI(50000));	  ;
		
		
	}

}
