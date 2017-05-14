package programmingExercices;


import java.util.Scanner;

public class ScannerMaxOfArray {





	public static void main(String[] args) {


		System.out.println("Saisissez vos chiffres");

		Scanner input = new Scanner (System.in);


		int max = Integer.MIN_VALUE;

		int min=Integer.MAX_VALUE;


		int[] tabArray = new int [128]; 


		for(int i=0; i<tabArray.length; i++){

			tabArray[i]=input.nextInt();


			if(input.nextInt()==0){

				break;
			}
		}


		for(int x: tabArray){

			if(x > max){

				max=x;

			} 

		}
		System.out.println("le max est:"+ " "+ max);
	}

}

