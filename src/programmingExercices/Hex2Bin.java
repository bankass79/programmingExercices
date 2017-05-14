package programmingExercices;

import java.util.Scanner;
public class Hex2Bin {
	public static void main(String[] args) {
		String hexStr; // The input hexadecimal String
		int hexStrLen; // The length of hexStr
		String binStr =""; // The equivalent binary String, accumulating from empty String
		// Lookup table for the binary string corresponding to Hex digit '0' (index 0) to 'F' (index 15)
		String[] binStrs
		= {"0000","0001","0010","0011","0100","0101","0110","0111",
				"1000","1001","1010","1011","1100","1101","1110","1111"};
		// Read input
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a Hexadecimal string: ");
		hexStr = in.next();
		hexStrLen = hexStr.length();
		// Process the string from the left
		for (int pos = 0; pos < hexStrLen; ++pos) {
			char hexChar = hexStr.charAt(pos);
			if (hexChar >= '0' && hexChar <= '9') {
				binStr += binStrs[hexChar-'0']; // index into the binStrs array
			} else if (hexChar >= 'a' && hexChar <= 'f') {
				binStr += binStrs[hexChar-'a'+10];
			} else if (hexChar >= 'A' && hexChar <= 'F') {
				binStr += binStrs[hexChar-'A'+10];
			} else {
				System.err.println("Error: Invalid Hex string \"" + hexStr + "\"");
				System.exit(1); // quit
			}
		}
		System.out.println("The equivalent binary for \"" + hexStr + "\" is \"" + binStr + "\"");
		in.close();
	}
}