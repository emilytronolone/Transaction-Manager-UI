/**
 This class is used to store all accounts created in this banking system.
 The methods implement various functionalities of the database.
 It also allows for an account to deposit and withdraw.
 @author Devin Gulati, Emily Tronolone
 */
package application;

import java.text.DecimalFormat;

public class AccountDatabase {
    private Account[] accounts;
    private int size;

    /**
     * Constructor for AccountDatabase, initializes array and sets size to 0
     */
    public AccountDatabase() {
        this.accounts = new Account[5];
        this.size = 0;
    }

    /**
     * Finds account in database
     * @param account: account to be found
     * @return index found, -1 otherwise
     */
    private int find(Account account) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].accountEquals(account)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases account array by 5
     */
    private void grow() {
        Account[] newDatabase = new Account[this.accounts.length + 5];
        int i = 0;
        for (Account acc : this.accounts) {
            newDatabase[i] = acc;
            i++;
        }
        this.accounts = newDatabase;
    }

    /**
     * Adds account to database
     * @param account: account to be added
     * @return true on success, false on failure
     */
    public boolean add(Account account) {
        if (this.find(account) > (-1)) {
            return false;
        }
        this.accounts[this.size] = account;
        this.size++;
        if (this.size >= this.accounts.length) {
            grow();
        }
        return true;
    }

    /**
     * Removes account from database
     * @param account: account to be removed
     * @return true on success, false on failure
     */
    public boolean remove(Account account) {
        int i = find(account);
        if (i > -1) {
            this.accounts[i] = this.accounts[this.size-1];
            this.accounts[accounts.length - 1] = null;
            this.size--;
            return true;
        }
        return false;
    }

    /**
     * Adds amount to account balance
     * @param account: account for funds to be deposited
     * @param amount: amount to be deposited
     * @return true on success, false on failure
     */
    public boolean deposit(Account account, double amount) {
        int i = find(account);
        if (i > -1) {
            this.accounts[i].credit(amount);
            return true;
        }
        return false;
    }

    /**
     * Withdraws funds from account
     * @param account: account to be withdrawn from
     * @param amount: amount to be withdrawn
     * @return 0: withdrawal successful, 1: insufficient funds, -1: account doesn’t exist
     */
    public int withdrawal(Account account, double amount) {
        int i = find(account);
        if (i == -1) {
            return -1;
        }
        if (accounts[i].getBalance() - amount >= 0) {
            accounts[i].debit(amount);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Sorts database by date account opened in ascending order
     */
    private void sortByDateOpen() {
        for(int i = 1; i < size; i++){
            Account temp = this.accounts[i];
            int j = i - 1;
            while (j >= 0 && this.accounts[j].getDateOpen().compareTo(temp.getDateOpen()) == 1){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }
            this.accounts[j+1] = temp;
        }

    }

    /**
     * Sorts accounts by last name of holder in ascending order
     */
    private void sortByLastName() {
        for(int i = 1; i < size; i++){
            Account temp = this.accounts[i];
            int j = i -1;
            while(j >= 0 && this.accounts[j].getHolder().getLname().toUpperCase().compareTo(temp.getHolder().getLname().toUpperCase()) > 0){
                this.accounts[j+1] = this.accounts[j];
                j--;
            }
            this.accounts[j+1] = temp;
        }
    }

    /**
     * Sorts by date open and concats into string
     * @return sorted database in string
     */
    public String printByDateOpen() {
    	if (this.size == 0) {
            return "Database is empty.";
        }
    	this.sortByDateOpen();
        String retval = "";
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        retval += "--Printing statements by last name--\n";
        for(int i = 0; i < size; i++){
            retval += accounts[i].toString() + "\n";
            retval += "-interest: " + accounts[i].monthlyInterest() + "\n";
            retval += "-fee: " + accounts[i].monthlyFee() + "\n";
            retval += "-new balance: " +
                    format.format(accounts[i].getBalance() + (accounts[i].monthlyInterest()) -
                            accounts[i].monthlyFee()) + "\n";
        }
        return retval;
    }

    /**
     * sorts by last name and concats into string
     * @return string representation of database
     */
    public String printByLastName() {
    	if (this.size == 0) {
            return "Database is empty.";
        }
    	this.sortByLastName();
    	String retval = "";
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        retval += "--Printing statements by last name--\n";
        for(int i = 0; i < size; i++){
            retval += accounts[i].toString() + "\n";
            retval += "-interest: " + accounts[i].monthlyInterest() + "\n";
            retval += "-fee: " + accounts[i].monthlyFee() + "\n";
            retval += "-new balance: " +
                    format.format(accounts[i].getBalance() + (accounts[i].monthlyInterest()) -
                            accounts[i].monthlyFee()) + "\n";
        }
        return retval;
    }

    /**
     * concats database into string
     * @return string form of database
     */
    public String printAccounts() {
    	if (this.size == 0) {
            return "Database is empty.";
        }
    	String retval = "--Listing all accounts in database--";
    	retval += "\n";
        for(int i = 0; i < size; i++){
            retval += accounts[i].toString();
            retval += "\n";
        }
        retval += "--end of listing--";
        return retval;
    }


    /**
     * Getter method for size
     * @return size
     */
    public int getSize() {
        return this.size;
    }
}