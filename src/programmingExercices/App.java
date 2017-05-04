package programmingExercices;

import java.util.function.BiFunction;

public class App {

	public static void main(String[] args) {
		String s = "Java";
		String n = "SE";
		// Line n1
		BiFunction<String, String, String> sf = (s1, n1) -> s1.concat(n1);
		
		System.out.println(sf.apply(s, n));
	}

}
