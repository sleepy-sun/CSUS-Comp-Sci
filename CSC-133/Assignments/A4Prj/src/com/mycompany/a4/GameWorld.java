package com.mycompany.a4;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.models.Point;
import java.util.Vector;
import com.codename1.ui.Form;
import java.util.Random;
import java.util.Random;
import com.codename1.ui.Dialog;
import java.util.*;

public class GameWorld extends Observable{

	//Define GameWorld constants, can be changed to play game differently
	private static final int maxDamage = 500;
	private static int lives = 3;
	
	
	private int clock;
	private int secCounter;
	private int worldWidth;
	private int worldHeight;
	
	private GameObjectCollection collection;

	private PlayerCyborg player;
	private boolean sound;
	private GameObjectCollection collisionsHandled;
	private Sound cyborgCrash;
	private Sound esCharge;
	private Sound lostLife;
	private BGSound bgSong;
	
	public GameWorld() {}
	
	/*
	 * Initializes the GameWorld's variables: clock, sound, and creates and adds 
	 * a PlayerCyborg, 4 Bases, 2 Drones, 2 EnergyStations, and 3 NonPlayerCyborgs. The initialization 
	 * will also call notifyObservers() once finished.
	 */
	public void init() {
		clock = 0;
		secCounter = 0;
		sound = true;
		
		collection = new GameObjectCollection();
		
		player = player.getPlayer(65, 65, maxDamage, this);
		collection.add(player);
		
		collection.add(new Base(1200, 270, 1, this));
		collection.add(new Base(250, 900, 2, this));
		collection.add(new Base(443, 530, 3, this));
		collection.add(new Base(1280, 950, 4, this));

		collection.add(new Drone(this));
		collection.add(new Drone(this));
		
		collection.add(new EnergyStation(this) );
		collection.add(new EnergyStation(this) );
		
		collection.add(new NonPlayerCyborg(200, 550, 40, this) );
		collection.add(new NonPlayerCyborg(843, 536, 40, this) );
		collection.add(new NonPlayerCyborg(900, 880, 40, this) );
	
		notifyObservers();
	}
	
	public void tick(int time) {
		
		System.out.println("_______________________________________________________________\n");
		System.out.println("Time Command\n");
		
		//Increment the amount of seconds gone by since the game has started
		secCounter = secCounter + time;
		if(secCounter % 1000 == 0) {		//If a total of a second has gone by, clock can tick by 1 sec., along with other functions supposed to be applied by a sec
			clock = clock + 1;
			System.out.println("THe clock ticked by a sec\n");
			
			//Obtain current energyLev, and energyConRate, and decrement Cyborg's energy level 
			int energyLevel = player.getEnergyLevel();
			int energyConRate = player.getEnergyConRate();
			player.setEnergyLevel(energyLevel - energyConRate);
			System.out.println("- You lost some energy from your consumption rate! Your energy level is now " + player.getEnergyLevel() + " \n");
		}
		
		//Change the heading of player Cyborg whether or not there is a new steeringDir
		int steerDir = player.getSteeringDir();
		player.setHeading(steerDir);
		System.out.println("- Your heading has changed to " + player.getHeading() + " \n");
		
		
		//Get an Iterator of the collection and find all movable GameObjects. Then, move() them
		//This also will update a Drone's heading automatically as it does so in it's move() method.
		//If the movable object is also a NPC, it will invoke it's strategy
		IIterator objects = collection.getIterator();
				
			while(objects.hasNext()) {
				GameObject obj = (GameObject) objects.getNext();
				if(obj instanceof Movable) {
					Movable m = (Movable) obj;
					if(obj instanceof NonPlayerCyborg) {
						NonPlayerCyborg npc = (NonPlayerCyborg) obj;
						npc.invokeStrategy();	//also changes NPC's heading
					}
					m.move(time);
				}
			}
		
		
			
		//Check if there are collisions, if so handle them, but do not handle them twice
			checkAndHandleColls();
			
			
			System.out.println("- All movable objects have moved \n");
			System.out.println("- All Non-Player Cyborgs are invoking their strategies. \n");
			
			//Damage and energy has been affected, check if lost life
			checkDead(player);
	
			
		System.out.println("\nEnd of Time Command");
		System.out.println("_______________________________________________________________\n");
		
		//Values have changed --> notifyObservers()
		notifyObservers();
		
	}
	
	
	//Check if there are collisions, if so handle them, but do not handle them twice
	public void checkAndHandleColls() {
		
		IIterator objects = collection.getIterator();		
	
		while(objects.hasNext()) {
			GameObject curObj = (GameObject) objects.getNext();											
	
			IIterator objects2 = collection.getIterator();
			while(objects2.hasNext()) {
				GameObject otherObj = (GameObject) objects2.getNext();
								
				if(curObj != otherObj) {
					if(curObj.collidesWith(otherObj)) {	//If there is a collision
						
						Vector otherObjColls = otherObj.getCollisions();			//check to see if otherObj has already handled their collision before
						boolean otherHandled = otherObjColls.contains(curObj);
						Vector curObjColls = curObj.getCollisions();
						boolean curHandled = curObjColls.contains(otherObj);
						
						if((otherHandled == false) && (curHandled == false)) {					//if not, handle it and add it to its own collection
							curObj.handleCollision(otherObj);
							curObj.add(otherObj);
							otherObj.add(curObj);
						}
					}
					
					else {	//the second there is no collision, check to see if there was one prior in collision Vectors, and remove object
						
						Vector otherObjColls = otherObj.getCollisions();			
						boolean otherDidHandle = otherObjColls.contains(curObj);
						
						Vector curObjColls = curObj.getCollisions();
						boolean curDidHandle = curObjColls.contains(otherObj);
						
						if(otherDidHandle && curDidHandle) {
							otherObjColls.remove(curObj);
							curObjColls.remove(otherObj);
						}
					}		
				}
			}
		}
	}
	
