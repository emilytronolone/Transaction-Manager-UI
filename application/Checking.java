package application;

/**
 * This class is used specifically for checking accounts.
 * It is a subclass of Account and overrides the two abstract methods.
 * It contains one additional attribute - directDeposit.
 * @author Devin Gulati, Emily Tronolone
 */
import java.text.DecimalFormat;

public class Checking extends Account {
    private boolean directDeposit;

    /**
     * Constructor for Checking, initializes values
     * @param holder: holder is passed to super
     * @param balance: balance is passed to super
     * @param dateOpen: dateOpen is passed to super
     * @param directDeposit: this.directDeposit is set equal to directDeposit
     */
    public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {
        super(holder, balance, dateOpen);
        this.directDeposit = directDeposit;
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
        double rate = (0.05/100)/12;
        return Double.parseDouble(df.format(this.getBalance()*rate));
    }

    /**
     * Calculates monthly fee
     * @return monthly fee
     */
    @Override
    public double monthlyFee() {
        if ((this.getBalance() >= 1500) || (!directDeposit)) {
            return 0;
        }
        return 25;
    }
    
    /**
     * Overrides account toString method
     * @return string representation of account
     */
    @Override
    public String toString() {
    	super.toString();
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();

        if(this.directDeposit) {
            return "*Checking*" + this.getHolder().getFname() + " " + this.getHolder().getLname() + "* " +
                    format.format(this.getBalance()) + "*" + this.getDateOpen() + "*direct deposit account*";
        }else{
            return "*Checking*" + this.getHolder().getFname() + " " + this.getHolder().getLname() + "* " +
                    format.format(this.getBalance()) + "*" + this.getDateOpen();
        }
    }
}