package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author amadou
 *
 */
public class NombrePremierTest {
	
	NombrePremier np = new NombrePremier();
	
	
	

	@Test
	public void testIsPrime() {
		
		
		assertEquals(false, np.isPrime(5));
	
	}
	
	@Test
	public void testIsPrime2(){
		
		assertEquals (true, np.isPrime2(13));
	}

}
