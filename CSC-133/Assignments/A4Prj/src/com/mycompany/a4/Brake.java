/*
 * This class is to serve as a command to brake the
 * PlayerCyborg using the Cyborg{}'s method brake().
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;

public class Brake extends Command{

	private GameWorld gw = new GameWorld();
		
	public Brake(GameWorld gw) {
		super("Brake");
		this.gw = gw;
			
		}

	/*
	 * This method calls the player's brake() method, and then checks
	 * if the player dies, as the speed may be reduced to 0. NotifyObservers() 
	 * is then called.
	 */
	public void actionPerformed(ActionEvent ev) {
		PlayerCyborg player = gw.getPlayer();
		System.out.println("_______________________________________________________________\n");
		
		player.brake();			//prints line in method
		gw.checkDead(player);
		System.out.println("_______________________________________________________________\n");
		
		gw.notifyObservers();
	}
}

