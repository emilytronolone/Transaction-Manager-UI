/**
This class creates Profile objects.
Profile objects represent a human person.
In this banking system, Profile is used to create owners of accounts.
@author Devin Gulati, Emily Tronolone
*/
package application;

public class Profile {
	private String fname;
	private String lname;

	/**
	 * Constructor for profile, initializes fname and lname
	 * @param fname: this.fname set equal to fname
	 * @param lname: this.lname set equal to lname
	 */
	public Profile(String fname, String lname){
		this.fname = fname;
		this.lname = lname;
	}
	
	/**
	 * Getter method for fname
	 * @return fname
	 */
	public String getFname() {
		return this.fname;
	}
	
	/**
	 * Getter method for lname
	 * @return lname
	 */
	public String getLname() {
		return this.lname;
	}

	/**
	 * Comparator for profiles
	 * @param profile: profile to be compared to
	 * @return true if equal, false otherwise
	 */
	public boolean profileEquals(Profile profile) {
		if ((this.fname.equals(profile.fname)) && (this.lname.equals(profile.lname))) {
			return true;
		}
		return false;
	}
}