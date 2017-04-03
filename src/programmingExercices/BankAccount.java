package programmingExercices;

/**
 * @author amadou
 *
 */
public class BankAccount {

	private double balance;
	private String owner;

	public BankAccount() {

		balance = 0;
		
	}

	public BankAccount(double initialBalance) {
		super();

		balance = initialBalance;
		owner="None";
	}

	public void deposit(double amount) {
		balance = balance + amount;

	}

	public void withdraw(double amount) {
		
		balance= balance-amount;

	}

	public double getBalance() {
		return balance;
	}
	
	public void monthlyFee(){
		
		this.withdraw(10); //Withdraw $10 from this account
	}

}
