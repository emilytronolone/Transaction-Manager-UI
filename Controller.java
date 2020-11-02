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

    @FXML // fx:id="messageArea"
    private TextArea messageArea; // Value injected by FXMLLoader

    @FXML // fx:id="firstName1"
    private TextField firstName1; // Value injected by FXMLLoader

    @FXML // fx:id="lastName1"
    private TextField lastName1; // Value injected by FXMLLoader

    @FXML // fx:id="month"
    private TextField month; // Value injected by FXMLLoader

    @FXML // fx:id="day"
    private TextField day; // Value injected by FXMLLoader

    @FXML // fx:id="year"
    private TextField year; // Value injected by FXMLLoader

    @FXML // fx:id="balance"
    private TextField balance; // Value injected by FXMLLoader

    @FXML // fx:id="checking1"
    private RadioButton checking1; // Value injected by FXMLLoader

    @FXML // fx:id="accountType1"
    private ToggleGroup accountType1; // Value injected by FXMLLoader

    @FXML // fx:id="savings1"
    private RadioButton savings1; // Value injected by FXMLLoader

    @FXML // fx:id="moneyMarket1"
    private RadioButton moneyMarket1; // Value injected by FXMLLoader

    @FXML // fx:id="directDeposit"
    private CheckBox directDeposit; // Value injected by FXMLLoader

    @FXML // fx:id="loyalCustomer"
    private CheckBox loyalCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="openAccountButton"
    private Button openAccountButton; // Value injected by FXMLLoader

    @FXML // fx:id="closeAccountButton"
    private Button closeAccountButton; // Value injected by FXMLLoader

    @FXML // fx:id="checking2"
    private RadioButton checking2; // Value injected by FXMLLoader

    @FXML // fx:id="accountType2"
    private ToggleGroup accountType2; // Value injected by FXMLLoader

    @FXML // fx:id="savings2"
    private RadioButton savings2; // Value injected by FXMLLoader

    @FXML // fx:id="moneyMarket2"
    private RadioButton moneyMarket2; // Value injected by FXMLLoader

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
    	if (currBalance == -1) {
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
    	if (currBalance == -1) {
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
    		messageArea.appendText("Please enter a valid balance.\n");
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

}
