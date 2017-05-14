package programmingExercices;

public class Echo {
	// get the System console object
	public static void main(String[] args) {
		
		java.io.Console console = System.console();
		
		if(console==null){
			
			System.err.println(" Java Hello you!");
			System.exit(-1);
		}
		
		console.printf(console.readLine());

	}
		
	}
			

