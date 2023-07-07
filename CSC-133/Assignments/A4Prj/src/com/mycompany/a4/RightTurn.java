/*
 * This class serves as a command to steer a PlayerCyborg 
 * to the right, by calling the Cyborg{} rightSteer()
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;

public class RightTurn extends Command{

	private GameWorld gw = new GameWorld();
	
	public RightTurn(GameWorld gw) {
		super("Right Turn");
		this.gw = gw;
	}

	/*
	 * This method calls the player's rightSteer() and then notifies the
	 * GameWorld's observers.
	 */
	public void actionPerformed(ActionEvent ev) {
		PlayerCyborg player = gw.getPlayer();
	
		System.out.println("_______________________________________________________________\n");
		player.rightSteer();
		System.out.println("- You steered right by 15!\n");
		System.out.println("_______________________________________________________________\n");
		
		gw.notifyObservers();
	}
}