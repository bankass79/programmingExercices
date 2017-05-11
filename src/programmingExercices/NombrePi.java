package programmingExercices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NombrePi {
	
	static int i;
	
	static int npts;
	
	static int n=0;
	int x;
	int y;
	int ESTIM_PI;
	int ERR_REL;
	public static void main(String [] args){
	BufferedReader input= new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Saisir le nombre de points souhaités.");
	
	try {
		npts=Integer.parseInt(input.readLine());
	} catch (NumberFormatException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	while(npts<1 || Math.floor(npts)!=0 || npts>500000){
		
		
	}
	
	}
	/*
	 * 1 VARIABLES
2 i EST_DU_TYPE NOMBRE
3 NPTS EST_DU_TYPE NOMBRE
4 N EST_DU_TYPE NOMBRE
5 X EST_DU_TYPE NOMBRE
6 Y EST_DU_TYPE NOMBRE
7 ESTIM_PI EST_DU_TYPE NOMBRE
8 ERR_REL EST_DU_TYPE NOMBRE
9 DEBUT_ALGORITHME
10 //Initialisation des variables N et NPTS.
11 //La variable NPTS correspond au nombre total de points
souhaités.
12 //NPTS est un entier naturel non nul.
13 //La variable N correspond au nombre de points situés
dans le quart de disque.
14 N PREND_LA_VALEUR 0
15 AFFICHER "Saisir le nombre de points souhaités."
16 LIRE NPTS
* 17 TANT_QUE (NPTS<1 OU NPTS-floor(NPTS)!=0 OU NPTS>500000)
FAIRE
18 DEBUT_TANT_QUE
19 AFFICHER "ATTENTION ! Le nombre de points doit être un
entier naturel non nul inférieur ou égal à 500 000 !"
20 LIRE NPTS
21 FIN_TANT_QUE
22 POUR i ALLANT_DE 1 A NPTS
23 DEBUT_POUR
24 X PREND_LA_VALEUR random()
25 Y PREND_LA_VALEUR random()
26 SI (pow(X,2)+pow(Y,2)<=1) ALORS
27 DEBUT_SI
28 N PREND_LA_VALEUR N+1
29 TRACER_POINT (X,Y)
30 FIN_SI
31 SINON
32 DEBUT_SINON
33 TRACER_POINT (X,Y)
34 FIN_SINON
35 FIN_POUR
36 ESTIM_PI PREND_LA_VALEUR 4*N/NPTS
37 AFFICHER "Avec "
38 AFFICHER NPTS
39 AFFICHER " points, la valeur estimée de PI vaut : "
40 AFFICHER ESTIM_PI
41 AFFICHER ", soit une erreur relative d'environ "
42 ERR_REL PREND_LA_VALEUR (ESTIM_PI/Math.PI-1)*100
43 AFFICHER ERR_REL
44 AFFICHER "%."
45 FIN_ALGORITHME
	 * 
	 * 
	 * 
	 * */
	

}
