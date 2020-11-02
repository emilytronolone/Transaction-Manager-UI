/**
This class is used specifically for money market accounts.
It is a subclass of Account and overrides the two abstract methods.
It contains one additional attribute - the number of withdrawals.
@author Devin Gulati, Emily Tronolone
*/
package application;

import java.text.DecimalFormat;

public class MoneyMarket extends Account {
    private int withdrawals;

    /**
     * Constructor for Checking, initializes values
     * @param holder: holder is passed to super
     * @param balance: balance is passed to super
     * @param dateOpen: dateOpen is passed to super
     */
    public MoneyMarket(Profile holder, double balance, Date dateOpen) {
        super(holder, balance, dateOpen);
        this.withdrawals = 0;
    }
    
	/**
	 * Calculates monthly interest
	 * @return monthly interest
	 */
	@Override
    public double monthlyInterest() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		if (this.getBalance() < 0) {
    		return 0;
    	}
    	double rate = (0.65/100)/12;
    	return Double.parseDouble(df.format(this.getBalance()*rate));
	}
    
	/**
     * Calculates monthly fee
     * @return monthly fee
     */
    @Override
	public double monthlyFee() {
		if ((this.getBalance() >= 2500) && (withdrawals<=6)) {
			return 0;
		}
		return 12;
	}
    
    /**
     * Overrides debit method in Account to keep track of withdrawal count
     * @param amount amount to decrease balance by
     */
    @Override
    public void debit(double amount) {
        this.withdrawals++;
        super.debit(amount);
    }

    /**
     * Overrides account toString method
     * @return string representation of account
     */
    @Override
    public String toString() {
    	super.toString();
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        if(this.withdrawals == 1)
            return "*Money Market*" + this.getHolder().getFname() + " " + this.getHolder().getLname() + "* " +
                    format.format(this.getBalance()) + "*" + this.getDateOpen() + "*1 withdrawal*";
        else{
            return "*Money Market*" + this.getHolder().getFname() + " " + this.getHolder().getLname() + "* " +
                    format.format(this.getBalance()) + "*" + this.getDateOpen() + "*" + this.withdrawals + " withdrawals*";
        }
    }
    
    /**
     * Setter method for withdrawals. Solely for testing purposes.
     * @param num: number of withdrawals to add to current amount of withdrawals
     */
    public void setWithdrawals(int num) {
    	this.withdrawals+=num;
    }
}