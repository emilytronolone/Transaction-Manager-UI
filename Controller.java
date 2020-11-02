package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
	
	@FXML
    private TextArea messageArea;

    @FXML
    private TextField firstName1;

    @FXML
    private TextField lastName1;

    @FXML
    private TextField month;

    @FXML
    private TextField day;

    @FXML
    private TextField year;

    @FXML
    private TextField balance;

    @FXML
    private RadioButton checking1;

    @FXML
    private ToggleGroup accountType1;

    @FXML
    private RadioButton savings1;

    @FXML
    private RadioButton moneyMarket1;

    @FXML
    private CheckBox directDeposit;

    @FXML
    private CheckBox loyalCustomer;

    @FXML
    private Button openAccountButton;

    @FXML
    private Button closeAccountButton;

    @FXML
    private TextField firstName2;

    @FXML
    private TextField lastName2;

    @FXML
    private TextField amount;

    @FXML
    private RadioButton checking2;

    @FXML
    private ToggleGroup accountType2;

    @FXML
    private RadioButton savings2;

    @FXML
    private RadioButton moneyMarket2;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    AccountDatabase database = new AccountDatabase();
    
    @FXML
    void closeAccount(ActionEvent event) {
    	if (firstName1.getText() == null || firstName1.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your first name.\n");
    		return;
    	}
    	
    	if (lastName1.getText() == null || lastName1.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your last name.\n");
    		return;
    	}
    	
    	int enteredMonth = validMonth();
    	int enteredDay = validDay();
    	int enteredYear = validYear();
    	
    	Date enteredDate = new Date(enteredMonth, enteredDay, enteredYear);
    	if (!enteredDate.isValid()) {
    		messageArea.appendText("Please enter a valid date.\n");
    		return;
    	}
    	
    	double currBalance = validBalance();
    	if (currBalance < 0) {
    		messageArea.appendText("Please enter a valid balance.\n");
    		return;
    	}
    	
    	Profile enteredHolder = new Profile(firstName1.getText(), lastName1.getText());
    	
    	if (checking1.isSelected()) {
    		Checking createChecking = new Checking(enteredHolder, currBalance, enteredDate, directDeposit.isSelected());
    		if (!database.remove(createChecking)) {
    			messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText("Account closed and removed from the database.\n");
			}
    		return;
    	} else if (savings1.isSelected()) {
    		Savings createSavings = new Savings(enteredHolder, currBalance, enteredDate, loyalCustomer.isSelected());
    		if (!database.remove(createSavings)) {
    			messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText("Account closed and removed from the database.\n");
			}
    		return;
    	} else if (moneyMarket1.isSelected()) {
    		MoneyMarket createMoneyMarket = new MoneyMarket(enteredHolder, currBalance, enteredDate);
    		if (!database.remove(createMoneyMarket)) {
    			messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText("Account closed and removed from the database.\n");
			}
    		return;
    	} else {
    		messageArea.appendText("Please select an account type.\n");
    	}
    }

    @FXML
    void disable(ActionEvent event) {
    	if (moneyMarket1.isSelected()) {	
    		loyalCustomer.setDisable(true);
    		directDeposit.setDisable(true);
    	}
    }

    @FXML
    void disableDirect(ActionEvent event) {
    	if (savings1.isSelected()) {	
    		loyalCustomer.setDisable(false);
    		directDeposit.setDisable(true);
    	}
    }

    @FXML
    void disableLoyal(ActionEvent event) {
    	if (checking1.isSelected()) {	
    		loyalCustomer.setDisable(true);
    		directDeposit.setDisable(false);
    	}
    }

    @FXML
    void openAccount(ActionEvent event) {
    	if (firstName1.getText() == null || firstName1.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your first name.\n");
    		return;
    	}
    	
    	if (lastName1.getText() == null || lastName1.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your last name.\n");
    		return;
    	}
    	
    	int enteredMonth = validMonth();
    	int enteredDay = validDay();
    	int enteredYear = validYear();
    	
    	Date enteredDate = new Date(enteredMonth, enteredDay, enteredYear);
    	if (!enteredDate.isValid()) {
    		messageArea.appendText("Please enter a valid date.\n");
    		return;
    	}
    	
    	double currBalance = validBalance();
    	if (currBalance < 0) {
    		messageArea.appendText("Please enter a valid balance.\n");
    		return;
    	}
    	
    	Profile enteredHolder = new Profile(firstName1.getText(), lastName1.getText());
    	
    	if (checking1.isSelected()) {
    		Checking createChecking = new Checking(enteredHolder, currBalance, enteredDate, directDeposit.isSelected());
    		if (!database.add(createChecking)) {
    			messageArea.appendText("Account is already in the database.\n");
			} else {
				messageArea.appendText("Account opened and added to the database.\n");
			}
    		return;
    	} else if (savings1.isSelected()) {
    		Savings createSavings = new Savings(enteredHolder, currBalance, enteredDate, loyalCustomer.isSelected());
    		if (!database.add(createSavings)) {
    			messageArea.appendText("Account is already in the database.\n");
			} else {
				messageArea.appendText("Account opened and added to the database.\n");
			}
    		return;
    	} else if (moneyMarket1.isSelected()) {
    		MoneyMarket createMoneyMarket = new MoneyMarket(enteredHolder, currBalance, enteredDate);
    		if (!database.add(createMoneyMarket)) {
    			messageArea.appendText("Account is already in the database.\n");
			} else {
				messageArea.appendText("Account opened and added to the database.\n");
			}
    		return;
    	} else {
    		messageArea.appendText("Please select an account type.\n");
    	}

    }

    @FXML
    double validBalance() {
    	try {
    		double doubleTest = Double.parseDouble(balance.getText()); 
    		return doubleTest;
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		//messageArea.appendText("Please enter a valid balance.\n");
    		return -1;
    	}
    }
    
    @FXML
    int validDay() {
    	try {
    		int dayTest = Integer.parseInt(day.getText()); 
    		return dayTest;
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		return -1;
    	}
    }

    @FXML
    int validMonth() {
    	try {
    		int monthTest = Integer.parseInt(month.getText()); 
    		return monthTest;
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		return -1;
    	}
    }

    @FXML
    int validYear() {
    	try {
    		int yearTest = Integer.parseInt(year.getText());
    		return yearTest;
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		return -1;
    	}
    }

    @FXML
    void deposit(ActionEvent event) {
    	if (database.getSize() == 0) {
    		messageArea.appendText("Database is empty.\n");
			return;
		}
    	
    	if (firstName2.getText() == null || firstName2.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your first name.\n");
    		return;
    	}
    	
    	if (lastName2.getText() == null || lastName2.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your last name.\n");
    		return;
    	}

    	double currAmount = validAmount();
    	if (currAmount <= 0) {
    		messageArea.appendText("Please enter a valid amount.\n");
    		return;
    	}
    	
    	Profile enteredHolder = new Profile(firstName2.getText(), lastName2.getText());
    	
    	if (checking2.isSelected()) {
    		Checking account = new Checking(enteredHolder, -1, null, false);
			if (!database.deposit(account, currAmount)) {
				messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText(currAmount + " deposited into account.\n");
			}
    		return;
    	} else if (savings2.isSelected()) {
    		Savings account = new Savings(enteredHolder, -1, null, false);
			if (!database.deposit(account, currAmount)) {
				messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText(currAmount + " deposited into account.\n");
			}
    		return;
    	} else if (moneyMarket2.isSelected()) {
    		MoneyMarket account = new MoneyMarket(enteredHolder, -1, null);
			if (!database.deposit(account, currAmount)) {
				messageArea.appendText("Account does not exist.\n");
			} else {
				messageArea.appendText(currAmount + " deposited into account.\n");
			}
    		return;
    	} else {
    		messageArea.appendText("Please select an account type.\n");
    	}
    }
    
    @FXML
    void withdraw(ActionEvent event) {
    	if (database.getSize() == 0) {
    		messageArea.appendText("Database is empty.\n");
			return;
		}
    	
    	if (firstName2.getText() == null || firstName2.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your first name.\n");
    		return;
    	}
    	
    	if (lastName2.getText() == null || lastName2.getText().trim().isEmpty()) {
    		messageArea.appendText("Please enter your last name.\n");
    		return;
    	}

    	double currAmount = validAmount();
    	if (currAmount <= 0) {
    		messageArea.appendText("Please enter a valid amount.\n");
    		return;
    	}
    	
    	Profile enteredHolder = new Profile(firstName2.getText(), lastName2.getText());
    	
    	if (checking2.isSelected()) {
    		Checking account = new Checking(enteredHolder, -1, null, false);
    		int withdrawal = database.withdrawal(account, currAmount);
			if (withdrawal == -1) {
				messageArea.appendText("Account does not exist.\n");
			} else if (withdrawal == 0) {
				messageArea.appendText(currAmount + " withdrawn from account.\n");
			} else {
				messageArea.appendText("Insufficient funds.\n");
			}
    		return;
    	} else if (savings2.isSelected()) {
    		Savings account = new Savings(enteredHolder, -1, null, false);
    		int withdrawal = database.withdrawal(account, currAmount);
			if (withdrawal == -1) {
				messageArea.appendText("Account does not exist.\n");
			} else if (withdrawal == 0) {
				messageArea.appendText(currAmount + " withdrawn from account.\n");
			} else {
				messageArea.appendText("Insufficient funds.\n");
			}
    		return;
    	} else if (moneyMarket2.isSelected()) {
    		MoneyMarket account = new MoneyMarket(enteredHolder, -1, null);
			int withdrawal = database.withdrawal(account, currAmount);
			if (withdrawal == -1) {
				messageArea.appendText("Account does not exist.\n");
			} else if (withdrawal == 0) {
				messageArea.appendText(currAmount + " withdrawn from account.\n");
			} else {
				messageArea.appendText("Insufficient funds.\n");
			}
    		return;
    	} else {
    		messageArea.appendText("Please select an account type.\n");
    	}
    }
    
    @FXML
    double validAmount() {
    	try {
    		double doubleTest = Double.parseDouble(amount.getText()); 
    		return doubleTest;
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		//messageArea.appendText("Please enter a valid amount.\n");
    		return -1;
    	}
    }
    
}