	public void createSounds() {
		cyborgCrash = new Sound("carcrash.wav");
		esCharge = new Sound("shield_battery.wav");
		lostLife = new Sound("death4.wav");
		
		bgSong = new BGSound("UpbeatFunk.wav");
		bgSong.play();
	}
	
	
	
	/*
	 * This method is to be called when the PlayerCyborg loses a life, and needs
	 * to restart another. It resets by calling player.resetPlayer(), then re-initializing
	 * the player. 
	 */
	public void resetLife() {
		lostLife.play();
		
		//1.)Make the player null, then make the PlayerCyborg{} re-initialize itself
		player.resetPlayer();			
		player = player.getPlayer(65, 65, maxDamage, this);	
		System.out.println(player.toString());
	
		showText("Because you are starting a new life, your values have been reset!");
		
		//2.) Variables have changed --> notifyObservers()
		notifyObservers();
	}
	
	
	
	
	/*
	 * This method checks whether or not a Cyborg needs to lose a life,
	 * or if its Game Over for the PlayerCyborg. The method should be used 
	 * after every situation where the Cyborg gains damage, loses speed, or energy.
	 */
	public void checkDead(Cyborg cy) {
		//1.) Obtain the damage and speed values of the Cyborg
		int damage = cy.getDamageLevel();
		int speed = cy.getSpeed();
		
		//1a.)If the Cyborg is the player, must also obtain its energy values, and test for lives
		if(cy instanceof PlayerCyborg) {
			int energy = player.getEnergyLevel();
			
			//1b.) Separate if statement due to the player's energy value: 
			//		**loses life if (1)damage exceeds the max, (2) has no speed, (3) has no energy
			if((damage >= maxDamage)|| (speed <= 0) || (energy <= 0 )) {		
				//2.) If there is more than 1 life left --> player's lives deducts and reset new life
				if(lives > 1)	{
					lives = lives - 1;
					if(damage >= maxDamage) {
						showText("Your damage went over the max amount of damage allowed, and lost a life! You gained " + damage + " when the max damage is " + maxDamage +". You now only have " + lives + "lives left.");
					}
					else if (speed <= 0) {
						showText("Your speed went below 0. You lost a life! You now only have " + lives + "lives left.");
					}
					else {
						showText("You let your energy get to 0! You lost a life. You now only have " + lives + " lives left.");
					}
					
					resetLife();
					
				}
			
				//2a.) Otherwise, this was the player's last life, lose game
				else { 
				showText("You lost all your lives before you could reach the last base! You lost, the game is over!");
					exit();
				}
			}
			
		}
		
		//1c.) Test the NPC: dies if (1) damage exceeds the max or (2) has no speed
		//Find the specific NPC and remove from the GameObjectCollection
		else {
			if(damage >= maxDamage) {
				showText("One of the NPC's gained more the max amount of damage allowed, and died! It had " + damage + " damage when the max is " + maxDamage);
				
				IIterator i = collection.getIterator();
				while(i.hasNext()) {
					GameObject o = (GameObject) i.getNext();
					if(o.getX() == cy.getX()) {
						int index = collection.getIndexOf(o);
						collection.remove(index);
						break;
					}
				}
				
			}	
			else if(speed <= 0) {
				showText("One of the NPC's lost all of its speed and died!");
				IIterator i = collection.getIterator();
				while(i.hasNext()) {
					GameObject o = (GameObject) i.getNext();
					if(o.getX() == cy.getX()) {
						int index = collection.getIndexOf(o);
						collection.remove(index);
						break;
					}
				}
			}
			
			
		}
	}
	
	
	
	
	//Collision methods:
	public void collideWithBase(Cyborg cy, Base b, GameObject leftObj) {

		int base = b.getNumber();
		
		//1.)Test whether the base inputed is the correct base to have collided with
		System.out.println("_____________________________________________________________________");
		System.out.println("CollideWithBase Command");
		
		int lastBase = cy.getLastBaseReached();
		
		
			// a.)If yes, then state so
		if(base == lastBase + 1) {
			if(cy instanceof PlayerCyborg) {
				System.out.println("Your Cyborg has collided with base #" + base);
			}
			
			cy.setLastBaseReached(base);
			
				//-If the base is the last base, win the game and exit
			if(base == 9) {
				if(cy instanceof PlayerCyborg) {
					System.out.println("The base you reached was the last base, you won the Game! ");
					exit();
				}
				
				else {
					System.out.println("A Non-Player Cyborg reached the last base before you. Sorry, you lost!");
					exit();
				}
			}
			else {
				if(cy instanceof PlayerCyborg) {
					System.out.println("That was the correct base, you are on track!");
				}
			}
		}
		
			//b.) Otherwise, direct user to the correct base
		else {
			if(cy instanceof PlayerCyborg) {
				System.out.println("Your Cyborg collided with base #" + base + ", but it wasn't the correct base. Your last base that was reached is #" + lastBase+ ". You must reach base #" + (lastBase +1) + " to be on track");
			}
		}
		
		System.out.println("\nEnd of Collide With Base Command");
		System.out.println("_______________________________________________________________\n");
		
	
		//2.) Variables have been updated, notify the Observers
		notifyObservers();	
	}
	
	
	
