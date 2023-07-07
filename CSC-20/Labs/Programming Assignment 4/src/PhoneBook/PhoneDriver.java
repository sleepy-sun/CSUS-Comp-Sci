package PhoneBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The PhoneDriver class is a driver for the PhoneBook class. This class utilizes the phoneBook
 * object and creates a menu for the user to interact with the Phonebook. The user has the options 
 * of printing the phonebook, sorting by firsgt name and last name, search the phonebook by last name,
 * add contacts to the phonebook, and to finally exit the phonebook. 
 * @author Javier Briseno
 *
 */
public class PhoneDriver {
	public static ArrayList<Contact> contacts;
	
	/**
	 * The main method of this program runs a while loop with a menu that is outputted to the user. The user 
	 * then can choose how they wan to interact with the phonebook. A switch statement is for choice, and
	 * other methods are called.
	 * @param args
	 */
	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		String User = null;
		PhoneBook phone = new PhoneBook();
	
		boolean run = true;
		
		while (run) {
			phone.displayMenu();
			User = input.nextLine().toUpperCase();
			
			switch (User) {
			
			case "P":
				print(phone);
				break;
			case "B":
				System.out.println("Sorting by First Name...");
				phone.bubbleSort();
				break;
			case "L":
				System.out.println("Sorting by Last Name...");
				phone.selectionSort();
				break;
			case "S":
				System.out.println("Last Name of the person you are searching for: ");
				search(input.nextLine(), phone);
                break;
			case "A":
				add(phone);
				break;
			case "Q":
				run = false;
				break;
			default:
				System.out.println("Not a valid option. Try typing one of the choices.");
				break;
			}
		}
		input.close();
	}
	
	/**
	 * The print method prints out the entire Phonebook's contacts
	 * @param phone
	 */
	public static void print(PhoneBook phone) {
		System.out.println(phone.toString());
	}
	
	/**
	 * The search method asks the user for a contact's last name and calls a method in the Phonebook class
	 * to search for a contact. If found it will say "Contact Found", if not it will print "Contact Not Found".
	 * @param lastName
	 * @param phone
	 */
	public static void search(String lastName, PhoneBook phone) {
		if(phone.binarySearch(lastName)==phone.binarySearch(lastName)){
            System.out.println("Contact Found");
        }else{
            System.out.println("Contact Not found");
        }
	}
	
	/**
	 * The add method will take in different inputs from the user by prompting different questions to get information
	 * about the Contact. It will then add the newContact into the arraylist ready to be printed when the user calls
	 * the print method.
	 * @param phone
	 */
	public static void add(PhoneBook phone) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter contact's first name: ");
        String firstName = input.next();
        System.out.println("Enter contact's last name.");
        String lastName = input.next();
        System.out.println("Enter contact's home number.");
        long homeNumber = input.nextLong();
        System.out.println("Enter contact's office number.");
        long officeNumber = input.nextLong();
        System.out.println("Enter contact's email address.");
        String emailAddress = input.next();
        System.out.println("Adding new contact - " + firstName + " " + lastName + " " + homeNumber + " " + officeNumber + " " + emailAddress + "\n");
        Contact newContact = new Contact(firstName, lastName, homeNumber, officeNumber, emailAddress);
        phone.add(newContact);
	}

}
