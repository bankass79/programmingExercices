package programmingExercices;

/*
 * Prompt user for an int, and convert to its equivalent hexadecimal number.
 */
import java.util.Scanner;
public class Dec2Hex {
	public static void main(String[] args) {
		int dec; // The input decimal number
		String hexStr = ""; // The equivalent hex String, accumulating from empty String
		int radix = 16; // Hex radix
		char[] hexChar = {'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F'}; // Use this array as lookup table
		// Read input
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a decimal number: ");
		dec = in.nextInt();
		// Repeated division and get the remainder
		while (dec > 0) {
			int hexDigit = dec % radix;
			hexStr = hexChar[hexDigit] + hexStr; // append in front of the hex string
			dec = dec / radix;
		}
		System.out.println("The equivalent hexadecimal number is " + hexStr);
		in.close();
	}

}
