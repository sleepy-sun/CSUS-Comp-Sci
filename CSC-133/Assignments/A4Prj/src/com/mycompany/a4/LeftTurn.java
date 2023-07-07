/*
 * This class acts as a command that steers the player 
 * left using the Cyborg{} leftSteer()
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;

public class LeftTurn extends Command{

	private GameWorld gw = new GameWorld();
	
	public LeftTurn(GameWorld gw) {
		super("Left Turn");
		this.gw = gw;
	}

	/*
	 * This method steers the player left and notifies the GameWorld's observers.
	 */
	public void actionPerformed(ActionEvent ev) {
		PlayerCyborg player = gw.getPlayer();
		
		System.out.println("_______________________________________________________________\n");
		player.leftSteer();
		System.out.println("- You steered left by 15!\n");
		System.out.println("_______________________________________________________________\n");
	
		gw.notifyObservers();
	}
}


