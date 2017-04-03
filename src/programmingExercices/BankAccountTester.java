package programmingExercices;

/**
 * @author amadou
 *A class to test the BankAccount class
 */
public class BankAccountTester {
	
	/**
	 * 
	 * Test the methods of the BankAccount class
	 * @param args not used
	 * 
	 * */

	public static void main(String[] args) {
		
			BankAccount harrysCheicking= new BankAccount();
			harrysCheicking.deposit(2000);
			harrysCheicking.withdraw(500);
			System.out.println(harrysCheicking.getBalance());
			System.out.println("Expected:1500");
	}

}
