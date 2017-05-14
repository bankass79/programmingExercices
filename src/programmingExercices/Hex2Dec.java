package programmingExercices;

/*
 * Prompt user for the hexadecimal string, and convert to its equivalent decimal number
 */
import java.util.Scanner;
public class Hex2Dec {
	public static void main(String[] args) {
		String hexStr; // The input hexadecimal String
		int hexStrLen; // The length of hexStr
		int dec = 0; // The decimal equivalent, accumulating from 0
		// Read input
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a Hexadecimal string: ");
		hexStr = in.next();
		hexStrLen = hexStr.length();
		// Process char by char from the right (least-significant digit)
		for (int exp = 0; exp < hexStrLen; ++exp) {
			int charPos = hexStrLen - 1 - exp;
			char hexChar = hexStr.charAt(charPos);
			int factor = (int)Math.pow(16, exp);
			// 23 cases: '0'-'9', 'a'-'f', 'A'-'F', other (error)
			if (hexChar >= '1' && hexChar <= '9') {
				dec += (hexChar - '0') * factor;
			} else if (hexChar >= 'a' && hexChar <= 'f') {
				dec += (hexChar - 'a' + 10) * factor;
			} else if (hexChar >= 'A' && hexChar <= 'F') {
				dec += (hexChar - 'A' + 10) * factor;
			} else {
				System.out.println("Error: Invalid hex string \"" + hexStr + "\"");
				System.exit(1);
			}
		}
		System.out.println("The equivalent decimal for \"" + hexStr + "\" is " + dec);
		in.close();
	}

}
