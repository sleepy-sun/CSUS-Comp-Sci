package Trains;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Javier Briseno
 * 
 * The Train class uses the Car class to work and manage the train linked list. This class
 * has the ability to attach and detach train car objects from the Car class using methods.
 * Also, the user can use this class to display the current train and all the values stored
 * in the cars data. Likewise the user cab use a method in this class to search for a particular
 * factory name, and the factory's materials will be displayed if found. A user can also request
 * to get a list of all the train cars based on their stops. Lastly, the merge method takes a file 
 * with additional factories to be added to the Train object. The train constructor reads from a file 
 * to create the train with its original cars.
 *
 */
public class Train {
	public Car front;
	
	/**
	 * Initializes a Train object that sets the beginning of the Train null.
	 */
	public Train() {
		this.front = null;
	}
	
	/**
	 * constructor: reads each train car details from the file into the LinkedList
	 * @param carFile
	 * @throws FileNotFoundException
	 */
	public Train(String carFile) throws FileNotFoundException {
		Scanner car = new Scanner(new File("car.txt"));
		String[] info;
		String line;
		
		while(car.hasNextLine()) {
			line = car.nextLine();
			info = line.split(",");
			
			attach(info[0], Integer.parseInt(info[1]), info[2]);
		}
	}
	
	/**
	 * The detach method takes a String factoryName that the user will input, and then the
	 * Train is searched one item at a time until the Car with the right Factory name is found.
	 * Once it is found, the car is then deleted, then the method is called again to find 
	 * anymore cars with the same name.
	 * @param factoryName
	 */
	public void detach(String factoryName) {
			Car current = front;
			Car previous = null;
			
			 if (current != null && current.factory.equalsIgnoreCase(factoryName)) {
				 front = front.next;
				 return;
			 }
			 while (current != null && !current.factory.equalsIgnoreCase(factoryName)) {
				 previous = current;
				 current = current.next;
			 }
			 if (current != null && current.factory.equalsIgnoreCase(factoryName)) {
				 previous.next = current.next;
				 detach(factoryName);
			 }
		
	}
	
	/**
	 * The attach method takes in three parameters which are the factory name, stop number, 
	 * and material name. The method then reads through the list until it reaches the end
	 * then the car is added to the list.
	 * @param factoryName
	 * @param stopNumber
	 * @param materialName
	 */
	public void attach(String factoryName, int stopNumber, String materialName) {
		if(front == null) {
			front = new Car(factoryName,stopNumber,materialName);
		} else {
			front = new Car(factoryName,stopNumber,materialName, front);
		}
		
		sort();
	}
	
	/**
	 * The search method takes in one parameter which is the factory name. This method then
	 * uses a loop to iterate over every item until it finds the car with the factory name.
	 * This loop also works until it reaches the end, so it will find all of the cars.
	 * @param factoryName
	 */
	public void search(String factoryName) {
		Car current = this.front;
		if (front == null) {
			System.out.println("Train car is empty.");
			return;
		} 
		while (current != null) {
			if (current.factory.equalsIgnoreCase(factoryName)) {
				System.out.println("This train car contains " + current.material);
			}
			current = current.next;
		}
	}
		
	/**
	 * The getCars method takes a String factory name as a parameter. 
	 * A while loop then iterates through the list and locates all of the
	 * cars until it finds one with the factory name. If found, the cars 
	 * materials are returned in a list.
	 * @param factoryName
	 * @return
	 */
	public List<String> getCars(String factoryName) {
		List<String> materials = new LinkedList<>();
		Car current = this.front;
		while(current.next != null) {
			if (current.next.factory.equalsIgnoreCase(factoryName)) {
				materials.add(current.material);
			}
			current = current.next;
		}
		return materials;
	}
	
	/**
	 * The displayTrainCars method outputs the entire train for the user. The train's
	 * cars and its contents are outputted onto the console in a sorted order.
	 * Also some logic is involved for the data to be organized, for ease of understanding.
	 */
	public void displayTrainCars() {
		if(front == null) {
			System.out.println("Empty");
		} else {
			if (front.factory.length()<8)
				System.out.println(front.factory + "\t\t" + front.stop + "\t" + front.material);
			else
				System.out.println(front.factory + "\t" + front.stop + "\t" + front.material);
			for(Car current = front.next; current != null; current=current.next) {
				if (current.factory.length()<8)
					System.out.println(current.factory + "\t\t" + current.stop + "\t" + current.material);
				else
					System.out.println(current.factory + "\t" + current.stop + "\t" + current.material);
			}
		}
	}
	
	/**
	 * The merge method takes a String update, which is the name of a file.
	 * The file is then read using a Scanner and attached to the train using 
	 * the attach method. Some String commands like .split into a char array
	 * are used for ease of understanding.
	 * @param update
	 * @throws FileNotFoundException
	 */
	public void merge(String update) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(update));
		while(scan.hasNextLine()) {
			String[] tokens = scan.nextLine().split(",");
			int a = 0;
			for(String b : tokens) {
				tokens[a] = b;
				a++;
			}
			attach(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
		}
		sort();
	}
	
	/**
	 * The sort method was added into the class to sort the Train linked list
	 * for ease of understanding the information and organization. This method uses
	 * a index temp car object that is used to compare the stop values of the
	 * cars in the list. If the next address of the cars is smaller, it is moved
	 * in front of it. A nested while loop is used to complete the sorting.
	 */
	public void sort() {
		Car current = this.front;
		Car index = null;
		int temp;
		String tempFactory;
		String tempMaterial;
		
		if(this.front == null) {
			return;
		} else {
			while (current != null) {
				index = current.next;
				
				while( index != null) {
					if (current.stop > index.stop) {
						temp = current.stop;
						tempFactory = current.factory;
						tempMaterial = current.material;
						
						current.stop = index.stop;
						current.factory = index.factory;
						current.material = index.material;
						
						index.stop = temp;
						index.factory = tempFactory;
						index.material = tempMaterial;
					}
					index = index.next;	
				}
				current = current.next;
			}
		}
		
		
	}
	/**
	 * The displayMenu method is a collection of Strings that outline 
	 * the user interface that the user will use to navigate the different methods.
	 */
	public void displayMenu () {
		System.out.println("Train Depot Menu Options\n");
		System.out.println("Enter A to Attach a train car");
		System.out.println("Enter R to Detatch car at factory");
		System.out.println("Enter D to Display all the train cars");
		System.out.println("Enter S to Search a train car");
		System.out.println("Enter M to Merge two train cars");
		System.out.println("Enter Q to Quit");
		System.out.println("\nPlease enter your choice ");
	}
}
