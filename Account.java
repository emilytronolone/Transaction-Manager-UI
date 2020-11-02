/**
This class is a superclass of Checking, Savings, and MoneyMarket.
It contains attributes and methods that all account types must have.
It contains two abstract methods that will be overridden and implemented in the subclasses.
@author Devin Gulati, Emily Tronolone
*/
package application;

public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;

	/**
	 * Constructor for Account class
	 * @param holder: passed for this.holder
	 * @param balance: passed for this.balance
	 * @param dateOpen: passed for this.dateOpen
	 */
	public Account(Profile holder, double balance, Date dateOpen){
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}

	/**
	 * Getter method for Holder
	 * @return holder
	 */
	public Profile getHolder() {
		return holder;
	}

	/**
	 * Getter method for dateOpen
	 * @return dateOpen
	 */
	public Date getDateOpen() {
		return dateOpen;
	}

	/**
	 * Decreases balance by amount
	 * @param amount: amount to decrease balance by
	 */
	public void debit(double amount) {
		this.balance-=amount;
	} //decrease the balance by amount

	/**
	 * Increases balance by amount
	 * @param amount: amount to increase balance by
	 */
	public void credit(double amount) {
		this.balance+=amount;
	} //increase the balance by amount

	/**
	 * Overrides toString method. Returns concatenation of First and Last name of Holder and balance
	 * @return String of first name, last name, and balance
	 */
	public String toString() {
		return holder.getFname() + ' ' + holder.getLname() + ' ' + this.balance;
	}

	/**
	 * Getter Method for balance
	 * @return double: account balance
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Comparator method for accounts
	 * @param account: account to be compared to
	 * @return true if equal, false otherwise
	 */
	public boolean accountEquals(Account account) {
		if ((this.holder.profileEquals(account.holder)) && (this.getClass().getName().equals(account.getClass().getName()))) {
			return true;
		}
		return false;
	}

	/**
	 * Abstract method that will be implemented in Account's subclasses. Calculates monthly interest.
	 * @return double
	 */
	public abstract double monthlyInterest();
	
	/**
	 * Abstract method that will be implemented in Account's subclasses. Calculates monthly fee.
	 * @return double
	 */
	public abstract double monthlyFee();
}