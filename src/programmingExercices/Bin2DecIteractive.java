package programmingExercices;

/*
 * Prompt user for a binary string, and convert into its equivalent decimal number.
 * Validate the input string.
 * Repeat the program, until user chooses to quit.
 * Allow blank in the binary string, e.g., "0100 1000".
 */
import java.util.Scanner;
public class Bin2DecIteractive {
	public static void main(String[] args) {
		String inStr; // The input binary string
		Scanner in = new Scanner(System.in);
		boolean done = false; // boolean flag for controlling the loop
		while (!done) {
			// Prompt for the input string
			System.out.print("Enter a binary string or 'q' to quit: ");
			inStr = in.nextLine(); // read entire line including blanks
			if (inStr.equals("q")) {
				System.out.println("Bye!");
				done = true;
			} else if (!isValidBinStr(inStr)) {
				System.out.println("Error: Invalid binary string: \"" + inStr + "\", try again.");
			} else {
				System.out.println("The equivalent decimal number for \"" + inStr + "\" is " + bin2Dec(inStr));
			}
		}
		in.close();
	}
	// Return true if the given string contains only binary numbers and blanks.
	public static boolean isValidBinStr(String binStr) {
		for (int i = 0; i < binStr.length(); ++i) {
			char binChar = binStr.charAt(i);
			if (binChar != '0' && binChar != '1' && binChar != ' ') {
				return false; // break on first invalid char
			}
		}
		return true;
	}
	// Return the equivalent decimal number of the given binary string.
	// Blanks are allowed in the binStr, e.g., "0010 1000".
	// Assume that the input contains '0', '1' or ' '.
	public static int bin2Dec(String binStr) {
		int binStrLen = binStr.length(); // The length of binStr
		int dec = 0; // Equivalent decimal number, accumulating from 0
		// We need to process from the right (i.e. Least-significant bit)
		for (int charPos = binStrLen - 1, exp = 0; charPos >= 0; --charPos) {
			char binChar = binStr.charAt(charPos);
			// 3 cases: '1' (add to dec, ++exp), '0' (++exp), ' ' (do nothing)
			if (binChar == '1') {
				dec += (int)Math.pow(2, exp);
				++exp;
			} else if (binChar == '0') {
				++exp;
			} // else for ' ' (do nothing)
		}
		return dec;
	}
}
