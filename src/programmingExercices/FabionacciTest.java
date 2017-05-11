package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

public class FabionacciTest {

	
	Fabionacci fabionnaccci= new Fabionacci();
	@Test
	public void testF() {
		
		assertEquals(2, fabionnaccci.f(2));
	}

}
