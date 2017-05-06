package programmingExercices;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Find the first non repeated character in a string
 *
 */

/**
 * Pseudo Algorithm

1.   First create the  character count hash table .
 
          For each character
            If there is no value stored in the character
                     set it to 1 .
            else
                     increment the value of the character by 1 .

2.  Scan the string
           For each character
           return character if the count in hash table is 1 .
           If no character have count 1 , return null 
 *
 */

public class TheFirstNonRepeatString {
	
	 public static void main(String[] args)
	    {
	        // TODO Auto-generated method stub
	        
	        System.out.println(" Please enter the input string :" );
	        Scanner in = new Scanner (System.in);
	        String s=in.nextLine();
	        char c=firstNonRepeatedCharacter(s);
	        System.out.println("The first non repeated character is :  " + c);
	    }
	    
	    public static Character firstNonRepeatedCharacter(String str)
	    {
	        HashMap<Character,Integer>  characterhashtable= 

	                     new HashMap<Character ,Integer>();
	        int i,length ;
	        Character c ;
	        length= str.length();  // Scan string and build hash table
	        for (i=0;i < length;i++)
	        {
	            c=str.charAt(i);
	            if(characterhashtable.containsKey(c))
	            {
	                // increment count corresponding to c
	                characterhashtable.put(  c ,  characterhashtable.get(c) +1 );
	            }
	            else
	            {
	                characterhashtable.put( c , 1 ) ;
	            }
	        }
	        // Search characterhashtable in in order of string str
	        
	        for (i =0 ; i < length ; i++ )
	        {
	            c= str.charAt(i);
	            if( characterhashtable.get(c)  == 1 )
	            return c;
	        }
	        return null ;
	    }

}
