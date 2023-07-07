package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandFoodStationCollision extends Command {
	private GameWorld gw;
	
	public CommandFoodStationCollision(GameWorld gw) {
		super("Collide with Food Station");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ant has collected some food!");
		gw.foodCollision();
	}
}
