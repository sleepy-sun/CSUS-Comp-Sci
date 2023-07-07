/*
 * This class serves as a command to invoke the effects of a PlayerCyborg
 * colliding with an NonPlayerCyborg.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import java.util.Random;

import com.codename1.ui.Command;

public class CollideWithNPC extends Command{

	private GameWorld gw = new GameWorld();
	private int causedDamage;
	
	public CollideWithNPC(GameWorld gw) {
		super("Collision with NPC");
		this.gw = gw;
		
		Random r = new Random();
		causedDamage = 1 + r.nextInt(4); 	//random damage from 1- 5(maxDamage set at 8)
	}

	/*
	 * This method invokes collision() on player and a random NPC. Collision()
	 * will invoke all effects of a collision between two cyborgs. NotifyObservers() 
	 * is called at the end.
	 */
	public void actionPerformed(ActionEvent ev) {
		System.out.println("____________________________________________________________");
		System.out.println("Collide With Non-Player Cyborg Command\n");
		System.out.println("- You have collided with a NonPlayer Cyborg!\n");
		
		PlayerCyborg player = gw.getPlayer();
		
		collision(player);
		
		//NPC is affected in the same ways as the player:
		
		//1.) Choose a random NPC out of the 3 in the collection
		Random r = new Random();
		int npcCounter = 1 + r.nextInt(3);		
		
		//1a.) Obtain the IIterator
		GameObjectCollection c = gw.getCollection();
		IIterator i = c.getIterator();
		
		//1b.)Use a counter to count until the nth NPC, nth = the random # generated
		int counter = 1;								
		NonPlayerCyborg npc = new NonPlayerCyborg(0,0,0, gw);
		
		while(i.hasNext()) {	
			GameObject obj = (GameObject) i.getNext();
			if(obj instanceof NonPlayerCyborg) {				//traverse until find an NPC
				if(counter != npcCounter) {
					counter++;								
				}
				else {
					npc = (NonPlayerCyborg) obj;			//Breaks out of loop once found the nth NPC
					break;
				}
			}
		}
		
		//2.) Now that the NPC is obtained, let it collide with the player
		collision(npc);
		
		System.out.println("\nEnd of Collide With NPC Command\n");
		System.out.println("__________________________________________________\n");
		
		
		//3.)Variables have changed, notifyObservers
		gw.notifyObservers();
	}
	
	/*
	 * This method takes a Cyborg and will decrease its damage by a previously
	 * randomly generated value, reduce it's speed due to the damage, check if the cyborg 
	 * is dead, and fade the cyborg's color.
	 */
	public void collision(Cyborg cy) {
		//1.)causedDamage = Random value generated in constructor --> NPC and Player experiences same damage
		//int causedDamage = 1 + r.nextInt(4); 	//random damage from 1- 5(maxDamage set at 8)
		//2.)Set the cyborg's new damage
		int currentDamage = cy.getDamageLevel();			//obtain current damage
		int newDamage = currentDamage + causedDamage;
		cy.setDamageLevel(newDamage);		
		
		if(cy instanceof PlayerCyborg) {
			System.out.println("- You gained " + causedDamage + " more damage. Your damage level is now at " + newDamage + "\n");
			System.out.println("- The max amount of damage you can have is " + gw.getMaxDamage() + "\n");
		}		
				
		//3.)Decrease the speed due to the damage accordingly
		cy.speedDueToColl();			
		
		if(cy instanceof PlayerCyborg) {
			System.out.println("- Due to the damage, your speed decreased and is now: " + cy.getSpeed() + "\n");
		}
		
		//4.) Check if the new damage and speed caused the player to lose a life, or the NPC to die:
		gw.checkDead(cy);
				
				
		//5.)Fade the Cyborg's color
		int currentColor = cy.getColor();
		cy.setColor(currentColor- 20);		//fades color by 20 each time
	}
}