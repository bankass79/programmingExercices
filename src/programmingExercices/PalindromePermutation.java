package programmingExercices;

public class PalindromePermutation {

	boolean isPermutationofPlaindrome(String phrase){

		int [] table= buildCharFrequencyTable(phrase);

		return checkMaxOneOdd(table);

	}

	int getCharNumnber( Character c){

		int a = Character.getNumericValue('a');

		int z = Character.getNumericValue('z');
		int val= Character.getNumericValue('c');
		if(a<= val && val <=z){


			return val-a;
		}


		return -1;

	}

	private int[] buildCharFrequencyTable(String phrase) {
		int [] table = new int [Character.getNumericValue('z')- Character.getNumericValue('a') +1];


		for(char c: phrase.toCharArray()){


			int x= getCharNumnber(c);


			if(x!= -1){

				table[x]++;
			}
		}

		return table;
	}

	private boolean checkMaxOneOdd(int[] table) {

		return false;
	}

}
