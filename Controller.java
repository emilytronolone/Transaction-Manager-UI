/**
 @author Devin Gulati, Emily Tronolone
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Controller {

    @FXML
    private RadioButton lnButton;

    @FXML
    private RadioButton doButton;

    @FXML
    private RadioButton nsButton;

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
    /**
     * Closes selected account
     */
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
    /**
     * Sets variables for money market account
     */
    void disable(ActionEvent event) {
        if (moneyMarket1.isSelected()) {
            loyalCustomer.setDisable(true);
            directDeposit.setDisable(true);
        }
    }

    @FXML
    /**
     * sets variables for savings account
     */
    void disableDirect(ActionEvent event) {
        if (savings1.isSelected()) {
            loyalCustomer.setDisable(false);
            directDeposit.setDisable(true);
        }
    }

    @FXML
    /**
     * sets variables for checking account
     */
    void disableLoyal(ActionEvent event) {
        if (checking1.isSelected()) {
            loyalCustomer.setDisable(true);
            directDeposit.setDisable(false);
        }
    }

    @FXML
    /**
     * opens account with entered information
     */
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
    /**
     * checks if balance is valid when entered
     */
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
    /**
     * checks if entered day is valid
     */
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
    /**
     * checks if entered month is valid
     */
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
    /**
     * checks if entered year is valid
     */
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
    /**
     * deposits entered amount into the entered account
     */
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
    /**
     * withdraws entered amount from entered account
     */
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
    /**
     * checks if entered amount is valid
     */
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

    @FXML
    /**
     * ensures only one type of sort is selected
     */
    void sortSelector(ActionEvent event){
        RadioButton[] buttons = {lnButton, doButton, nsButton};
        for (RadioButton button : buttons) {
            if(!((RadioButton)event.getSource()).getId().equals(button.getId())){
                button.setSelected(false);
            }
        }
    }

    @FXML
    /**
     * prints database to textarea using selected sort method, defaults to unsorted database
     */
    void displayTab(ActionEvent event){
        RadioButton selection = nsButton;
        RadioButton[] buttons = {lnButton, doButton, nsButton};
        for (RadioButton button : buttons) {
            if(button.isSelected()){
                selection = button;
            }
        }
        switch (selection.getId()){
            case "lnButton":{
                messageArea.appendText(database.printByLastName() + "\n");
                break;
            }
            case "doButton":{
                messageArea.appendText(database.printByDateOpen() + "\n");
                break;
            }
            case "nsButton":
            default: {
                messageArea.appendText(database.printAccounts() + "\n");
                break;
            }
        }
    }

    @FXML
    /**
     * handles import and export buttons, reads database from file, exports database to entered file
     */
    void porting(ActionEvent event){
        if(((Button)event.getSource()).getId().equals("Import")){
            FileChooser fileChooser = new FileChooser();
            File importFile = fileChooser.showOpenDialog(new Stage());
            if(!importFile.isFile()){
                messageArea.appendText("Please select a text file\n");
            }
            Scanner scanner;
            try {
                 scanner = new Scanner(importFile);
            } catch (FileNotFoundException e) {
                messageArea.appendText("File not found");
                return;
            }
            if(scanner == null){
                messageArea.appendText("File not found");
                return;
            }
            String command = scanner.nextLine();
            DecimalFormat df = new DecimalFormat("#.00");
            while (scanner.hasNext()) { // reads from the scanner until the Quit command is read
                String[] tokens = command.split(","); // splits each line of input into an array of Strings split by " "
                // System.out.println("command: " + tokens[1]);
                switch (tokens[0]) { // first element of this array should always be the command
                    case "S":{

                        String[] date_tokens = tokens[4].split("/");
                        Date date = new Date(Integer.parseInt(date_tokens[0]), Integer.parseInt(date_tokens[1]),
                                Integer.parseInt(date_tokens[2]));

                        boolean isLoyal;
                        if (tokens[5].toLowerCase().equals("true")) {
                            isLoyal = true;
                        } else if (tokens[5].toLowerCase().equals("false")) {
                            isLoyal = false;
                        } else {
                            //System.out.println("Input data type mismatch.");
                            break;
                        }

                        Savings s = new Savings(new Profile(tokens[1], tokens[2]), Double.parseDouble(tokens[3]), date, isLoyal);
                        database.add(s);

                        break;
                    }

                    case "C":{

                        String[] date_tokens = tokens[4].split("/");
                        Date date = new Date(Integer.parseInt(date_tokens[0]), Integer.parseInt(date_tokens[1]),
                                Integer.parseInt(date_tokens[2]));

                        boolean isLoyal;
                        if (tokens[5].toLowerCase().equals("true")) {
                            isLoyal = true;
                        } else if (tokens[5].toLowerCase().equals("false")) {
                            isLoyal = false;
                        } else {
                            //System.out.println("Input data type mismatch.");
                            break;
                        }

                        Checking c = new Checking(new Profile(tokens[1], tokens[2]), Double.parseDouble(tokens[3]), date, isLoyal);
                        database.add(c);

                        break;
                    }

                    case "M":{
                        String[] date_tokens = tokens[4].split("/");
                        Date date = new Date(Integer.parseInt(date_tokens[0]), Integer.parseInt(date_tokens[1]),
                                Integer.parseInt(date_tokens[2]));

                        MoneyMarket s = new MoneyMarket(new Profile(tokens[1], tokens[2]), Double.parseDouble(tokens[3]), date);
                        database.add(s);

                        break;
                    }


                    default: {
                        messageArea.appendText("Command '" + tokens[1] + "' isn't supported.\n");
                        break;
                    }

                }
                command = scanner.nextLine();
            }
            messageArea.appendText("Transaction processing completed.\n");
            scanner.close();

        }else{
            String filename = messageArea.getText();
            filename = filename.split("\n")[filename.split("\n").length-1];
            String retval = database.printAccounts();
            try{
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.write(retval);
                fileWriter.close();
                messageArea.appendText("\nFinished writing to file!\n");
            }catch (Exception e){
                messageArea.appendText("Please enter a valid filename: \n");
            }

        }
    }

}
