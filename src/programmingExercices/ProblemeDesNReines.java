package programmingExercices;

public class ProblemeDesNReines {


	static boolean check( int [] cols, int r){

		for(int q=0; q<r; q++){

			if( cols[q]==cols[r] || Math.abs(cols[q]- cols[r])==r-q){

				return false;

			}else{
				return true;
			}



		}
		return false;
	}

	static boolean findSolutionRec(int [] cols, int r){

		if(r==cols.length){

			return true;
		}


		for (int c=0; c<cols.length; c++){


			cols[r]=c;

			if(check(cols,r) && findSolutionRec(cols,  r+1)){

				return true;
			}
		}
		return false;


	}


	static int [] findSolution(int n){

		int [] cols=new int [n];
		if(findSolutionRec(cols, 0)){

			return cols;
		}else{

			throw new Error("pas de solution  pour n+ n");
		}


	}
	
	static int countRec(int [] cols, int r){
		
		if(r==cols.length){
			
			return 1;
			
		}
		int f=0;
		for (int h=0; h <cols.length;h++ ){
			
			cols[r]=h;
			
			if(check(cols,r)){ 
				
				f+=countRec(cols,r+1);
				
			}
		}
		return f;
	}
}
