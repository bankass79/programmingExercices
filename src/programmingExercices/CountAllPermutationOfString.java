package programmingExercices;

public class CountAllPermutationOfString {

	
	static void permutation(String str){
		
		permutation (str, "");
	}

	private static void permutation(String str, String string) {
		
		if(str.length()==0){
		
	         System.out.println(string);
		}else{
		
		
		for (int i = 0; i < str.length(); i++) {
			
			String rem= str.substring(0, i) + str.substring(i+1);
			
			permutation(rem,  string + str.charAt(i));
		}
			
		}
		
	}
}
