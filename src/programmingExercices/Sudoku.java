package programmingExercices;

public class Sudoku {
	// les grilles contiennent des valeurs comprises entre 0 et 9
	private int [] grid = new int [81]; 
	// le numero de ligne est  compris entre 0 et 8 
	
	int row (int c) {
		
		return c/9;
	}
  
	
	//  column colonne, 
	
	int column (int c){
		
		return c % 9;
	}
	
	
 //sous-groupe de case representé par l'indice c
	
	int group(int c){
		
		return 3* (row(c)/3) +column(c)/3; 
	}
	
	
	/** sameZone () permet de  vérifier si deux cases c1 et c2 appartiennent à la même 
	 * colonne, la même ligne ou le même sous-groupe 
	 * @param c1
	 * @param c2
	 * @return
	 */
	boolean sameZone(int c1, int c2){
		
		return row (c1)== row(c2) || column(c1)==column(c2) || group(c1)==group(c2);
	}
	
	
	// Check vérifie s'il y a conflit  entre valeurs de deux cases différentes
	
	
	boolean  check (int p){
		
		for( int c=0; c<81; c++){
			
			if (c!=p && sameZone(p,c) && this.grid [p]==this.grid[c]){
				
				return false;
			}
		}
		return true;
	}
	
	
	// essaye de résoudre la grille courante et signale le succès
	boolean solve(){
		
		
		for( int c=0; c<81; c++){
			
			if(this.grid[c]==0){
				
				
				for(int v=1; v<10; v++){
					
					this.grid[c]=v;
					
					if(check(c) && solve()){
						
						return true;
					}
					this.grid[c]=0;
					return false;
				}
			}
		}
		
		
		return true;
		
		
	}
}
