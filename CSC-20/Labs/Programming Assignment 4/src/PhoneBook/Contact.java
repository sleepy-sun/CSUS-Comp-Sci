package PhoneBook;

/**
 * The Contact class acts as a object that holds a persons information that can
 * be used within a phoneBook. The contact class has field values that are firstName,
 * lastName, homeNumber, officeNumber, and lastly emailAddress. All of these values
 * have getters and setters. Lastly there is a to string method that returns a string
 * containing all of the users information.
 * @author Javier Briseno
 *
 */
public class Contact {
	private String firstName;
    private String lastName;
    private long homeNumber;
    private long officeNumber;
    private String emailAddress;
    
    /**
     * Initializes a Contact object with the parameters being a person's first & last name, 
     * their home and office number, and lastly their email address. All of these objects 
     * field values will be set with these parameters
     * @param firstName
     * @param lastName
     * @param homeNumber
     * @param officeNumber
     * @param emailAddress
     */
    public Contact(String firstName, String lastName, long homeNumber, long officeNumber, String emailAddress){
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.homeNumber = homeNumber;
    	this.officeNumber = officeNumber;
    	this.emailAddress = emailAddress;
    }
    /*
    public Contact() {
    	this.firstName = "";
    	this.lastName = "";
    	this.homeNumber = 0;
    	this.officeNumber = 0;
    	this.emailAddress = "";
    }
*/
    
	/** Each get and set method are used to retrieve their respective object. Then will return 
	 * their respective object as well.
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(long homeNumber) {
		this.homeNumber = homeNumber;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * This toString method formats the printed array list
	 */
	public String toString() {
		return String.format( "%-15s %-15s %-15s %-15s %-15s %n", firstName, lastName, homeNumber, officeNumber, emailAddress );
	}
}
