package programmingExercices;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculateRotationTest {
	@Test
	public void test() {
		assertEquals(-1, CalculateRotation.shiftedDiff("hoop", "pooh"));
		assertEquals(2, CalculateRotation.shiftedDiff("coffee", "eecoff"));
		assertEquals(4, CalculateRotation.shiftedDiff("eecoff", "coffee"));
	}
}
