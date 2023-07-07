package PhoneBook;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PhoneBook class is a class that creates PhoneBook objects, This class has a default and regular constructor.
 * The regular constructor uses a file to fill up the contacts field value is contacts listed in the file. This class
 * has the ability to search the contacts with a binary search. A user can also use this classes driver to add more contacts to
 * the list. This class can also be sorted by either first name or last name. Lastly, this class has a toString method that
 * provides a nicely formatted list of all of the contacts in the phone book
 * @author Javier Briseno
 *
 */
public class PhoneBook {
	public static ArrayList<Contact> contacts = new ArrayList<>();
	public static File file = new File("ContactDetails -1.txt");
	static Scanner scan;
    
    /**
     *  PhoneBook will create arraylist of contacts by reading data from the file. It will assign each object a 
     *  different token. If the file contains - it will replace it with a 0.
     */
    public PhoneBook() {
		String line;
		String firstName;
		String lastName;
		String emailAddress;
		long homeNumber;
		long officeNumber;
		
		try {
			 scan = new Scanner(file);
		} catch (Exception e) {
			System.out.println("Error opening file!");
		}
		
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			String tokens[] = line.split(",");
			
			firstName = tokens[0];		
			lastName = tokens[1];
			
			if(tokens[2].equals("-")) {
				homeNumber = 0;
			} else {
				homeNumber = Long.parseLong(tokens[2]);
			} 
			
			if(tokens[3].equals("-")) {
				officeNumber = 0;
			} else {
				officeNumber = Long.parseLong(tokens[3]);
			}
			emailAddress = tokens[4];
			
			Contact newContact = new Contact(firstName, lastName, homeNumber, officeNumber, emailAddress);
			contacts.add(newContact);
		}

    }
    
    
    /**
     * The add method adds a contacts given by a parameter to the contacts arraylist.
     * @param c
     */
    public void add(Contact c){
		// add contact to the array list.
    	contacts.add(c);
    	
    
    }
    
    
    /**
     * The toString method uses a for loop to iterate the entire contacts arraylist and toString
     * every item in the list and add it to a string. The string is then returned.
     */
    public String toString(){
    	String phonebook = "FirstName\tLastName\tHomeNumber\tOfficeNumber\tEmailAddress\n";
        for (int i = 0 ; i < contacts.size(); i++) {
            phonebook += contacts.get(i).toString();
        }
        return phonebook;
    }

   /**
    * The bubblesort method sorts the contacts by first name.
 	*/
    public void bubbleSort(){
             //Sort phonebook by first name 
	   for(int i = 0; i < contacts.size(); i++) {
		   for(int j = 0; j < contacts.size()- i - 1; j++) {
			   if(contacts.get(j).getFirstName().compareTo(contacts.get(j+1).getFirstName()) > 0) {  //If a name that is further down the list is out of place,
				   //add code here for bubblesort  												   // it will be switched to be in the right place
				   Contact temp = contacts.get(j);
                   contacts.set(j,contacts.get(j+1));
                   contacts.set(j+1, temp); 
			   }
		   }
	   }
   }
  
 /**
 * The selectionsort method sorts the contacts by last name.
 */
public void selectionSort(){
//Sort phone book by last name using selection sort algorithm.  
        int n = contacts.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (contacts.get(j).getLastName().compareTo(contacts.get(min).getLastName()) < 0) {
                    min = j;
                }
        }
 	}
 	
 /**
  * The binarysearch method takes a string last name as a parameter. This method then uses a sorted contacts array
  * to search and narrow down the last by half until it finds the last name of the person. If not found, then
  * false is returned.
 * @param lastName
 * @return
 */
public boolean binarySearch(String lastName){
     // Search contact by last name using binary search. 
     // return true if contact is found else false
	if (PhoneBook.contacts.size() >= 0) {
		if (PhoneBook.contacts.size() > 20) {
			this.selectionSort();
		}
		int foundIndex, min = 0, max = contacts.size() - 1;
		
		while (min <= max) {
			int middle = (min + max) / 2, current = lastName.compareToIgnoreCase(String.valueOf(contacts.get(middle).getLastName()));
		
			if (current == 0) {
				System.out.println(contacts.get(middle).toString());
				return true;
			} else if (current < 0) { //too high
				max = middle - 1;
			} else { //too low
				min = middle + 1;
			}
		}
	}
	 return false;
 }
 
 /**
 * The displayMenu method just prints out the menu the user will use to interact with the program.
 */
public void displayMenu () {
	 	System.out.println("----------------------------------------------");
		System.out.println("P: Print Phonebook");
		System.out.println("B: Sort phone book by first name");
		System.out.println("L: Sort phone book by last name");
		System.out.println("S: Search contact by last name ");
		System.out.println("A: Add contact to phone book");
		System.out.println("Q: Quit");
		System.out.println("----------------------------------------------");
		System.out.println("Please select one of the options above: ");
	}

}
