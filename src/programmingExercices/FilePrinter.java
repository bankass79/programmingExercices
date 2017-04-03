package programmingExercices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilePrinter {
	public static void main (String [] args)throws IOException{
		File file = new File("fdata/nameFile");
		
		//File file = new File("fdata/nombre.txt");
		
		
	    if (file.isDirectory()) {
	    	
	      String[] files = file.list();
	      
	      for (int i = 0; i < files.length; i++){
	    	  
	    	
	    	  System.out.println(files[i]);
	      }
	    	  
	       
	      
	    } else {
	    	
	      FileReader fr = new FileReader(file);
	      
	      BufferedReader in = new BufferedReader(fr);
	      
	      String line;
	      while ((line = in.readLine()) != null)
	        System.out.println(line);
	    }
	}

}
