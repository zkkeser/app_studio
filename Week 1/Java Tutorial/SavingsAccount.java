public class SavingsAccount{
	
	//properties of the class
	private int balance;
	
	//constructor
	public SavingsAccount() {
		balance = 0;
	}
	public SavingsAccount(int initialBalance){
		initialBalance = balance;
		
	}
	
	public void greet(){
		System.out.println("Hello there! Welcome to Banksy's BankyBank!");
	}
	public int showBalance(){
		return balance;
	}
	
	public void deposit(int howMuch) {
		balance = balance + howMuch;
		if (balance < 0){
			System.out.println("Oi! You've got a negative balance, mate!");
		}
		System.out.println(balance);
	}
	
	public void withdraw(int howMuch) {
		balance = balance - howMuch;
		if (balance < 0){
			System.out.println("Oi! You've got a negative balance, mate!");
		}
		System.out.println(balance);
	}
	
}
