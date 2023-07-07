package com.mycompany.a4;
import com.codename1.ui.events.*;
import com.codename1.ui.Command;

public class Tick extends Command{

	private GameWorld gw = new GameWorld();
	private int secCounter;
	
	public Tick(GameWorld gw) {
		super("Tick");
		this.gw = gw;
		secCounter = 0;
	}

	/*
	 * This method will invoke all effects of the tick command:
	 * the time ticks by 1 sec, the headings of cyborgs change, 
	 * the player's energy depletes, and all movable objects move()
	 */
	public void actionPerformed(ActionEvent ev) {
		PlayerCyborg player = gw.getPlayer();
		
		System.out.println("_______________________________________________________________\n");
		System.out.println("Time Command\n");
		
		secCounter = secCounter + 20;
		if(secCounter % 1000 == 0) {		//If a total of a second has gone by, clock can tick by 1 sec., along with other functions supposed to be applied by a sec
			int clock = gw.getTime();
			gw.setTime(clock + 1);
			System.out.println("The clock ticked by a sec\n");
			
			//Obtain current energyLev, and energyConRate, and decrement Cyborg's energy level 
			int energyLevel = player.getEnergyLevel();
			int energyConRate = player.getEnergyConRate();
			player.setEnergyLevel(energyLevel - energyConRate);
			System.out.println("- You lost some energy from your consumption rate! Your energy level is now " + player.getEnergyLevel() + " \n");
			
			
			//Change the heading of player Cyborg whether or not there is a new steeringDir
			int steerDir = player.getSteeringDir();
			player.setHeading(steerDir);
			System.out.println("- Your heading has changed to " + player.getHeading() + " \n");
			
		}

		
		//Get an Iterator of the collection and find all movable GameObjects. Then, move() them
		//This also will update a Drone's heading automatically as it does so in it's move() method.
		//If the movable object is also a NPC, it will invoke it's strategy
		GameObjectCollection c = gw.getCollection();	
		IIterator objects = c.getIterator();
				
			while(objects.hasNext()) {
				GameObject obj = (GameObject) objects.getNext();
				if(obj instanceof Movable) {
					Movable m = (Movable) obj;
					if(obj instanceof NonPlayerCyborg) {
						NonPlayerCyborg npc = (NonPlayerCyborg) obj;
						npc.invokeStrategy();	//also changes NPC's heading
					}
					m.move(20);
				}
			}
			
			
			System.out.println("- All movable objects have moved \n");
			System.out.println("- All Non-Player Cyborgs are invoking their strategies. \n");
			
			gw.checkAndHandleColls();
			
			//Damage and energy has been affected, check if lost life
			gw.checkDead(player);
	
			
		System.out.println("\nEnd of Time Command");
		System.out.println("_______________________________________________________________\n");
		
		//Values have changed --> notifyObservers()
		gw.notifyObservers();
	}
		
}