	public void collideWithES(Cyborg cy, EnergyStation es, GameObject leftObj) {
		System.out.println("_______________________________________________________________________________________\n");
		System.out.println("Collide with EnergyStation Command\n");
		esCharge.play();
		
		//1.)Obtain capacity of EnergyStation 
				int capacity = es.getCapacity();		
		
				//1a.)If there is energy left: give to player Cyborg, fade EnergyStation color, and create a new EnergyStation
				if(capacity > 0) {
					int currentCyborgEnergy = cy.getEnergyLevel();
					
					cy.setEnergyLevel(currentCyborgEnergy + capacity);	
					es.setCapacity(0);				//This ES is now at 0 capacity
					
					//Fade ES color
					int eColor = es.getColor();
					es.setColor(eColor - 50);		
					
					//Creates a new EnergyStation
					collection.add(new EnergyStation(this));		//Add new ES to the collection (constructor makes with random size and location)
					if(cy instanceof PlayerCyborg) {
						System.out.println("You collided with an Energy Station and recieved " + capacity + " amount of energy! You now have " + cy.getEnergyLevel() + "amount of energy!");
				
					}
				}
				//1b.) If there is no energy, relay message
				else {		
					if(cy instanceof PlayerCyborg) {
						System.out.println("You have collided with an Energy Station, but unfortunately it was empty already. You gained no energy from this.");
					}
				}
				
				System.out.println("\nEnd of Collide with EnergyStation Command");
				System.out.println("_______________________________________________________________________________________\n");
				
				
		//2.) To ensure collision is done once, move Cyborg away --> avoidCollision()
		//avoidCollision(cy, es, leftObj);
		
		
		//3.)Variables have changed, notify observers
				notifyObservers();
	}
	
	
	
	public void collideWithDrone(Cyborg cy, Drone d, GameObject leftObj) {
		System.out.println("_________________________________________________________________________________________\n");
		System.out.println("Collide with Drone Command\n");
		
		//1.) Obtain the Drone's location and assign to player's (As stated in A#1)
		float xLoc = d.getX();
		float yLoc = d.getY();
		
		//Also no specification on who's location to assign to who's: uses Drone location to update player's
		cy.setLocation(xLoc, yLoc);
		
		
		//No specification on increase of damage: uses random generated damage 
		//3.)Generate a random caused damage and set the player's new damage
		Random r = new Random();
		int causedDamage = 1 + r.nextInt(4); 	//random damage from 1- 5(maxDamage set at 8)
		
		int currentDamage = cy.getDamageLevel();			//obtain current damage
		int newDamage = currentDamage + causedDamage;
		cy.setDamageLevel(newDamage);		
		
		//4.)Decreases player's speed accordingly due to new damage
		cy.speedDueToColl();	
		
		
		//5.) Check to see if the new damage and speed caused the player to lose a life
		checkDead(cy);
				
		
		//6.)Fade player's color
		int currentColor = cy.getColor();
		cy.setColor(currentColor- 50);		//fades color by 20 each time
		
		System.out.println("\nEnd of Collide with Drone Command");
		System.out.println("____________________________________________________________________________________________________\n");
		
		/*7.) To ensure collision is done once, move Cyborg away --> avoidCollision()
		*/
		//avoidCollision(cy, d, leftObj);
		
		//8.) show notifications
		if(cy instanceof PlayerCyborg) {
			System.out.println("Your Cyborg crashed into a Drone! You gained " + causedDamage + " damage, decreased speed, and faded in color.");
			/*showText("You gained " + causedDamage + " more damage. Your damage level is now at " + newDamage + "\n");
			showText("Due to the damage, your speed decreased and is now: " + cy.getSpeed());*/
		}
		
		//9.) Variables have changed, notify observers
		notifyObservers();
	}
	
	
	
