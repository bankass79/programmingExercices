package programmingExercices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author amadou
 * 
 * this programm  display a name inside a box on the console screen
 *
 */
public class NamePrinter {
	
	// méthode  qui crée un box avec le nom de l'utilisateur
	
	public static void  dessineUneBoite (String nom){
		
		// initialisation de la longueur du nom
		
		int longueurNom= nom.length();
		
		//on  dessine la ligne haut du box 
		
		System.out.print("+");
		
		for(int i=0; i<longueurNom; i++){
			
			System.out.print("-");
			
		}
		
		// retour à la ligne
		
		System.out.print("+\n");
		
		// dessine les bordures gauche et droite du box rempli du nom
					
		System.out.print("|" + nom + "|");
					
		// dessine la ligne du bas de la  box contenant le nom
		
		            System.out.print("\n+");
	
		for(int i=0; i<longueurNom; ++i){
			
			System.out.print("-");
		}
		
		System.out.print("+\n");
	}
	
	
	public static void main (String [] args){
		
		  String nom="";
		  
		// Bufferedreader et InputStream vont ensemble, ici lit l'entrée clavier saisie par l'opérateur
		
		InputStreamReader in = new InputStreamReader (System.in);
		
		// lecture ligne par ligne par BufferedReader de l'entrée clavier
		
		BufferedReader read= new BufferedReader(in);
		
		// On demande à l'opérateur de saisir son nom
		  
		  System.out.println("Quel est votre nom et saisissez-le sur clavier?:");
		
		try {
			nom=read.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		// On ecrit sur la console le nom saisi par l'opérateur avec la méthode dessineUneBoite
		
		dessineUneBoite(nom);
 
	}

}
