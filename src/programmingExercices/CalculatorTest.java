package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
    public void testAssociativityOfMultiplication() throws Exception {
        Calculator calc = new Calculator();
        // Try to test for behavior, rather than specific inputs
        // Otherwise people may try to cheat, and only program for exercise inputs!
        for (int i = 0; i < 100; i++) {
            Double a = Math.random();
            Double b = Math.random();
            Double c = Math.random();
            String message = String.format("(%g * %g) * %g == %g * (%g * %g)", a, b, c, a, b, c);
            assertEquals(message,
                    calc.multiply(calc.multiply(a, b), c),
                    calc.multiply(a, calc.multiply(b, c)),
                    1E-14);
        }
    }
}
