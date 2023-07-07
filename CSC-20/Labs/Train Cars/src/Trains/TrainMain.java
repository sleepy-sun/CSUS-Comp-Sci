package Trains;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Javier Briseno
 * Description: This main method creates a menu for a user to interact
 * 				with a train that holds many different cars and data within them
 * 				These cars represent a linked list. The user will have multiple
 * 				options in this class. The main class will have options that
 * 				range from the ability to attach and detach a car, to display all the cars
 * 				search for a car, merge train cars, and to quit the program altogether.
 * 				All of these different options are methods in the Train class to help the
 * 				user manage the train and its data.
 *
 */
public class TrainMain {

	public static void main(String[] args) throws FileNotFoundException {
		String carFile = "car.txt";
		String updateFile = "update.txt";
		Train train = new Train(carFile);
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		String User = null;
		
		do {
			train.displayMenu();
			User = input.next();
			if(User.equalsIgnoreCase("A")){
	            System.out.println("Enter a factory name: ");
	            String factory = input.next();
	            System.out.println("Enter the stop number");
	            int stop = input.nextInt();
	            System.out.println("Enter the material being transported");
	            String material = input.next();
	            System.out.println("Adding a new car - " + factory + " " + stop + " " + material);
	            train.attach(factory, stop, material);
			} else if (User.equalsIgnoreCase("R")) {
	        	System.out.println("What is the name of the factory you want to detach?");
	            String factory = input2.nextLine();
	            train.detach(factory);
	            System.out.println("The car has now been detached");
	        } else if (User.equalsIgnoreCase("D")) {
	        	System.out.println();
	        	train.displayTrainCars();
	        	System.out.println();
	        } else if (User.equalsIgnoreCase("S")) {
	        	System.out.println("What is the factory train car name you are looking for?");
	        	String factory = input2.nextLine();
	        	train.search(factory);
	        } else if (User.equalsIgnoreCase("M")) {
	        	System.out.println("Showing current cars: ");
	        	train.displayTrainCars();
	        	train.merge(updateFile);
	        	System.out.println("\nDisplaying new cars:");
	        	train.displayTrainCars();
	        } else if (User.equalsIgnoreCase("Q")) {
	        	System.out.println("Have a nice day!");
	        } else {
	        	System.out.println("Not a valid option. Try typing one of the choices.");
	        }
			
		} while (!User.equalsIgnoreCase("Q"));
		
		input.close();
		input2.close();
	}


}
