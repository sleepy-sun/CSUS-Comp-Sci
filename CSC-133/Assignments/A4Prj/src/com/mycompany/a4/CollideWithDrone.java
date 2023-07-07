/*
 * This class serves as a command to invoke effects to the player
 * when crashing into a Drone.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import java.util.Random;

import com.codename1.ui.Command;

public class CollideWithDrone extends Command{

	private GameWorld gw = new GameWorld();
	
	public CollideWithDrone(GameWorld gw) {
		super("Collision with Drone");
		this.gw = gw;
	}

	/*
	 * This method will assign the player's location to the first Drone's it finds
	 * as instructed in Assignment 1 The player also gains damage, decreases speed,
	 * and fades in color.
	 */
	public void actionPerformed(ActionEvent ev) {
		/*PlayerCyborg player = gw.getPlayer();
		
		System.out.println("_________________________________________________________________________________________\n");
		System.out.println("Collide with Drone Command\n");
		System.out.println("- Your Cyborg crossed over the same location as a Drone!\n");
		Drone d = new Drone(gw);
		
		//1.)Obtain an Iterator to find the first drone (no specifics on which drone to collide with)
		GameObjectCollection c = gw.getCollection();
		IIterator objects = c.getIterator();	
		while(objects.hasNext()) {						//traverse collection
			GameObject obj = (GameObject) objects.getNext(); 
			if(obj instanceof Drone) {
				d = (Drone) obj;	
				break;							//First Drone found will break the loop
			}
		}
		
		//2.) Obtain the Drone's location and assign to player's
		int xLoc = d.getX();
		int yLoc = d.getY();
		
		//Also no specification on who's location to assign to who's: uses Drone location to update player's
		player.setLocation(xLoc, yLoc);
		
		
		//No specification on increase of damage: uses random generated damage 
		//3.)Generate a random caused damage and set the player's new damage
		Random r = new Random();
		int causedDamage = 1 + r.nextInt(4); 	//random damage from 1- 5(maxDamage set at 8)
		
		int currentDamage = player.getDamageLevel();			//obtain current damage
		int newDamage = currentDamage + causedDamage;
		player.setDamageLevel(newDamage);		
		
		System.out.println("- You gained " + causedDamage + " more damage. Your damage level is now at " + newDamage + "\n");
		System.out.println("- The max Damage you can have is " + gw.getMaxDamage() + "\n");
		
		
		//4.)Decreases player's speed accordingly due to new damage
		player.speedDueToColl();	
		System.out.println("Due to the damage, your speed decreased and is now: " + player.getSpeed() + "\n");
		
		
		//5.) Check to see if the new damage and speed caused the player to lose a life
		gw.checkDead(player);
				
		
		//6.)Fade player's color
		int currentColor = player.getColor();
		player.setColor(currentColor- 20);		//fades color by 20 each time
		
		System.out.println("\nEnd of Collide with Drone Command");
		System.out.println("____________________________________________________________________________________________________\n");
		
		
		//7.) Variables have changed, notify observers
		gw.notifyObservers();*/
	}
}