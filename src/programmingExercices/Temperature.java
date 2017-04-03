package programmingExercices;

import java.util.Scanner;

public class Temperature {
	
public static void main(String[] args) {
		
		//standard input
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		//if no temperatures provided
		if(n <= 0) {
			System.out.println(0); 
			in.close();
			return;
		}
		
		//find the closest number to the zero
		Integer closest = null;
		for(int i = 0; i < n; i++) {
			int current = in.nextInt();
			if(closest == null || Math.abs(closest) > Math.abs(current) 
					|| Math.abs(closest) == Math.abs(current) && closest < current) closest = current;
		}
		System.out.println(closest);
		in.close();
}

}
