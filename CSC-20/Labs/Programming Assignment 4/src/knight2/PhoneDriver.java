/**
 * @author Brandon Sayareh
 * @version 5/14/2021
 */
package knight2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Main class to the program
 */
public class PhoneDriver {
	/**
	 * Main part of the program
	 * @param args for string
	 * @throws FileNotFoundException if there is no file to read
	 */
	public static void main(String[] args) throws FileNotFoundException{
		PhoneBook phoneBook = new PhoneBook();
			
		Scanner scan = new Scanner(new File("ContactDetails -1.txt"));
		
		String inline;
		String firstName;		String lastName;		long homeNumber;		long officeNumber;		String emailAddress;

		while(scan.hasNextLine()) {
			try {
				inline = scan.nextLine();
				String tokens[] = inline.split(",");
				firstName=tokens[0];		lastName=tokens[1];
				if(tokens[2].equals("-")) {
					homeNumber=0;
				}
				else {
					homeNumber=Long.parseLong(tokens[2]);
				}
				
				if(tokens[3].equals("-")) {
					officeNumber=0;
				}
				else {
					officeNumber=Long.parseLong(tokens[3]);
				}
				
				emailAddress=tokens[4];
				
				Contact newContact = new Contact(firstName, lastName, homeNumber, officeNumber, emailAddress);
				phoneBook.add(newContact);
			}catch(Exception E) {
				E.printStackTrace();
				System.out.println("Error: File cannot be read.");
			}
		}
		menu(phoneBook);
	}

	/**
	 * Write a static function to print Phone book using the toString method of the Phonebook class
	 * @param phoneBook
	 */
		private static void menu(PhoneBook phoneBook) {
			boolean quit=false;
			
			@SuppressWarnings("resource")
			Scanner input=new Scanner(System.in);
			
			while(quit==false) {
				System.out.println("P: Print Phonebook");					//print phone book
				System.out.println("B: Sort phone book by first name");		//use bubble sort
				System.out.println("L: Sort phone book by last name");		//use selection search
				System.out.println("S: Search contact by last name");		//use binary search
				System.out.println("A: Add contact to phone book");
				System.out.println("Q: Quit");
				
				String option = input.nextLine().toLowerCase();
				
				switch(option) {
					case"p":
						System.out.println(phoneBook.toString());
						break;
					case"b":
						phoneBook.bubbleSort();
						break;
					case"l":
						phoneBook.selectionSort();
						break;
					case "s":
	                    System.out.print("Enter the last name to be searched: ");
	                    search(input.nextLine(), phoneBook);
	                    break;
					case "a":
	                    add(phoneBook);
	                    break;
					case"q":
						System.out.println("Good Bye!");
						quit=true;
						return;
					default:
						System.out.println("Invalid input, try again.");	
				}
			}	
		}
		/**
		 * adding into phone Book
		 * @param phoneBook adds a person into the phonebook
		 */
		public static void add(PhoneBook phoneBook){
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
	        System.out.print("Enter first name: ");
	        String firstName = scan.nextLine();
	        System.out.print("Enter last name: ");
	        String lastName = scan.nextLine();
	        System.out.print("Enter Home Number: ");
	        
	        long homeNumber = scan.nextLong();
	        System.out.print("Enter Office Number: ");
	        long officeNumber = scan.nextLong();
	        System.out.print("Enter email address: ");
	        String emailAdress = scan.nextLine();
	        Contact newContact = new Contact(firstName,lastName,homeNumber,officeNumber,emailAdress);
	        phoneBook.add(newContact);
	    }
		/**
		 * Searching for if a contact is in phoneBook
		 * @param lastName searches for last name
		 * @param phoneBook searches phone book for the lastName
		 */
		public static void search(String lastName,PhoneBook phoneBook){
	        if(phoneBook.binarySearch(lastName)==phoneBook.binarySearch(lastName)){
	            System.out.println("Contact Found");
	            //System.out.println(phoneBook.contacts. + phoneBook.contacts.get(1).getLastName());
	        }else{
	            System.out.println("Contact Not found");
	        }
	    }
}
