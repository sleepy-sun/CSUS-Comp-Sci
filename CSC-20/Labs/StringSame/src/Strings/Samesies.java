package Strings;

import java.util.Scanner;

/**
 * @author Javier Briseno
 * 
 * This Class will take in two strings inputed by the user and compare them to each other.
 * If they are the same the program will output "They are the same!" If not it will output
 * "Definitely not the same line!"
 * 
 */
public class Samesies {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        
        System.out.println("Please type first word or line: ");
        String test1 = input.nextLine();
        System.out.println("Please type second word or line: ");
        String test2 = input.nextLine();
        
        isSame(test1,test2);
        
        
        
        input.close();
	}

	/**
	 * This method will use the power of recursion to compare two strings
	 * in order to see if they are the same. 
	 * @param test1
	 * @param test2
	 */
	private static void isSame(String test1, String test2) {
		// TODO Auto-generated method stub
		if(test1.length()==0 && test2.length()==0) {
    		System.out.println("They are the same!");
    	}
    	else if(test1.length()== test2.length()) {
    		char letters1 = test1.charAt(0);
    		char letters2 = test2.charAt(0);
    		if(letters1==letters2) {
    			isSame(test1.substring(1), test2.substring(1));
    		} else {
        		System.out.println("Definitely not the same lines.");
        	}
    	} else {
    		System.out.println("Definitely not the same lines.");
    	}
	}

}
