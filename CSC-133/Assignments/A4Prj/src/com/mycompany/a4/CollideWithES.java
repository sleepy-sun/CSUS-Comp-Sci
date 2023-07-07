/*
 * This class serves as a command that invokes the effects of 
 * the PlayerCyborg colliding with an EnergyStation.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import java.util.Random;

import com.codename1.ui.Command;

public class CollideWithES extends Command{

	private GameWorld gw = new GameWorld();
	
	public CollideWithES(GameWorld gw) {
		super("Collision with Energy Station");
		this.gw = gw;
	}

	/*
	 * This method picks a random EnergyStation and gives its energy to the player if it has any.
	 * Then notifyObservers().
	 */
	public void actionPerformed(ActionEvent ev) {
		System.out.println("_______________________________________________________________________________________\n");
		System.out.println("Collide with EnergyStation Command\n");
		PlayerCyborg player = gw.getPlayer();
		
		//1.) Find set of EnergyStations in the collection
				//1a.) Obtain an IIterator, and create a collection for just EnergyStations
				GameObjectCollection c = gw.getCollection();
				IIterator objects = c.getIterator();
				GameObjectCollection energyStats = new GameObjectCollection();
				
				//1b.)Traverse the IIterator, add each ES into the new collection
				while(objects.hasNext()) {		
					GameObject obj= (GameObject) objects.getNext();
					if(obj instanceof EnergyStation) {	
						energyStats.add(obj);
					}
				}
				
		//2.)Pick a random EnergyStation from this new set
				Random r = new Random();
				int index = r.nextInt(energyStats.getSize()-1); 	//choose a random index from the collection of Energystations
				
				//2a.)Obtain an IIterator for the new collection of ES
				IIterator esIterator = energyStats.getIterator();
				EnergyStation e = new EnergyStation(gw);
				for(int i = 0; i == index; i++) {
					if(esIterator.hasNext()) {						//Traverses until the random index generated earlier
						 e = (EnergyStation) esIterator.getNext();
					}
				}
				
				
		//3.)Obtain capacity of EnergyStation 
				int capacity = e.getCapacity();		
		
				//3a.)If there is energy left: give to player Cyborg, fade EnergyStation color, and create a new EnergyStation
				if(capacity > 0) {
					int currentCyborgEnergy = player.getEnergyLevel();
					
					player.setEnergyLevel(currentCyborgEnergy + capacity);	
					e.setCapacity(0);				//This ES is now at 0 capacity
					
					//Fade ES color
					int eColor = e.getColor();
					e.setColor(eColor - 20);		
					
					//Creates a new EnergyStation
					c.add(new EnergyStation(gw));		//Add new ES to the collection (constructor makes with random size and location)
					System.out.println("- You collided with an Energy Station and recieved " + capacity + " amount of energy!\n");
				}
				
				//3b.) If there is no energy, relay message
				else {		
					System.out.println("- You have collided with an Energy Station, but unfortunately it was empty already. You gained no energy from this.\n");
				}
				
				System.out.println("\nEnd of Collide with EnergyStation Command");
				System.out.println("_______________________________________________________________________________________\n");
				
		//4.)Variables have changed, notify observers
				gw.notifyObservers();
	}
}