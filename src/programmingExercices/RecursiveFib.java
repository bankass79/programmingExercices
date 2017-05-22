package programmingExercices;

import java.util.Scanner;

public class RecursiveFib {

	public static void main(String[] args) {

Scanner input= new Scanner(System.in);

System.out.println("Entrez n:");

int n= input.nextInt();

for( int i =1; i<n; i++){
	
	long f = fib(i);
	
	System.out.println("fib(" + i +  ") = " + f);
}

	}

	private static long fib(int n) {
		
		if(n<=2){
			
			return 1;
		}else{
			
			return fib(n-1) + fib(n-2);
		}
		
		
	}

}