	/*
	 * This method derives a random amount of damage to apply to both cyborgs in a cyborg collision
	 */
	public void collideWithCyborg(Cyborg cy1, Cyborg cy2, GameObject leftObj) {
		cyborgCrash.play();
		
		//1.)Obtain a random damage from 1-5 (maxDamage set at 8)
		Random r = new Random();
		int causedDamage = 1 + r.nextInt(4); 
		
		//2.) Have both cyborgs experience the same effects
		applyCyCollision(cy1, causedDamage);
		applyCyCollision(cy2, causedDamage);
		
		//3.) Avoid collision, choose cy1 to move away from cy2:
		//avoidCollision(cy1, cy2, leftObj);
	}
	
	/*
	 * This method gives the given cyborg all the effects of colliding with another cyborg
	 */
	public void applyCyCollision(Cyborg cy, int causedDamage) {
		if(cy instanceof PlayerCyborg) {
			System.out.println("You collided into a Non-Player Cyborg! You have gained damage, decreased speed, and faded in color!");
		}
		//1.)Set the cyborg's new damage
		
			int currentDamage = cy.getDamageLevel();			//obtain current damage
			int newDamage = currentDamage + causedDamage;
			cy.setDamageLevel(newDamage);		
				
			if(cy instanceof PlayerCyborg) {
				//showText("You gained " + causedDamage + " more damage. Your damage level is now at " + newDamage + "The max amount of damage you can have is" + maxDamage);
			}				
		//3.)Decrease the speed due to the damage accordingly
			cy.speedDueToColl();			
				
			if(cy instanceof PlayerCyborg) {
				//showText("Due to the damage, your speed decreased and is now: " + cy.getSpeed());
			}
		//4.) Check if the new damage and speed caused the player to lose a life, or the NPC to die:
			checkDead(cy);
							
		//5.)Fade the Cyborg's color
			int currentColor = cy.getColor();
			cy.setColor(currentColor- 50);		//fades color by 20 each time
	}
	
	/*
	 * This method will show a Dialog box on the screen to display a given notification
	 */
	public void showText(String text) {
		Boolean ok = Dialog.show("Notification", text, "Ok", "Cancel");
	}
	
	/*
	 * Asks for confirmation to quit
	 */
	public void exit() {
		Boolean ok = Dialog.show("Confirm quit", "Are you sure you want to exit?", "Yes", "No");
		if(ok) {
			System.exit(0);
		}
		
	}
	/*
	 * This method calls its parent's, Observable, addObserver() method.
	 */
	public void addObserver(Observer obj) {
		super.addObserver(obj);
	}
	
	/*
	 * This method calls its parent's, Observable, setChanged() and 
	 * notifyObservers() method.
	 */
	public void notifyObservers() {
		super.setChanged();
		super.notifyObservers(this);
		
	}
	
	/*
	 * List of Setter methods, self-explanatory
	 */
	public void setTime(int t) {
		clock = t;
	}
	
	public void setWidth(int w) {
		worldWidth = w;
	}
	
	public void setHeight(int h) {
		worldHeight = h;
	}
	public void setSound(boolean value) {
		sound = value;
	}
	
	public void setLives(int l) {
		lives = l;
	}
	
	/*
	 * List of Getter methods, self-explanatory
	 */
	public PlayerCyborg getPlayer() {
		return player;
	}
	public BGSound getBGSong() {
		return bgSong;
	}
	public GameObjectCollection getCollection() {
		return collection;
	}
	public int getTime() {
		return clock;
	}
	
	public int getLivesLeft() {
		return lives;
	}
	
	public int getLastBase() {
		return player.getLastBaseReached();
	}
	
	public int getEnergyLev() {
		return player.getEnergyLevel();
	}
	
	public int getDamageLev() {
		return player.getDamageLevel();
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public int getMaxDamage() {
		return maxDamage;
	}
	
	public int getWidth() {
		return worldWidth;
	}
	
	public int getHeight() {
		return worldHeight;
	}
}
