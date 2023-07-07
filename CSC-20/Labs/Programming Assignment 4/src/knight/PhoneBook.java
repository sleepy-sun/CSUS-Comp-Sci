package knight;
/**
 * @author Brandon Sayareh
 * @version 5/14/2021
 */

import java.util.ArrayList;
/**
 * phoneBook class that create a phone book with all the contacts
 */
public class PhoneBook {
	/**
	 * array for contacts
	 */
		public ArrayList<Contact> contacts;
	/**
	 *  create arraylist of contacts by reading data from the file            
	 */
	    public PhoneBook(){
	    	this.contacts = new ArrayList<Contact>();    
	    }
	/**
	 * add contact to the array list
	 * @param c adds this contact to the array
	 */
	    public void add(Contact c){
	    	this.contacts.add(c);
	    }
	/**
	 * returns the entire phone book as string
	 */
	    public String toString() {
	        String phoneBook = "FirstName\tLastName\tHomeNumber\tOfficeNumber\tEmailAddress\n";
	        for (int i=0 ; i<contacts.size(); i++) {
	            phoneBook += contacts.get(i).toString();
	        }
	        return phoneBook;
	    }
	/**
	 * Sort phonebook by first name 
	 */
	   public void bubbleSort(){
		   //getting size
		   		int size=this.contacts.size();
		   //sorting
		   		for(int x=0; x<size-1; x++) {
		   			for(int y=0; y<size-x-1; y++) {
		   				//swapping 
		   					if(this.contacts.get(y).getFirstName().compareTo(this.contacts.get(y+1).getFirstName())>0) {
		   						Contact temp = this.contacts.get(y);
		   						this.contacts.set(y, this.contacts.get(y+1));
		   						this.contacts.set(y+1, temp);
		   					}
		   			}
		   		}      
	   }
	/**
	 * Sort phone book by last using selection sort algorithm. 
	 */
	   public void selectionSort(){
		   int size = this.contacts.size();
		   
		   for(int x=0; x<size; x++) {
			   int min=x;
			   for(int y=x+1; y<size; y++) {
				   if(this.contacts.get(y).getLastName().compareTo(this.contacts.get(min).getLastName())<0) {
					   min=y;
				   }
				   //swapping
				   		Contact temp = this.contacts.get(min);
				   		this.contacts.set(min, this.contacts.get(y));
				   		this.contacts.set(x, temp);
			   }
		   }
	   }
	/**
	 * Search contact by last name using binary search. Returns true if contact is found else false.
	 * @param name take name and searches for it
	 * @return true if contact is found else false
	 */
	   public boolean binarySearch(String name){
		   this.selectionSort();
		   int min=0;
		   int max=this.contacts.size();
		   
		   while(min<max) {
			   int mid = (min+max)/2;
			   int compare = this.contacts.get(mid).getLastName().compareTo(name);
			   
			   if(compare==0) {
				   return true;
			   }
			   else if(compare<0) {
				   min=mid+1;
			   }
			   else{//compare>0
				   max=min-1;
			   }
		   }
		   //if element DNE
		   	return false;
	   }
	   
}