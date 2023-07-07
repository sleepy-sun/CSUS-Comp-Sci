package knight;
/**
 * @author Brandon Sayareh
 * @version 5/14/2021
 */

/**
 * Used to create individual contacts
 */
public class Contact {
	//variables
		private String firstName;
		private String lastName;
		private long homeNumber;
		private long officeNumber;
		private String emailAddress;
	/**
	 * Constructor
	 * @param firstName stores current first name
	 * @param lastName stores current last name
	 * @param homeNumber stores current home number
	 * @param officeNumber stores office number
	 * @param emailAddress stores email address
	 */
		public Contact(String firstName, String lastName, long homeNumber, long officeNumber, String emailAddress) {
			this.firstName=firstName;				this.lastName=lastName;
			this.homeNumber=homeNumber;				this.officeNumber=officeNumber;
			this.emailAddress=emailAddress;
		}
//-----------------------------------GETTERS-----------------------------------
	/**
	 * Getter for first name
	 * @return first name
	 */
		public String getFirstName() {
			return this.firstName;
		}
	/**
	 * Getter for last name
	 * @return last name
	 */
		public String getLastName() {
			return this.lastName;
		}	
	/**
	 * Getter for home number
	 * @return home number
	 */
		public long getHomeNumber() {
			return this.homeNumber;
		}
	/**
	 * Getter for for office number
	 * @return office number
	 */
		public long getOfficeNumber() {
			return this.officeNumber;
		}
	/**
	 * Getter for email address
	 * @return email address
	 */
		public String getEmailAddress() {
			return this.emailAddress;
		}
//-----------------------------------SETTERS-----------------------------------
	/**
	 * Setter for first name
	 * @param firstName sets first name
	 */
		public void setFirstName(String firstName) {
			this.firstName=firstName;
		}
	/**
	 * Setter for last name
	 * @param lastName sets last name
	 */
		public void setLastName(String lastName) {
			this.firstName=lastName;
		}
	/**
	 * Setter for home number
	 * @param homeNumber sets home number
	 */
		public void setHomeNumber(long homeNumber) {
			this.homeNumber=homeNumber;
		}
	/**
	 * Setter for office number
	 * @param officeNumber sets office number
	 */
		public void setOfficeNumber(long officeNumber) {
			this.officeNumber=officeNumber;
		}	
	/**
	 * Setter for email address
	 * @param emailAddress sete email address
	 */
		public void setEmailAddress(String emailAddress) {
			this.emailAddress=emailAddress;
		}		
	/**
	 * toString method that makes output a string
	 * @return contact as a string
	 */
		public String toString() {
	        StringBuilder string = new StringBuilder();
	        if(this.firstName.length()>=8) {
	         string.append(this.getFirstName()+"\t"+this.getLastName()+"\t\t"+this.getHomeNumber()+"\t"
	        +String.valueOf(this.getOfficeNumber())+"\t"+this.getEmailAddress());

	         string.append("\n");
	        } else if (this.lastName.length()>=8) {
	            string.append(this.getFirstName()+"\t\t"+this.getLastName()+"\t");
	            if (this.homeNumber==0) {
	                string.append("-"+"\t\t"
	                        +String.valueOf(this.getOfficeNumber())+"\t"+this.getEmailAddress());
	            } else {
	                string.append(this.getHomeNumber()+"\t"
	                        +String.valueOf(this.getOfficeNumber())+"\t"+this.getEmailAddress());
	            }

	                     string.append("\n");
	        } else if(this.homeNumber==0) {
	            string.append(this.getFirstName()+"\t\t"+this.getLastName()+"\t\t"+"-"+"\t\t"
	                    +String.valueOf(this.getOfficeNumber())+"\t"+this.getEmailAddress());

	                     string.append("\n");
	        } else {
	            string.append(this.getFirstName()+"\t\t"+this.getLastName()+"\t\t"+this.getHomeNumber()+"\t");
	            if (this.officeNumber==0) {
	                string.append("-"+"\t\t"+this.getEmailAddress());
	            } else {
	                string.append(String.valueOf(this.getOfficeNumber())+"\t"+this.getEmailAddress());
	            }

	                     string.append("\n");
	        }

	         return string.toString();
	    }
	
}