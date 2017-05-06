package programmingExercices;

/**
 * @author amadou
 * Cet algorithme donne tous les diviseurs positifs d'un entier
  naturel N strictement positif donné.
 *VARIABLES
   N EST_DU_TYPE NOMBRE
 I EST_DU_TYPE NOMBRE
 R EST_DU_TYPE NOMBRE
 DEBUT_ALGORITHME
 //Première saisie de la valeur de la variable N
 AFFICHER "Saisir la valeur de l'entier naturel non nul
N."
 LIRE N
 TANT_QUE (N<=0 OU N-floor(N)!=0 OU N>200000) FAIRE
  DEBUT_TANT_QUE
 AFFICHER "ATTENTION ! N doit être un entier nature non
nul inférieur ou égal à 200 000 !"
 LIRE N
  FIN_TANT_QUE

//La valeur de la variable N est valide. On démarre la
recherche des diviseurs.
 AFFICHER "Les diviseurs de "
 AFFICHER N
 AFFICHER " sont : "
 POUR I ALLANT_DE 1 A N
 DEBUT_POUR
 R PREND_LA_VALEUR N%I
 SI (R==0) ALORS
 DEBUT_SI
 AFFICHER " "
 AFFICHER I
 FIN_SI
 FIN_POUR
 FIN_ALGORITHME
 */
public class DiviseurNonNull {

	double n, i, r;
	
	public DiviseurNonNull( double a, double b, double c) {

		  this.i=a;
		  this.n=b;
		  this.r=c; 

	}
	//La valeur de la variable N est valide. On démarre la recherche des diviseurs.
	void diviseurPositif(){
		
		while (n<=0 || Math.floor(n) !=0 || n>200000) {
			
			System.out.println("ATTENTION ! N doit être un entier nature non nul inférieur ou égal à 200 000 !");
		}
		
		for (int i = 0; i < n; i++) {
			
			r= (n/i);
			
			if(r==0){
				
				System.out.println(r);
				
				System.out.println(i);
			}else{
				
				System.out.println(n);
			}
			
		}
	}
	
}
