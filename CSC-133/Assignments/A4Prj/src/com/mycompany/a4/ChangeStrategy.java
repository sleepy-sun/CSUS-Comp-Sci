/*
 * This class is to change the currStrategy of every NPC in a given 
 * GameWorld.
 */

package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;

public class ChangeStrategy extends Command{

	private GameWorld gw = new GameWorld();
	
	public ChangeStrategy(GameWorld gw) {
		super("Change Strategy");
		this.gw = gw;
	}

	/*
	 * This method obtains the GameWorld's collection to find all NPCs. 
	 * All NPC's get stored into a new GameObjectCollection to then traverse
	 * to switch each of their strategies and update their lastBaseReached.
	 */
	public void actionPerformed(ActionEvent ev) {
		System.out.println("_______________________________________________________________\n");
		System.out.println("Change Strategy Command\n");
		
		//1.) Obtain collection, and create a new GameObjectCollection for just NPCs
		GameObjectCollection c = gw.getCollection();
		IIterator objects = c.getIterator();
		GameObjectCollection NPCs = new GameObjectCollection();
		
		//2.)Traverse the collection, each NPC found is stored into the new NPC collection
		while(objects.hasNext()) {		
			GameObject obj= (GameObject) objects.getNext();
			if(obj instanceof NonPlayerCyborg) {		//finds all NPCs and stores in a new GameObjectCollection
				NPCs.add(obj);
			}
		}
		
		//3.)There exists only 2 strategies --> if not one, then switch to the other:
		
		
		//3a.)Obtain IIterator of the new NPC collection, and for each switch their strategy:
		IIterator npc = NPCs.getIterator();
		while(npc.hasNext()) {
			NonPlayerCyborg n = (NonPlayerCyborg) npc.getNext();
			if(n.getStrategy() instanceof Attack) {					//	**If the current strategy is Attack, then switch to NextBase, vice versa
				n.setStrategy(new NextBase(n, n.getBasesInGW()));	//"n.getBasesinGW()" passes in the now needed GameWorld bases via GameObjectCollection
			}
			
			else {// currStrategy must = NextBase, --> switch to Attack Strategy
				n.setStrategy(new Attack(n, n.getPlayer()));	
			}

			}
		
		
		System.out.println("\nEnd of Change Strategy Command");
		System.out.println("_______________________________________________________________\n");
		
		//5.) Variables have changed --> notifyObservers();
		gw.notifyObservers();
	}
}