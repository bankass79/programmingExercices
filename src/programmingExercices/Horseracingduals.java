package programmingExercices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Horseracingduals {
	
	
	  public static void main(String args[]) {
	        Scanner in = new Scanner(System.in);
	        int N = in.nextInt();
	        in.nextLine();

	        List<Integer> strengths = new ArrayList<Integer>();
	        for (int i = 0; i < N; i++) {
	            strengths.add(in.nextInt());
	            in.nextLine();
	        }

	        Collections.sort(strengths);

	        Integer min = Integer.MAX_VALUE;
	        for(int j = 0; j < strengths.size() - 1; j++) {
	            int diff = Math.abs(strengths.get(j) - strengths.get(j+1));

	            if(diff < min) {
	                min = diff;
	            }

	            if(min == 1) {
	                break;
	            }
	        }
	        System.out.println(min);
	}

}
