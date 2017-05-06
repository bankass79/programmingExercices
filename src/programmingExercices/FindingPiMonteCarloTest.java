package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindingPiMonteCarloTest {
	
	// FindingPiMonteCarlo  fpi= new FindingPiMonteCarlo();
	
	 
	 @Test
	public void testCalculerPI() {
		
		  new FindingPiMonteCarlo().calculerPI(50000);
		
		
	}

}
