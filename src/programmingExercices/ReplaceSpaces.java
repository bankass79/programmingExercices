package programmingExercices;

/**
 * @author amadou
 *
 */
public class ReplaceSpaces {



	public static void replaceSpace(char [] str, int trueLength){


		int index, j=0, spaceCount=0;

		for ( j = 0; j < str.length; j++) {

			if( str [j] == ' '){


				spaceCount++;
			}


			index= trueLength+ spaceCount*2;

			if(trueLength<str.length){

				str[trueLength]= '\0';

				for( j= trueLength-1; j>=0; j--){
					if( str[j]== ' '){
						str[index-1]='0';

						str[index-2]= '2';

						str[index-3]='%';
						index= index-3;
					} else{

						str[index-1]= str[j];
						index--;
					}
				}
			}


		}


	}
}
