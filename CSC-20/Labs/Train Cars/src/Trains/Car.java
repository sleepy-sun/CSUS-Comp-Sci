package Trains;

/**
 * @author Javier Briseno
 * The Car class constructs a chain of theoretical "train cars" that impersonate
 * a linked list. Every car object in the list has the attributes of having
 * a factory name, material name, and stop number, and a address to the next Car object 
 * in the list. Other classes can complement this class, and use it to utilize
 * a linked list that resembles a train.
 */
public  class Car {
	public String factory;
	public int stop;
	public String material;
	public Car next;
		
	/**
	 * Car object that calls on the regular constructor using default
	 *  parameters. Default parameters would be "" for Strings, null 
	 *  for another car object, and 0 for int.
	 */
	public Car(){
	 // default constructor
		this.factory = "";
		this.stop = 0;
		this.material = "";
		this.next = null;
			
	}
	 /**
	  * Initializes a car object that calls on the regular constructor using some
	  * given parameters and a null address for the next car object. This constructer
	  * is great for adding the last car to the list.
	 * @param fact
	 * @param s
	 * @param m
	 */
	public Car(String fact, int s, String m){
	    // regular constructor sets next to null
		this.factory = fact;
	    this.stop = s;
	    this.material = m;
	    this.next = null;
	    	
	    			
	}

	 /**
	  * Initializes a car object that creates the car object with a address 
	  * of the next car object in the list. This is the main constructor, all 
	  * of the other constructors call on this one. Great for adding car objects 
	  * in the middle of the list.
	 * @param fact
	 * @param s
	 * @param m
	 * @param next
	 */
	public Car(String fact, int s, String m, Car next){
	    // regular constructor sets   this.next=next;
	    this.factory = fact;
	    this.stop = s;
	    this.material = m;
	    this.next = next;
	}

}