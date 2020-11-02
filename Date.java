/**
This class is used to create Date objects containing a month, day, and year.
It implements the Java interface Comparable.
It overrides two methods.
@author Devin Gulati, Emily Tronolone
*/
package application;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor for Date, passes initial values
     * @param year: year is set equal to this.year
     * @param month: month is set equal to this.month
     * @param day: day is set equal to this.day
     */
    public Date(int month, int day, int year){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Getter method for year
     * @return year
     */
    public int getYear(){
        return this.year;
    }
    
    /**
     * Getter method for day
     * @return day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Getter method for month
     * @return month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Overrides comparator method. Compares two dates.
     * @param date: date to be compared to
     * @return 1 if this is later, -1 if this is earlier, 0 if equal
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.getYear()){
            return 1;
        }else if (date.getYear() > this.year){
            return -1;
        }

        if (this.month > date.getMonth()){
            return 1;
        }else if (this.month < date.getMonth()){
            return -1;
        }

        if (this.day > date.getDay()){
            return 1;
        }else if (this.day < date.getDay()){
            return -1;
        }

        return 0;
    } //return 0, 1, or -1

    /**
     * Overrides toString method. Outputs formatted date.
     * @return "month/day/year"
     */
    @Override
    public String toString() {
    	return this.month + "/" + this.day + "/" + this.year;
    } //in the format mm/dd/yyyy

    /**
     * Checks if date is valid mm/dd/yyyy.
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        if (this.month > 12 || this.month < 1){
            return false;
        }
        int[] dates = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (this.year % 400 == 0) {
        	dates[1] = 29;
        }
        if (this.year % 4 == 0){
        	dates[1] = 29;
        }
        

        if (this.day > dates[this.month-1] || this.day < 1){
            return false;
        }
        if ((this.year+"").length() != 4){
            return false;
        }

        return true;
    }
}