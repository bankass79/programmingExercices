package programmingExercices;

import java.util.Random;
import java.util.Scanner;

/**
 * @author amadou
 * 
 * l'objectif de cet exercice est d'estimer la valeur de Pi (math) par l'algorithme de Monte-Carlo
 *
 */


public class FindingPiMonteCarlo {


	public static double calculerPI(int npts) {


		int  i, n = 0;

		double x;
		double y;
		double estim_Pi, error_Pi, err_Rel;

	/*	System.out.println("Saisir le nombre de points souhaités");

		Scanner in = new Scanner(System.in);

		npts= in.nextInt();*/

		//Random randomGen = new Random (System.currentTimeMillis());		

		while (npts<1 || npts>500000) {

			System.out.println(" ATTENTION! le nombre de points doit être un entier naturel non nul inférieur ou egal à  500 000 ! ");

			//npts= in.nextInt();
		}

		for(i =0; i< npts; i++){

		
			x= Math.random();
			y= Math.random();
		

			if( Math.pow(x, 2)+ Math.pow(y, 2) <=1){

				n= n+1;
			}

		}
		estim_Pi= 4.0*n/npts;
		System.out.print("Avec");
		System.out.print(" "+npts);
		System.out.print("Points, la valeur estimée de PI vaut: ");
		System.out.print(estim_Pi);
		System.out.print(", soit une erreur relative d'environ");

		err_Rel= ((estim_Pi/Math.PI-1));

		System.out.println(err_Rel);
		System.out.println("%");
		
		
		return estim_Pi;



	}

}
