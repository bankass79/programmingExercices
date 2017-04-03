package programmingExercices;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * In stations and airports you often see this type of screen. Have you ever asked yourself how it might be possible to simulate this display on a good old terminal? We have: with ASCII art!
 * 
 * ASCII art allows you to represent forms by using characters. To be precise, in our case, these forms are words. For example, the word "MANHATTAN" could be displayed as follows in ASCII art:
 * # #  #  ### # #  #  ### ###  #  ###
 * ### # # # # # # # #  #   #  # # # #
 * ### ### # # ### ###  #   #  ### # #
 * # # # # # # # # # #  #   #  # # # #
 * # # # # # # # # # #  #   #  # # # #
 * ​Your mission is to write a program that can display a line of text in ASCII art.
 * 
 * INPUT:
 * Line 1: the width L of a letter represented in ASCII art. All letters are the same width.
 * Line 2: the height H of a letter represented in ASCII art. All letters are the same height.
 * Line 3: The line of text T, composed of N ASCII characters.
 * Following Lines: the string of characters ABCDEFGHIJKLMNOPQRSTUVWXYZ? Represented in ASCII art.
 * 
 * OUTPUT:
 * The text T in ASCII art.
 * The characters a to z are shown in ASCII art by their equivalent in upper case.
 * The characters which are not in the intervals [a-z] or [A-Z] will be shown as a question mark in ASCII art.
 * 
 * CONSTRAINTS :
 * 0 < L < 30
 * 0 < H < 30
 * 0 < N < 200
 * 
 * 
 * 
 *
 */
public class AsciiChart {
	
	   static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?";


	    public static void main(String args[]) {
	        Scanner in = new Scanner(System.in);
	        int L = in.nextInt(); // Lageur d'une lettre repséentéé en ASCII
	        int H = in.nextInt(); // La hauteur d'une lettre en art ASCII
	      
	      
	        if (in.hasNextLine()) {
	            in.nextLine();
	        }
	      
	        String T = in.nextLine().toUpperCase(); // La ligne de texte composée de N caractères ASCII
	        
	        ArrayList <String> ascII= new ArrayList <String> ();
	        
	        for (int i = 0; i < H; i++) {
	            String ROW = in.nextLine();
	            ascII.add(ROW);
	        }
	    List<Integer> pos= new ArrayList<Integer> ();

	   for(char c : T.toCharArray()) {
				if(c < 'A' || c > 'Z') pos.add(26);
				else {
					for(char letter : alphabet.toCharArray()) {
						if(c == letter) {
						 pos.add(alphabet.indexOf(letter));
						}
					}
				}
			}
			
			//print result
			for(int j = 0; j < H; j++) {
				for(int k = 0; k < pos.size(); k++) {
					System.out.print(ascII.get(j).substring(pos.get(k)*L, pos.get(k)*L + L));
				}
				System.out.println();
			}
			in.close();
	        // Write an action using System.out.println()
	        // To debug: System.err.println("Debug messages...");

	      //  System.out.println("answer");
	    }
}