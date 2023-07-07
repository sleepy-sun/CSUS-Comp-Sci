/*
 * This class is to serve as a command to accelerate the 
 * PlayerCyborg using the Cyborg{}'s method accelerate().
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;

public class Accelerate extends Command{

	private GameWorld gw = new GameWorld();
	
	public Accelerate(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
		
	}

	/*
	 * This method calls the player's acclerate().
	 */
	public void actionPerformed(ActionEvent ev) {
		PlayerCyborg player = gw.getPlayer();
		
		System.out.println("_______________________________________________________________\n");
		player.accelerate();
		System.out.println("_______________________________________________________________\n");
		
		gw.notifyObservers();
	}
}
